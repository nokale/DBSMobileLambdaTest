package com.qa.tests;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Pages.dbsPages.CardsPage;
import com.qa.Pages.dbsPages.LoginPage;
import com.qa.Utils.ExcelModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class login extends BaseTest {

    LoginPage loginpage;
    CardsPage cardPage;
    String propFileName = "src/main/resources/config.properties";
    PropertyFileReader propertyFileReader = new PropertyFileReader(propFileName);
    Set<String> devices = new HashSet<>();

    @BeforeClass
    public void beforeClass() {
        Set<String> allProperties = propertyFileReader.getProperties().stringPropertyNames();
        for (String propertyName : allProperties) {
            if (propertyName.startsWith("device")) {
                String[] parts = propertyName.split("\\.");
                if (parts.length >= 2) {
                    devices.add(parts[0]);
                }
            }
        }
    }


    @AfterClass
    public void afterClass() {
        tearDown();
    }

    @Test
    public void loginScreen() throws Exception {
        String mobileNumber=propertyFileReader.getProperty("MobileNumber");
        //List<ExcelModel> filteredTestData = getFilteredTestData("signup");
        for (String device : devices) {
            setUp(device);
            String platform = getPlatform(device);
            loginpage = new LoginPage(getDriver(), platform, propertyFileReader);
            cardPage = new CardsPage(getDriver(), platform, propertyFileReader);
            loginpage.appLogin();
            cardPage.clickonCardsModule();
            cardPage.validateOptionsOnCards();

        }
    }

}
