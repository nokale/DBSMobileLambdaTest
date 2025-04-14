package com.qa.tests;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Pages.dbsPages.LoginPage;
import com.qa.Pages.dbsPages.PayAndTransferPage;
import com.qa.Utils.TestUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PayAndTransferTest extends BaseTest {
    LoginPage loginpage;
    TestUtils utils = new TestUtils();
    PayAndTransferPage paytransferpage;
    String propFileName = "src/main/resources/config.properties";
    PropertyFileReader propertyFileReader = new PropertyFileReader(propFileName);
    Set<String> devices = new HashSet<>();

    @BeforeClass
    public void beforeClass() throws IOException {
        Set<String> allProperties = propertyFileReader.getProperties().stringPropertyNames();
        for (String propertyName : allProperties) {
            if (propertyName.startsWith("device")) {
                String[] parts = propertyName.split("\\.");
                if (parts.length >= 2) {
                    devices.add(parts[0]);
                }
            }
        }
        String mobileNumber = propertyFileReader.getProperty("MobileNumber");
        //List<ExcelModel> filteredTestData = getFilteredTestData("signup");
        for (String device : devices) {
            setUp(device);
            String platform = getPlatform(device);
            loginpage = new LoginPage(getDriver(), platform, propertyFileReader);
            paytransferpage= new PayAndTransferPage(getDriver(), platform, propertyFileReader);
            loginpage.appLogin();
            paytransferpage.clickPayandTransfer();

        }
    }
    @AfterClass
    public void afterClass() {
        tearDown();
    }
    @BeforeMethod
    public void beforeTestMethod(ITestResult result)
    {
        utils.log("Executing method: "+result.getMethod().getMethodName());
    }

    @Test(priority = 1,alwaysRun = true)
    public void TC_MOB_155() {
        paytransferpage.verifyOptionsOnPayAndTransfer();
    }
    @Test(priority = 2,alwaysRun = true)
    public void TC_MOB_156() {
        paytransferpage.ValidateCarPaymentPage();
    }

    @Test(priority = 3,alwaysRun = true)
    public void TC_MOB_161() {
        paytransferpage.ValidateMerchantStandingInstruction();
    }

}
