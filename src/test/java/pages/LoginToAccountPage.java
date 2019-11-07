package pages;

import jsons.JsonAPI;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class LoginToAccountPage {

        WebDriver driver;
        JsonAPI json = new JsonAPI("loginToAccount.json");

        public LoginToAccountPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
        @FindBy (name = "email")
        WebElement emailField;

        @FindBy (name = "passwd")
        WebElement passwordField;

        @FindBy (name = "SubmitLogin")
        WebElement submitButton;

        @FindBy (xpath = "//*[@id=\"center_column\"]/div[1]")
        WebElement alertMessage;

        public void fillEmailField(String email){
            emailField.sendKeys(json.getFromJSON(email));
        }
        public void fillPasswordField(String password){
            passwordField.sendKeys(json.getFromJSON(password));
        }
        public void enterSubmitButton(){
            submitButton.click();
        }
        public boolean isAlertAppear(Wait wait, String alert){
            wait.until(ExpectedConditions.visibilityOf(alertMessage));
            if(alertMessage.getText().contains(json.getFromJSON(alert))){
                return true;
            }else
                return false;
        }
        public void clearField(){

            try{
                emailField.clear();
                passwordField.clear();
            }catch (NoSuchElementException e){
                System.out.println("clearField() -->  NO SUCH ELEMENT EXCEPTION");
            }

        }
    }
