package pages;

import jsons.JsonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EnterRegisterEmailPage {
    WebDriver driver;
    JsonAPI json = new JsonAPI("CreateAccountJSON.json");

    public EnterRegisterEmailPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (name = "email_create")
    WebElement emailField;

    @FindBy (name = "SubmitCreate")
    WebElement submitButton;

    @FindBy (id = "create_account_error")
    public WebElement messageError;

    @FindBy (className = "login")
    public WebElement loginPageButton;

    public void sendEmailAddress(String emailAddress) {
        emailField.sendKeys(json.getFromJSON(emailAddress));
    }

    public void sendInvalidEmailAddress(Wait wait){

        ArrayList<String> invalidEmailAddressArray = json.getInvalidEmailAddress();

        for (String invalidEmail : invalidEmailAddressArray){
            emailField.sendKeys(invalidEmail);
            submitButton.click();

            try{
                wait.until(ExpectedConditions.visibilityOf(messageError));
            }catch (NoSuchElementException e){
                break;
            }
            clearEmailField();
        }
    }
    public void clickCreateAnAccount() {
        submitButton.click();
    }
    public void clearEmailField() {
        emailField.clear();
    }
    public boolean isAlertExist(Wait wait) {
        wait.until(ExpectedConditions.visibilityOf(messageError));
        return messageError.getSize().height > 0;
    }
    public void openLoginPage(){
        loginPageButton.click();
    }
    public boolean isExpectedTitle(String expectedTitle){

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(json.getFromJSON(expectedTitle)))
            return true;
        else
            return false;
    }

    public void waitForExpectedURL(Wait wait, String expectedURL){
        wait.until(ExpectedConditions.urlToBe(json.getFromJSON(expectedURL)));
    }

    public boolean isExpectedURL(String expectedURL){
        String actualURL = driver.getCurrentUrl();
        if(actualURL.equals(json.getFromJSON(expectedURL)))
            return true;
        else
            return false;
    }

    }

