package com.qa.Pages.dbsPages;

import com.qa.Listners.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class homePage  {
   public static void main(String[] args)
   {
       List<String> l=new ArrayList<String>();
       l.add("1");
       l.add(0,"2");
   }
    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public homePage(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
    }

    public void waitFor(int seconds) {
        try{
            Thread.sleep(seconds);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void homeButton() {
        driver.findElement(By.xpath(getHomePage())).click();
    }

    private String getHomePage() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@class=\"android.view.View\" and ./*[@content-desc=\"Home\"]]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void backButton() {
        driver.findElement(By.xpath(getBackPage())).click();
    }

    private String getBackPage() {
        if (platform.equalsIgnoreCase("android")) {
            return "";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }


    public void userName() {
        driver.findElement(By.xpath(getUserName())).click();
    }

    private String getUserName() {
        if (platform.equalsIgnoreCase("android")) {
            return "";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void userProfile() {
        driver.findElement(By.xpath(getUserProfile())).click();
    }

    private String getUserProfile() {
        if (platform.equalsIgnoreCase("android")) {
            return "";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public void notification() {
        driver.findElement(By.xpath(getNotification())).click();
    }

    private String getNotification() {
        if (platform.equalsIgnoreCase("android")) {
            return "";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
}
