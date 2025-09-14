package LambdaTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import DriverManager.WebDriverInstanceSetup;

public class BaseTest {
    
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverInstanceSetup.getDriverInstance("Chrome");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterMethod
    public void tearDown(){
        WebDriverInstanceSetup.removeWebDriver();
    }
    
}
