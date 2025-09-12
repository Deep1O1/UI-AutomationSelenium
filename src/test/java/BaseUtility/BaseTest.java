package BaseUtility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import DriverManager.WebDriverInstanceSetup;
import org.testng.annotations.Parameters;
import java.net.MalformedURLException;

public class BaseTest {
    
    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browserName","browserVersion","platform"})
    public void setUp( String browserName,  String browserVersion,  String platform) throws MalformedURLException {
        driver = WebDriverInstanceSetup.getWebDriverInstance(browserName,browserVersion,platform);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterMethod
    public void tearDown(){
        WebDriverInstanceSetup.removeWebDriver();
    }
    
}
