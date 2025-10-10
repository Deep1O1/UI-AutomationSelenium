package BaseUtility;

import ReusableUtility.configReader;
import UtilityManager.WebDriverInstanceSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    
    protected WebDriver driver;

    //@Parameters({"browser"})
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




    public String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null");
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String directory = "Test-Reports/screenshots";
        String fileName = testName + "_" + timestamp + ".png";
        String path = directory + "/" + fileName;

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshotsDir = new File(directory);
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "./screenshots/" + fileName;
    }

    public void cleanScreenshotFolder(){
        String directory = "Test-Reports/screenshots";
        Path screenshotDir = Paths.get(directory);

        try {
            if (Files.exists(screenshotDir)) {
                Files.walk(screenshotDir)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete: " + path);
                            }
                        });
            } else {
                Files.createDirectories(screenshotDir);
                System.out.println("Screenshot folder created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
