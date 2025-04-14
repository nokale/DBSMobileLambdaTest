package com.qa.Pages;

import com.qa.Listners.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class login {
    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public login(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
    }

    public void enterUsername(String username) {
        driver.findElement(By.xpath(getUserName())).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(By.xpath(getPassword())).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath(getLoginButtonXPath())).click();
    }

    private String getUserName() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@content-desc=\"test-Username\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "//*[@name=\"test-Username\"]";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String getPassword() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@content-desc=\"test-Password\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "//*[@name=\"test-Password\"]";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String getLoginButtonXPath() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@content-desc=\"test-LOGIN\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "//*[@name=\"test-LOGIN\"]";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

}
