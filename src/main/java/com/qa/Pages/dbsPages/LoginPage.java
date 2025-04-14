package com.qa.Pages.dbsPages;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import okio.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.Set;

public class LoginPage extends BaseTest {
   // private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private final WebDriverWait wait;
    TestUtils utils = new TestUtils();
    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public LoginPage(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
        this.wait = new WebDriverWait(driver, 10);
    }


    public void preciseLocation() {
        WebElement location = driver.findElement(By.xpath(setPreciseLocation()));
        if (location.isEnabled()) {
            location.click();
        } else {
            utils.log().info("Precise location is not present");
             }
    }

    public boolean isPreciseLocationPresent() {
        try {
            WebElement element = driver.findElement(By.xpath(setPreciseLocation()));
            return element != null;
            //return driver.findElement(By.xpath(setPreciseLocation()));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String setPreciseLocation() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_location_accuracy_radio_fine\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void approximateLocation() {
        driver.findElement(By.xpath(setApproximateLocation())).click();
    }

    public boolean isApproximateLocationPresent() {
        try {
            return driver.findElement(By.xpath(setApproximateLocation())) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String setApproximateLocation() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_location_accuracy_radio_coarse\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String accessDeviceLocation() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void oneTimeRun() {
        WebElement ele = driver.findElement(By.xpath(locationOneTimeRun()));
        if (ele.isDisplayed()) {
            driver.findElement(By.xpath(locationOneTimeRun())).click();
        }
    }

    private String locationOneTimeRun() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_allow_one_time_button\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void noLocation() {
        driver.findElement(By.xpath(locationPermissionDenied())).click();
    }

    private String locationPermissionDenied() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void accountSelection() throws IOException {
        if (driver.findElement(By.xpath(selectAccount())).isDisplayed()) {
            driver.findElement(By.xpath(selectAccount())).click();
            utils.log("Selecting account by mobile number");
            wait.until(ExpectedConditions.elementToBeClickable( driver.findElement(By.xpath(selectAccount()))));
            utils.waitFor(1000);
        } else {
            Assert.fail("Mobile number is not selected.");
        }

    }

    private String selectAccount() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Select\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void user(String phoneNumber) {
        WebElement phonenumberDropdown= driver.findElement(By.xpath(selectUser(phoneNumber)));
       phonenumberDropdown.click();

       wait.until(ExpectedConditions.elementToBeClickable(By.xpath(allowDailer())));
       utils.waitFor(2000);
      /* try {
           driver.context("WEBVIEW_com.dbs.in.cbcards");
           WebElement shadowHost = driver.findElement(By.cssSelector("#lottie-play"));
           // Use JavaScriptExecutor to interact with shadow DOM
           JavascriptExecutor js = (JavascriptExecutor) driver;
           // Check if the element has a shadow root
           Boolean isShadowHost = (Boolean) js.executeScript("return arguments[0].shadowRoot !== null", shadowHost);
           // Print the result
           if (isShadowHost) {
               System.out.println("The element is a shadow host.");
               // Access the shadow root
               WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);

               // Locate a shadow element within the shadow root
               WebElement shadowElement = shadowRoot.findElement(By.xpath("//*[@id=\"animation\"]/svg/g/g[2]/g"));
               wait.until(ExpectedConditions.visibilityOf(shadowElement));
              } else {
               System.out.println("The element is not a shadow host.");
           }  }
       catch (Exception e)
       {
           e.printStackTrace();
       }*/
        utils.log("Selecting phone number from list");
          }

    private String selectUser(String phoneNumber) {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text='" + phoneNumber + "']";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }



    public void manageCall(boolean allow) {
        utils.waitFor(2000);
           String xpath = allow ? allowDailer() : denyDailer();
        try {
            boolean isPresent = wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)),
                    ExpectedConditions.elementToBeClickable(By.xpath(xpath))));
            if (isPresent) {
                WebElement element = driver.findElement(By.xpath(xpath));
                if (allow) {
                    Assert.assertTrue(element.isDisplayed(), "Manage call popup is not present");
                    element.click();
                    utils.log("Allowed managing calls popup");
                } else {
                    element.click();
                    utils.log("Denied managing calls popup");
                }
            } else {
                utils.log().error ("Element is not present on the screen.");
            }
        } catch (Exception e) {
            utils.log() .error("Failed to manage calls: ", e);
            throw e;
        }
    }

    private String allowDailer() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String denyDailer() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void acceptDeviceLocation() {
        if (driver.findElement(By.xpath(accessDeviceLocation())).isEnabled()) {
            driver.findElement(By.xpath(accessDeviceLocation())).click();
          //  System.out.println(driver.findElement(By.xpath(accessDeviceLocation())).isDisplayed());
            //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(accessDeviceLocation()))));
            utils.waitFor(5000);
            utils.log().info("Access device location");
        } else {
            utils.log().warn("Device location popup not present.");

        }

    }

    public void appLogin() throws IOException {
        String mob = propertyFileReader.getProperty("MobileNumber");
      /* if (isPreciseLocationPresent()) {
            preciseLocation();
            //  loginpage.acceptDeviceLocation();
            oneTimeRun();
        } else {
            acceptDeviceLocation();
        }*/
        acceptDeviceLocation();
        accountSelection();
        user(mob);
       // implicitlyWait();
        //loginpage.manageCalls(true);
        manageCall(true);


    }
}
