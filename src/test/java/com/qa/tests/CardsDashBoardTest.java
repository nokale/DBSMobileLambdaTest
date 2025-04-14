package com.qa.tests;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Pages.dbsPages.CardsPage;
import com.qa.Pages.dbsPages.LoginPage;
import com.qa.Pages.dbsPages.PayAndTransferPage;
import com.qa.Utils.TestUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CardsDashBoardTest extends BaseTest {
    TestUtils utils = new TestUtils();
    LoginPage loginpage;
    CardsPage cardPage;
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
       //List<ExcelModel> filteredTestData = getFilteredTestData("signup");
        for (String device : devices) {
            setUp(device);
            String platform = getPlatform(device);
            loginpage = new LoginPage(getDriver(), platform, propertyFileReader);
            cardPage = new CardsPage(getDriver(), platform, propertyFileReader);
            paytransferpage= new PayAndTransferPage(getDriver(), platform, propertyFileReader);
            loginpage.appLogin();
            cardPage.clickonCardsModule();
        }
    }

    @BeforeMethod
    public void beforeTestMethod(ITestResult result) {
        utils.log("Executing method: "+result.getMethod().getMethodName());
    }

    @AfterClass
    public void afterClass() {
        tearDown();
    }

     @Test(priority = 2,testName = "Vantage Card Test",enabled = false)
     public void TC_MOB_59() throws Exception {
        //List<ExcelModel> filteredTestData = getFilteredTestData("signup");
         for (String device : devices) {
            cardPage.validateOptionsOnCards();
         }
     }
     @Test(priority = 3,enabled = false)
     public void TC_MOB_132()
     {
         //need to check with Debo unable to perform scrolling
         cardPage.verifyBilledUnBilledTranscation();
   }
//card module upgrade option
    @Test(testName = "Card Upgrade", priority = 1,enabled = false)
    public void TC_MOB_128() {
            cardPage.verifyCardUpgradeMessage();
    }
    @Test(testName = "Vantage Card Test")
    public void TC_MOB_69() {
        cardPage.verifyCardDetails();
    }


}
