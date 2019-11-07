package pages;

import jsons.JsonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class FillRegisterFormPage{

    WebDriver driver;
    JsonAPI json = new JsonAPI("CreateAccountJSON.json");
    EnterRegisterEmailPage enterRegisterEmailPage;

    public FillRegisterFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        enterRegisterEmailPage = new EnterRegisterEmailPage(driver);
    }

    @FindBy(id = "customer_firstname")
    WebElement firstNameField;

    @FindBy(id = "customer_lastname")
    WebElement lastNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "address1")
    WebElement addressField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "id_state")
    WebElement stateField;

    @FindBy(id = "postcode")
    WebElement zipCodeField;

    @FindBy(id = "id_country")
    WebElement countryField;

    @FindBy(id = "phone_mobile")
    WebElement mobilePhoneField;

    @FindBy(name = "submitAccount")
    WebElement registerButton;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div")
    WebElement alertMessage;

    @FindBy (name = "firstname")
    WebElement autocompleteFirstName;

    @FindBy (name = "lastname")
    WebElement autocompleteLastName;

    @FindBy (className = "logout")
    WebElement logOutButton;

    public void fillForm(String firstName, String lastName, String email, String password, String address, String city, String state, String zipCode, String country, String mobile){

        firstNameField.sendKeys(json.getFromJSON(firstName));

        lastNameField.sendKeys(json.getFromJSON(lastName));

        if(email.length() != 0){
            emailField.clear();
            emailField.sendKeys(json.getFromJSON(email));
        }

        passwordField.sendKeys(json.getFromJSON(password));

        addressField.sendKeys(json.getFromJSON(address));

        cityField.sendKeys(json.getFromJSON(city));

        stateField.sendKeys(json.getFromJSON(state));

        zipCodeField.sendKeys(json.getFromJSON(zipCode));

        countryField.sendKeys(json.getFromJSON(country));

        mobilePhoneField.sendKeys(json.getFromJSON(mobile));
    }
    public void clickSubmitButton() {
        registerButton.click();
    }

    //zastanowic sie czy metody ponizej nie wpleść w isAlertCorrectly
    public void waitForAuthentication(Wait wait) {
        wait.until(ExpectedConditions.urlToBe(json.getFromJSON("authenticationURL")));
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
    }
    public void waitForNextPage(Wait wait, String expectedURL){
        wait.until(ExpectedConditions.urlToBe(json.getFromJSON(expectedURL)));
    }
    public boolean isAlertCorrectly(String alert){
        if(alertMessage.getText().contains(json.getFromJSON(alert))){
            return true;
        }else
            return false;
    }
    public boolean isStateFieldExist(){
        return stateField.getSize().height == 0;
    }

    public void resetForm(Wait wait){
        enterRegisterEmailPage.openLoginPage();
        enterRegisterEmailPage.sendEmailAddress("newEmailAddress");
        enterRegisterEmailPage.clickCreateAnAccount();
        wait.until(ExpectedConditions.urlToBe(json.getFromJSON("expectedFormURL")));
    }
    public boolean checkAutocompleteOfFields(String firstName, String lastName){
        String firstNameFromJson = json.getFromJSON(firstName);
        String lastNameFromJson = json.getFromJSON(lastName);

        String textFromAutocompleteFirstName = autocompleteFirstName.getAttribute("value");
        String textFromAutocompleteLastName = autocompleteLastName.getAttribute("value");

        if(firstNameFromJson.equals(textFromAutocompleteFirstName) && lastNameFromJson.equals(textFromAutocompleteLastName))
            return true;
        else
            return false;
    }
    public boolean isLogoutButtonExist (){
        String textFromLogoutbutton = json.getFromJSON("logOutText");
        if (logOutButton.getText().equals(textFromLogoutbutton))
            return true;
        else
            return false;
    }

}