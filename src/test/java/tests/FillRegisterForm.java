package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.FillRegisterFormPage;
import setup.Setup;

public class FillRegisterForm {

    FillRegisterFormPage fillRegisterFormPage;
    WebDriver driver;
    Wait wait;

    @BeforeTest
    void setup() {
        driver = Setup.driver;
        wait = Setup.wait;
        fillRegisterFormPage = new FillRegisterFormPage(driver);
    }
    @AfterMethod
    void resetForm(){
        fillRegisterFormPage.resetForm(wait);
    }
    @Test (priority = 1)
    void fillFormWithoutName(){

        fillRegisterFormPage.
                fillForm
                        ("empty", "lastName", "", "password", "address", "city", "state", "zipCode", "country", "mobile");


        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("firstNameAlert"));
    }
    @Test (priority = 2)
    void fillFormWithoutLastName(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "empty",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("lastNameAlert"));
    }
    @Test (priority = 3)
    void fillFormWithoutEmail(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "empty",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");


        fillRegisterFormPage.clickSubmitButton();
        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("emailAlert"));
    }
    @Test (priority = 4)
    void fillFormWithExistingEmail(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "existingEmailAddress",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("existingEmailAlert"));
    }
    @Test (priority = 5)
    void fillFormWithoutPassword(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "empty",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("passwordAlert"));
    }
    @Test (priority = 6)
    void fillFormWithTooShortPassword(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "shortPassword",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("shortPasswordAlert"));
    }
    @Test (priority = 7)
    void fillFormWithoutAddress(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "empty",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("addressAlert"));
    }
    @Test (priority = 8)
    void fillFormWithoutCity(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "empty",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("cityAlert"));
    }
    @Test (priority = 9)
    void fillFormWithoutState(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "empty",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("stateAlert"));
    }
    @Test (priority = 10)
    void fillFormWithoutZipCode(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "empty",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("zipCodeAlert"));
    }
    @Test (priority = 11)
    void fillFormWithInvalidZipCode(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "invalidZipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("invalidZipCodeAlert"));
    }
    @Test (priority = 12)
    void fillFormWithoutCountry(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "emptyCountry",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("countryAlert"));
        Assert.assertTrue(fillRegisterFormPage.isStateFieldExist());
    }
    @Test (priority = 13)
    void fillFormWithoutMobilePhone(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "empty");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("mobileAlert"));
    }
    @Test (priority = 14)
    void fillFormWithInvalidPhoneMobile(){

        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "invalidMobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForAuthentication(wait);

        Assert.assertTrue(fillRegisterFormPage.isAlertCorrectly("invalidMobileAlert"));
    }
    @Test (priority = 15)
    void checkAutocompleteOfFirstAndLastNameField(){
        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        Assert.assertTrue(fillRegisterFormPage.checkAutocompleteOfFields("firstname", "lastName"));
    }
    @Test (priority = 16)
    void fillFormCorrectly(){
        fillRegisterFormPage.
                fillForm(
                        "firstname",
                        "lastName",
                        "",
                        "password",
                        "address",
                        "city",
                        "state",
                        "zipCode",
                        "country",
                        "mobile");

        fillRegisterFormPage.clickSubmitButton();

        fillRegisterFormPage.waitForNextPage(wait, "myAccountURL");

        Assert.assertTrue(fillRegisterFormPage.isLogoutButtonExist());

    }
}
