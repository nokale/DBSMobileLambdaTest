package com.qa.tests;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Pages.login;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class test1 extends BaseTest {
  /*  login log ;
    private JSONObject data;
    String propFileName = "src/main/resources/config.properties";
    PropertyFileReader propertyFileReader = new PropertyFileReader(propFileName);
    Set<String> devices = new HashSet<>(); // Use Set instead of List to avoid duplicates

    @BeforeClass
    public void beforeClass() throws Exception {
        InputStream datais = null;
        try {
            String dataFileName = "DataSheet/Cred.json";
            System.out.println("Data File Path: " + dataFileName);
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            if (datais == null) {
                System.err.println("Failed to load file: " + dataFileName);
                return;
            }
            JSONTokener tokener = new JSONTokener(datais);
            data = new JSONObject(tokener);
//            System.out.println("Loaded JSON data: " + data.toString(4));

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(datais != null) {
                datais.close();
            }
        }
        // To Avoid 3 iteration for Device value,version value and Platform value based on it will perform only once
            Set<String> allProperties = propertyFileReader.getProperties().stringPropertyNames();
            for (String propertyName : allProperties) {
                if (propertyName.startsWith("device")) {
                    String[] parts = propertyName.split("\\.");
                    if (parts.length >= 2) {
                        devices.add(parts[0]); // Add only the device name
                    }
                }
            }
    }

    @AfterClass
    public void afterClass() {
    }

    @Test()
    public void login() {
        for (String device : devices) {
            setUp(device);
            String platform = getPlatform(device);
            log = new login(getDriver(), platform, propertyFileReader);
            log.enterUsername(data.getJSONObject("validUser").getString("username"));
            log.enterPassword(data.getJSONObject("validUser").getString("password"));
            log.clickLoginButton();
            tearDown();
        }
    }*/

}
