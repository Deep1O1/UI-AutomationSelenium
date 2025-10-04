package MakeMyTrip;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import UtilityManager.WebDriverInstanceSetup;

public class BaseTestMMT {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverInstanceSetup.getDriverInstance("Chrome");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get("https://www.makemytrip.com/");
    }

    @AfterMethod
    public void tearDown(){
        WebDriverInstanceSetup.removeWebDriver();
    }
}
