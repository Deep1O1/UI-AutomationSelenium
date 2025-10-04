package BaseUtility;
import ReusableUtility.configReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import DriverManager.WebDriverInstanceSetup;

public class BaseTest {
    
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : configReader.getProperty("browser");
        driver = WebDriverInstanceSetup.getDriverInstance(browserName);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterMethod
    public void tearDown(){
        WebDriverInstanceSetup.removeWebDriver();
    }


}
