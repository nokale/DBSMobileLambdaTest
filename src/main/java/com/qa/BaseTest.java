package com.qa;

import com.qa.Listners.PropertyFileReader;
import com.qa.Utils.ExcelModel;
import com.qa.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public AppiumDriver driver;
    static PropertyFileReader propertyFileReader;
    TestUtils utils = new TestUtils();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

    public AppiumDriver getDriver() {
        return driver;
    }

    public String getDateTime() {
        return dateTime.get();
    }


    protected void setUp(String deviceName) {
        String propFileName = "src/main/resources/config.properties";
        propertyFileReader = new PropertyFileReader(propFileName);
        try {
            utils.log().info("load " + propFileName);
            String devicePrefix = "device";
            int totalDevices = 1;
            for (int i = 1; ; i++) {
                /**
                 * Count the number of devices listed in the properties file
                 */
                String deviceKey = devicePrefix + i;
                String deviceValueKey = deviceKey + ".DeviceValue";
                String deviceValue = propertyFileReader.getProperty(deviceValueKey);
                if (deviceValue != null) {
                    totalDevices++;
                } else {
                    break;
                }
            }
            for (int deviceCount = 1; deviceCount <= totalDevices; deviceCount++) {
                String deviceKey = devicePrefix + deviceCount;
                String deviceValueKey = deviceKey + ".DeviceValue";
                String deviceValue = propertyFileReader.getProperty(deviceValueKey);
                if (deviceValue != null) {
                    System.out.println("setUp: " + deviceValue);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
            e.printStackTrace();
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
        try {
            DesiredCapabilities capabilities;
            String[] parts = deviceName.split("\\.");
            String actualDeviceName = parts[0];
            String deviceValueKey = actualDeviceName + ".DeviceValue";
            String versionValueKey = actualDeviceName + ".versionValue";
            String platformValueKey = actualDeviceName + ".PlatformValue";
            String DeviceValue = propertyFileReader.getProperty(deviceValueKey);
            String versionValue = propertyFileReader.getProperty(versionValueKey);
            String PlatformValue = propertyFileReader.getProperty(platformValueKey);
//            System.out.println("Device Value: " + DeviceValue);
//            System.out.println("Version Value: " + versionValue);
//            System.out.println("Platform Value: " + PlatformValue);
            // Check if any of the values are null
            if (DeviceValue == null || versionValue == null || PlatformValue == null) {
                throw new IllegalArgumentException("One or more values not found for device: " + deviceName);
            }
            switch (PlatformValue.toLowerCase()) {
                case "android":
                    capabilities = getAndroidCapabilities(PlatformValue,DeviceValue, versionValue);
                    break;
                case "ios":
                    capabilities = getIOSCapabilities(PlatformValue,DeviceValue, versionValue);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid platform value: " + PlatformValue);
            }
            String username = propertyFileReader.getProperty("LT_USERNAME");
            String accessKey = propertyFileReader.getProperty("LT_ACCESS_KEY");
            String hubUrl = "https://"+username+":"+accessKey+"@mobile-hub.lambdatest.com/wd/hub";
                    //"https://mobile-hub.lambdatest.com/wd/hub";
            try {
                if (PlatformValue.equalsIgnoreCase("android")) {
                   // capabilities.setCapability("user", username);
                   // capabilities.setCapability("accessKey", accessKey);
                    driver = new AndroidDriver<>(new URL(hubUrl), capabilities);
                   String str= String.valueOf(driver.getSessionId());
                    utils.log("Session ID: "+str);
                } else if (PlatformValue.equalsIgnoreCase("ios")) {
                    capabilities.setCapability("user", username);
                    capabilities.setCapability("accessKey", accessKey);
                    driver = new IOSDriver<>(new URL(hubUrl), capabilities);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error initializing AppiumDriver: " + e.getMessage());
            }
            utils.log().info("driver initialized: " + driver);
        } catch (Exception e) {
            utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
            e.printStackTrace();
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
    }

    private DesiredCapabilities getAndroidCapabilities(String PlatformValue, String DeviceValue, String versionValue) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "TEST DBS UAT ");
        capabilities.setCapability("name", PlatformValue + " " + DeviceValue + " " + versionValue);
        capabilities.setCapability("platformName", PlatformValue);
        capabilities.setCapability("deviceName", DeviceValue);
        capabilities.setCapability("platformVersion", versionValue);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("app", propertyFileReader.getProperty("AndroidApplicationID"));
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console", true);
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("devicelog", true);
        capabilities.setCapability("enableScreenshotUnblock", false);
       // capabilities.setCapability("autoWebview", true);
        return capabilities;
    }

    private DesiredCapabilities getIOSCapabilities(String PlatformValue, String DeviceValue, String versionValue) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "DBS UAT APPLICATION");
        capabilities.setCapability("name", PlatformValue + " " + DeviceValue + " " + versionValue);
        capabilities.setCapability("platformName", PlatformValue);
        capabilities.setCapability("deviceName", DeviceValue);
        capabilities.setCapability("platformVersion", versionValue);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("app", propertyFileReader.getProperty("IOSApplicationID"));
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console", true);
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("devicelog", true);
        capabilities.setCapability("enableScreenshotUnblock", true);
        //capabilities.setCapability("autoWebview", true);
        return capabilities;
    }


    public void tearDown() {
        if (driver != null) {
            utils.log("Closing the browser.");
            driver.quit();
        }
    }

    public void implicitlyWait(AppiumDriver driver1){
        try{
            driver1.manage().timeouts().implicitlyWait(7000, TimeUnit.SECONDS);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    protected String getPlatform(String deviceName) {
        String propFileName = "src/main/resources/config.properties";
        PropertyFileReader propertyFileReader = new PropertyFileReader(propFileName);
        String[] parts = deviceName.split("\\.");
        String actualDeviceName = parts[0];
        String deviceKey = actualDeviceName + ".DeviceValue";
        String platformKey = actualDeviceName + ".PlatformValue";

        try {
            String deviceValue = propertyFileReader.getProperty(deviceKey);
            if (deviceValue != null) {
                System.out.println("setUp: " + deviceValue);
                String platformValue = propertyFileReader.getProperty(platformKey);
                if (platformValue != null) {
                    return platformValue.toLowerCase();
                } else {
                    throw new IllegalArgumentException("Platform value not found for device: " + deviceName);
                }
            } else {
                throw new IllegalArgumentException("Device value not found for device: " + deviceName);
            }
        } catch (Exception e) {
            utils.log().fatal("Error getting platform. ABORT!!!\n" + e.toString());
            e.printStackTrace();
            throw new RuntimeException("Error getting platform: " + e.getMessage());
        }
    }

    public static List<ExcelModel> getFilteredTestData(String testNameFilter) {
        String propFileName = "src/main/resources/config.properties";
        propertyFileReader = new PropertyFileReader(propFileName);
        List<ExcelModel> testData = new ArrayList<>();
        String ExcelFile = System.getProperty("user.dir") + propertyFileReader.getExcel();
        String DataSheet = propertyFileReader.getSheetName();
        try (FileInputStream fileInputStream = new FileInputStream(ExcelFile);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheet(DataSheet);
            Iterator<Row> iterator = sheet.iterator();
            if (iterator.hasNext()) {
                iterator.next();
            }
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (isRowEmpty(currentRow)) {
                    break;
                }
                String testCaseId = getStringValue(currentRow.getCell(0));
                String testName = getStringValue(currentRow.getCell(1));
                if (testName.equalsIgnoreCase(testNameFilter)) {
                    String mobileNumber = getStringValue(currentRow.getCell(2));
                    String pin = getStringValue(currentRow.getCell(3));
                    String dob = getStringValue(currentRow.getCell(4));
                    String user = getStringValue(currentRow.getCell(5));
                    String password = getStringValue(currentRow.getCell(6));

                    testData.add(new ExcelModel(testCaseId, testName, mobileNumber, pin, dob, user, password));
                }
            }
            System.out.println("Size of filtered testData: " + testData.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testData;
    }

    private static String getStringValue(Cell cell) {
        ArrayList<String> cellValues = new ArrayList<>();
        String cellValue = "";
        if(cell == null){
            cellValues.add(cellValue);
        }
        if (cell == null) {
            return "";
        }else {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue().trim();
                case NUMERIC:
                    return String.valueOf((long) cell.getNumericCellValue()).trim();
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue()).trim();
                case FORMULA:
                    return cell.getCellFormula().trim();
                default:
                    return "";
            }
        }
    }
    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null && !cell.toString().trim().isEmpty()) {
                return false; // Return false if any cell is not empty
            }
        }
        return true;
    }


    }
