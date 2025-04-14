package com.qa.tests;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Pages.login;
import com.qa.Utils.ExcelModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class testDatawithExcel extends BaseTest {/*
    login login;
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

    }

    @Test
    public void loginWithFilteredData() {
        List<ExcelModel> filteredTestData = getFilteredTestData("res");
        for (String device : devices) {
            setUp(device);
            String platform = getPlatform(device);
            login = new login(getDriver(), platform, propertyFileReader);
            for (ExcelModel data : filteredTestData) {
                login.enterUsername(data.getUser());
                login.enterPassword(data.getPassword());
                login.clickLoginButton();
            }
            tearDown();
        }
    }
*/
}
