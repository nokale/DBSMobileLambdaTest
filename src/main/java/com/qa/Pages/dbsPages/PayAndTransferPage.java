package com.qa.Pages.dbsPages;

import com.qa.BaseTest;
import com.qa.Listners.PropertyFileReader;
import com.qa.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PayAndTransferPage extends BaseTest {
       TestUtils utils = new TestUtils();
    private final WebDriverWait wait;
    private static final Logger logs = LogManager.getLogger(PayAndTransferPage.class);
    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public PayAndTransferPage(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
        this.wait = new WebDriverWait(driver, 30);
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

    public void clickPayandTransfer()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getPayandTransfer())));
        driver.findElement(By.xpath(getPayandTransfer())).click();
       // utils.waitFor(6000);
        logs.info("Checking logss");
        utils.log("Clicking on Pay and Transfer");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getCardBillPayment())));
       /* WebElement ele=driver.findElement(By.xpath("//*[@class=\"android.view.View\" and ./parent::*[@text=\"Pay & Transfer Card Bill Payment Scan & Pay Merchant Standing Instruction\"]]/*[2]/*[1]/*[@class=\"android.view.View\"]/*[@class=\"android.view.View\"]/*[@class=\"android.widget.Button\"]"));
        String str= ele.getText();                               //*[@class="android.view.View" and ./*[@content-desc="Pay & Transfer"]]
        System.out.println("verifying locatin on pay:"+str);
        ele.click();
      WebElement webtext= driver.findElement(By.xpath("//button[@id=\"PAY_TRANSFER_FORM-PAY_TRANSFER_CARD_PAY_BTN\"]"));
        System.out.println(webtext.getText());
        System.out.println("Attribute"+webtext.getAttribute("name"));
        webtext.click();*/

    }
    public void verifyOptionsOnPayAndTransfer()
    {
        Map<By, String> elementsToValidate = new HashMap<>();
        elementsToValidate.put(By.xpath(getCardBillPayment()), "Card Bill Payment");
        elementsToValidate.put(By.xpath(getScanAndPay()), "Scan & Pay");
        elementsToValidate.put(By.xpath(getMerchantStandingInst()), "Merchant Standing Instruction");
        for (Map.Entry<By, String> entry : elementsToValidate.entrySet()) {
            By locator = entry.getKey();
            String expectedText = entry.getValue();

            try {
              WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                String actualText = element.getText();
                Assert.assertEquals(actualText, expectedText, "Text validation failed for element with locator: " + locator+" Actual text: " + actualText+ " Expected: " + expectedText);
                utils.log("Validation passed for element with locator: " + locator + " Actual text: " + actualText+ " Expected: " +expectedText);

            } catch (Exception e) {
                System.err.println("Exception occurred while validating element with locator: " + locator);
                e.printStackTrace();
                Assert.fail("Validation failed for element with locator: " + locator);
            }
        }
    }
    public void checkContext()
    {
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {

            System.out.println("Available context: " + context);
            if (context.equalsIgnoreCase("WEBVIEW_com.dbs.in.cbcards"))
            {
                driver.context(context);
            }
        }

    }
    public void ValidateCarPaymentPage()
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(getCardBillPayment()))));
            driver.findElement(By.xpath(getCardBillPayment())).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getCardBillPayment())));
            utils.waitFor(2000);
            utils.switchToWebViewContext(driver);
            WebElement elementtoBechecked = driver.findElement(By.xpath(getMakePayment()));
            Assert.assertTrue(elementtoBechecked.isDisplayed(), "Unable to find the navigate to Make Payment page");
            utils.log("Passed-Able to navigate to Pay card Bill option");
            driver.findElement(By.xpath("//*[@class=\"flaticon-close flaticon-modal-close\"]")).click();
            utils.waitFor(2000);
        }
        catch (Exception e) {
           // driver.findElement(By.xpath("//*[@class=\"flaticon-close flaticon-modal-close\"]")).click();
            e.printStackTrace();
        }



    }
    public void ValidateMerchantStandingInstruction()
    {
        utils.switchToNativeContext(driver);
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(getMerchantStandingInst()))));
       driver.findElement(By.xpath(getMerchantStandingInst())).click();
       try {
            Thread.sleep(8000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("current context: "+driver.getContext());
        utils.waitFor(3000);
        //utils.fetchAllContext(driver);
        utils.waitFor(3000);
        utils.switchToChromeWebView(driver);
        // wait.until(ExpectedConditions.urlToBe("https://www.sihub.in/managesi/dbs/#/home/landing"));
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("//a[text()=\"Continue\"]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("sihub"),"Unable to navigate to sihub website");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name=\"email\"]")).isDisplayed(),"username filed is not present");
        Assert.assertTrue( driver.findElement(By.xpath("//input[@name=\"user_password\"]")).isDisplayed(),"password field is not present");
        driver.quit();
    }

       public void waitForNetworkCallsToComplete() {
              wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript(
                "return window.performance.getEntriesByType('resource').filter(e => e.initiatorType === 'xmlhttprequest').length === 0"
        ));
    }


    private String getMerchantStandingInst() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Merchant Standing Instruction\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String getPayandTransfer() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@content-desc=\"\uF013\" and ./following-sibling::*[@content-desc=\"Pay & Transfer\"]]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String getMakePayment()
    {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@name=\"PAY_CARD_MAK_PMENT_BTN\"]";
           // return "//*[text()=\"Make Payment\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getCardBillPayment() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Card Bill Payment\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getScanAndPay() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Scan & Pay\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    //*[@text="Card Bill Payment"]
}
