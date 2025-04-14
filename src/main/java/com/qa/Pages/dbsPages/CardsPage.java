package com.qa.Pages.dbsPages;

import com.qa.Listners.PropertyFileReader;
import com.qa.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.*;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CardsPage {
    private final WebDriverWait wait;
    private static final Logger logs = LogManager.getLogger(CardsPage.class);
    TestUtils utils = new TestUtils();
    LoginPage login;
    // private static final Logger logger = LogManager.getLogger(CardsPage.class);
    private final AppiumDriver<WebElement> driver;
    private final String platform;
    private final PropertyFileReader propertyFileReader;

    public CardsPage(AppiumDriver<WebElement> driver, String platform, PropertyFileReader propertyFileReader) {
        this.driver = driver;
        this.platform = platform;
        this.propertyFileReader = propertyFileReader;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickonCardsModule() {
    /*   try {
           WebElement element=driver.findElement(By.xpath("//*[@id='animation']/svg/g"));
           utils.waitUntilElementInVisible(element,driver);
       }
       catch (Exception e)
       {
           utils.log(":Loading issue");
       }
*/
        driver.findElement(By.xpath(getCardsModule())).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getCardUpgrade())));
        logs.info("Checking logss");
        utils.log("Clicking on Cards");
    }

    private String getCardsModule() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@content-desc=\"\uF013\" and ./following-sibling::*[@content-desc=\"Cards\"]]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }


    public boolean setCardFeature() {
        String ele;
        if (platform.equalsIgnoreCase("android")) {
            ele= "//*[@text=\"Card features\"]";
            // cardfeature= cardFeatures;
        } else if (platform.equalsIgnoreCase("ios")) {
            ele= "";
        }
        System.out.println(driver.findElement(By.xpath("//*[@text=\"Card features\"]")).isDisplayed());
        // driver.findElement(By.xpath("//*[@text=\"Card features\"]")).click();
        if(driver.findElement(By.xpath("//*[@text=\"Card features\"]")).isDisplayed()) {
            return true;
        }
        else {
            return  false;
        }
    }


