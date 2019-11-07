package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EnterRegisterEmailPage;
import pages.FillRegisterFormPage;
import pages.LoginToAccountPage;
import setup.Setup;

public class LoginToAccount {

    LoginToAccountPage loginToAccountPage;
    EnterRegisterEmailPage enterRegisterEmailPage;
    FillRegisterFormPage fillRegisterFormPage;
    WebDriver driver;
    Wait wait;

    @BeforeTest
    void setup() {
        driver = Setup.driver;
        wait = Setup.wait;
        loginToAccountPage = new LoginToAccountPage(driver);
        enterRegisterEmailPage = new EnterRegisterEmailPage(driver);
        fillRegisterFormPage = new FillRegisterFormPage(driver);
    }
    @AfterMethod
    void clearFields(){
        loginToAccountPage.clearField();
    }
    @Test (priority = 1)
    void openLoginPage(){
        enterRegisterEmailPage.openLoginPage();
        Assert.assertTrue(enterRegisterEmailPage.isExpectedTitle("expectedTitle"));
    }
    @Test (priority = 2)
    void fillInvalidEmailAndValidPassword(){
        loginToAccountPage.fillEmailField("invalidEmail");
        loginToAccountPage.fillPasswordField("password");
        loginToAccountPage.enterSubmitButton();

        Assert.assertTrue(loginToAccountPage.isAlertAppear(wait, "invalidEmailMessage"));
    }
    @Test (priority = 3)
    void fillValidEmailWithoutPassword(){
        loginToAccountPage.fillEmailField("email");
        loginToAccountPage.enterSubmitButton();

        Assert.assertTrue(loginToAccountPage.isAlertAppear(wait, "emptyPasswordFieldMessage"));
    }
    @Test (priority = 4)
    void fillValidEmailAndInvalidPassword(){
        loginToAccountPage.fillEmailField("email");
        loginToAccountPage.fillPasswordField("invalidPassword");
        loginToAccountPage.enterSubmitButton();

        Assert.assertTrue(loginToAccountPage.isAlertAppear(wait, "invalidPasswordMessage"));
}
    @Test (priority = 5)
    void fillPasswordWithoutEmail(){
        loginToAccountPage.fillPasswordField("password");
        loginToAccountPage.enterSubmitButton();

        Assert.assertTrue(loginToAccountPage.isAlertAppear(wait, "emptyEmailFieldMessage"));
}
    @Test (priority = 6)
    void emptyEmailAndPasswordField(){
        loginToAccountPage.enterSubmitButton();

        Assert.assertTrue(loginToAccountPage.isAlertAppear(wait, "emptyEmailFieldMessage"));
    }
    @Test (priority = 7)
    void fillValidEmailAndValidPassword(){
        loginToAccountPage.fillEmailField("email");
        loginToAccountPage.fillPasswordField("password");
        loginToAccountPage.enterSubmitButton();

        fillRegisterFormPage.waitForNextPage(wait, "myAccountURL");

        Assert.assertTrue(fillRegisterFormPage.isLogoutButtonExist());
    }
}
