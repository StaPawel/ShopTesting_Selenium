package shopTestPack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetupData {
    SetupJson dataFromJson = new SetupJson();
    WebDriver driver;
    WebDriverWait wait;

    void setup() {
        String driverPath = dataFromJson.getDriverPath();
        String urlPath = dataFromJson.getUrlPath();
        String webDriver = dataFromJson.getWebDriver();
        System.setProperty(webDriver, driverPath);
        driver = new ChromeDriver();
       driver.get(urlPath);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
    }
    //metody ponizej przeniesc do klasy CreateAccountPage
    void sendEmailAddress(String emailAddress) {
        driver.findElement(By.name("email_create")).sendKeys(emailAddress);
    }
    void clickCreateAnAccount() {
        driver.findElement(By.name("SubmitCreate")).click();
    }
    void clearEmailField() {
        driver.findElement(By.name("email_create")).clear();
    }
    boolean isAlertExist() {
        return driver.findElements(By.id("create_account_error")).size() > 0;
    }
    void openLoginPage() {
        driver.findElement(By.className("login")).click();
    }
}
