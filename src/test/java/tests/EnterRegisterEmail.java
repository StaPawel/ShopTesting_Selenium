package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EnterRegisterEmailPage;
import setup.Setup;

public class EnterRegisterEmail{
    WebDriver driver;
    Wait wait;
    EnterRegisterEmailPage enterRegisterEmailPage;

    @BeforeTest
    public void setup(){
        this.driver = Setup.driver;
        this.wait = Setup.wait;
        enterRegisterEmailPage = new EnterRegisterEmailPage(driver);

    }
    @Test (priority = 0)
    void openLoginPage(){
        enterRegisterEmailPage.openLoginPage();
        Assert.assertTrue(enterRegisterEmailPage.isExpectedTitle("expectedTitle"));
    }
    @Test (priority = 1)
    void setInvalidEmailAddress(){
        enterRegisterEmailPage.sendInvalidEmailAddress(wait);
        Assert.assertTrue(enterRegisterEmailPage.isAlertExist(wait));
    }
    @Test (priority = 2)
    void setExistingEmailAddress(){

        enterRegisterEmailPage.sendEmailAddress("existingEmailAddress");
        enterRegisterEmailPage.clickCreateAnAccount();

        Assert.assertTrue(enterRegisterEmailPage.isAlertExist(wait));
    }
    @Test (priority = 3)
    void setNewEmailAddress(){

        enterRegisterEmailPage.clearEmailField();
        enterRegisterEmailPage.sendEmailAddress("newEmailAddress");
        enterRegisterEmailPage.clickCreateAnAccount();
        enterRegisterEmailPage.waitForExpectedURL(wait, "expectedFormURL");

        Assert.assertTrue(enterRegisterEmailPage.isExpectedURL("expectedFormURL"));
    }
}
