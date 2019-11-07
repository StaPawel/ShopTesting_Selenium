package setup;

import jsons.JsonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class Setup {

    JsonAPI json = new JsonAPI("setup.json");

    static public WebDriver driver;
    static public Wait wait;

    String driverPath = json.getFromJSON("driverPath");
    String urlPath = json.getFromJSON("urlPath");
    String webDriver = json.getFromJSON("webDriver");

    @BeforeSuite
    public void setup() {
        System.setProperty(webDriver, driverPath);
        driver = new ChromeDriver();
        driver.get(urlPath);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 7);
    }
}
