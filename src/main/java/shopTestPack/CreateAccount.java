/*
package shopTestPack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccount {
    WebDriver driver;
    WebDriverWait wait;
    SetupData ob = new SetupData();


    CreateAccount(){
        ob.setup();
        this.driver = ob.driver;
        this.wait = ob.wait;

}
    @BeforeTest
    void openLoginPage(){
        String expectedTitle = "Login - My Store";
        ob.openLoginPage();
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @Test (priority = 2)
    void setInvalidEmailAddress(){
        //utworzyc zewnętrzny plik z danymi testowymi lub podpiąć je do bazy danych
        String [] invalidAddress = {
                "example@email@com",
                "example.email@com",
                "example@email",
                "example.com",
                "example@email.com;",
                "example@@email.com",
                "true",
                "false",
                "null",
                ";",
                "@.com",
                ""
        };
        for (String actualAddress : invalidAddress){
            ob.sendEmailAddress(actualAddress);
            ob.clickCreateAnAccount();
            try{
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("create_account_error"))));
            }catch (Exception ex){
                System.out.println("Invalid address email: '" + actualAddress + "' was approved");
                Assert.assertFalse(ob.isAlertExist());
                break;
            }
            ob.clearEmailField();
        }
        Assert.assertTrue(ob.isAlertExist());
    }
    @Test (priority = 3)
    void setExistingEmailAddress(){
        String existingEmailAddress = "sample@test.com";
        ob.openLoginPage();
        ob.sendEmailAddress(existingEmailAddress);
        ob.clickCreateAnAccount();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("create_account_error"))));
        Assert.assertTrue(ob.isAlertExist());
    }
    @Test (priority = 4)
    void setNewEmailAddress(){
        String newEmailAddress = "pawel@seleniumtest.com.pl";
        ob.openLoginPage();
        ob.sendEmailAddress(newEmailAddress);
        ob.clickCreateAnAccount();



    }
}
*/