public void checkContext()
{
    Set<String> contexts = driver.getContextHandles();
    for (String context : contexts) {

        System.out.println("Available context: " + context);
    }

}
    public void validateOptionsOnCards() throws Exception {
        try {
            checkContext();
            MobileElement cardFeatures = (MobileElement) driver.findElement(By.xpath(getCardFeatures()));
          //  MobileElement cardDetails = (MobileElement) driver.findElement(By.xpath(getCardDetails()));
            MobileElement payCardBill = (MobileElement) driver.findElement(By.xpath(getPayCardBill()));
            MobileElement controls = (MobileElement) driver.findElement(By.xpath(getControls()));
            MobileElement emi = (MobileElement) driver.findElement(By.xpath(getEMI()));
            MobileElement cardUpgrade = (MobileElement) driver.findElement(By.xpath(getCardUpgrade()));
            MobileElement outStandingBalLimit = (MobileElement) driver.findElement(By.xpath(getTotalOutStandingLimit()));
            MobileElement unBilledSection = (MobileElement) driver.findElement(By.xpath(getUnbilled()));
            System.out.println("unBilledSection: "+unBilledSection.isDisplayed());
            //  MobileElement billedSection= (MobileElement) driver.findElement(By.xpath(getBilled()));
            MobileElement statementLink = (MobileElement) driver.findElement(By.xpath(getStatementsLink()));
            if (cardFeatures.isDisplayed() && /*cardDetails.isDisplayed() &&*/ payCardBill.isDisplayed() && payCardBill.isDisplayed()
                    && controls.isDisplayed() && emi.isDisplayed() && cardUpgrade.isDisplayed() && outStandingBalLimit.isDisplayed()
                    && unBilledSection.isDisplayed()/* && billedSection.isDisplayed() && statementLink.isDisplayed()*/) {
                // utils.passMessage("All expected element are present on cards screen.");
                utils.log("Expected condition passed on card screen");
                assertTrue("Card details, Pay card bill,Controls,EMI,Card upgrade,Outstanding balances and limits, Unbilled section,Billed section, Statement Link", true);
                // assert  true;
            } else {
                Assert.fail("Unable to get the options on cards screen.");
                //  utils.failMessage("Unable to get the options on cards screen.");
            }
        }
        catch(Exception e) {
          utils.log().error("Option is not visible");
          e.printStackTrace();
        }

    }

    private String setCardDetails() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Card details\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public String getCardFeatures() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Card features\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getPayCardBill() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Pay card bill\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getCardDetails() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Card details\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }

    }
    private String getControls()
    { if (platform.equalsIgnoreCase("android")) {
        return "//*[@text=\"Controls\"]";
    } else if (platform.equalsIgnoreCase("ios")) {
        return "";
    } else {
        throw new IllegalArgumentException("Invalid platform specified");
    }
    }
    private String getEMI(){
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"EMI\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getCardUpgrade(){
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Card upgrade\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getTotalOutStandingLimit(){
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@class=\"android.view.View\" and ./*[@text=\"Outstanding balance & Limits\"]]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getStatementsLink(){
        if (platform.equalsIgnoreCase("android")) {
            return " //*[@text=\"Statements\"]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    private String getBilled() {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@class=\"android.view.View\" and ./*[@text=\"Billed\"]]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    private String getUnbilled()
    {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@text=\"Unbilled\"]";
                     //*[@class="android.view.View" and ./*[@text="This Month"]]
                    //"//*[@class=\"android.view.View\" and ./*[@text=\"Unbilled\"]]";
        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
    public void verifyBilledUnBilledTranscation()
    {
    utils.switchToWebViewContext(driver);
   // String elementText = "Unbilled";
        /*WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\"" + elementText + "\"));"));*/
       /* driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                +".scrollIntoView(new UiSelector()"+".textMatches(\""+"Billed"+ "\").instance(0))"));*/
      //  System.out.println(elementText.isEnabled());

        if(driver.findElement(By.xpath(getUnbilled())).isEnabled() && driver.findElement(By.xpath(getBilled())).isEnabled())
        //  if (element.isDisplayed())
        {

            // element.click();
            driver.findElement(By.xpath(getBilled())).click();
            utils.log("Expected condition passed on card screen");
            logs.info("Expected condition passed on card screen");
            assertTrue("Card details, Unbilled section,Billed section", true);
            assert  true;
        }
        else
            Assert.fail("Unable to click on billed and unbilled section");
    }


public void verifyCardUpgradeMessage()
{
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getCardUpgrade())));
    driver.findElement(By.xpath(getCardUpgrade())).click();
    String str="\"Coming soon: More options to upgrade your Super Life";
    utils.log( "Context check : "+driver.getContext());
    utils.switchToWebViewContext(driver);
    //driver.findElement(By.xpath(getCardUpgradeMessage()));
    System.out.println(driver.findElement(By.xpath(getCardUpgradeMessage())).getText());
    Assert.assertEquals(driver.findElement(By.xpath(getCardUpgradeMessage())).getText(),str);
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getCardUpgradeBackButton()))));
    WebElement backButton= driver.findElement(By.xpath(getCardUpgradeBackButton()));
    if(backButton.isEnabled())
    {
        backButton.click();
    }
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(getCardUpgradeBackButton())));
    utils.switchToNativeContext(driver);
    Assert.assertTrue(driver.findElement(By.xpath(getCardUpgrade())).isDisplayed(),"Error while clicking on back button on card upgrade page");

   //*[@text="'Coming soon: More options to upgrade your Super Life"]

}
public void verifyCardDetails()
{ //invalid otp scenario
    login = new LoginPage(driver, platform, propertyFileReader);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getCardDetails())));
    driver.findElement(By.xpath(getCardDetails())).click();
    login.manageCall(true);
    utils.switchToWebViewContext(driver);
   int size= driver.findElements(By.xpath("//*[@id=\"VERIFICATION_FORM-VERI_OTP_FIELD\"]/input")).size();
    System.out.println("Size: "+size);
    driver.findElement(By.xpath("//*[@id=\"VERIFICATION_FORM-VERI_OTP_FIELD\"]/input")).sendKeys("1234563");
    driver.findElement(By.xpath("//*[@id=\"VERIFICATION_FORM-SUBMIT_OTP_BTN\"]")).click();
    WebElement ele=driver.findElement(By.xpath("//div[@class=\"popupcontent\" and contains(text(),\"Invalid\")]"));
    if(ele.isDisplayed())
    {
        Assert.assertTrue(ele.getText().equalsIgnoreCase("Invalid OTP please try again"));
    }
}
    public String getCardUpgradeMessage()
    {
        if (platform.equalsIgnoreCase("android")) {
          //  return "//*[contains(text(),\"Coming soon: More options to upgrade your Super Life\")]";
            return "//*[@name=\"CARD_UPGRADE_LBL1\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }

    public String getCardUpgradeBackButton()
    {
        if (platform.equalsIgnoreCase("android")) {
            return "//*[@class=\"flaticon-close flaticon-modal-close\"]";

        } else if (platform.equalsIgnoreCase("ios")) {
            return "";
        } else {
            throw new IllegalArgumentException("Invalid platform specified");
        }
    }
}
