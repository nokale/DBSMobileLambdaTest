package com.qa.Pages.dbsPages;

import com.qa.Listners.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class morePage {

    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public morePage(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
    }

    public void signIn() {
        driver.findElement(By.xpath(signInButton())).click();
    }

    private String signInButton() {
        if (platform.equalsIgnoreCase("android")) {
            return "";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
}
