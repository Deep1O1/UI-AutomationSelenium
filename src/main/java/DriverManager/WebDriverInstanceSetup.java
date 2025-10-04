package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverInstanceSetup {
    
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverInstanceSetup() {

    }

    public static WebDriver getDriverInstance(String browserName) {
        if (driver.get() == null) {
            switch (browserName) {
                case "Chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver.set(new ChromeDriver(options));
                }
                case "Edge" -> {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    driver.set(new EdgeDriver(edgeOptions));
                }
                case "FireFox" -> {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                    driver.set(new FirefoxDriver(firefoxOptions));
                }
                default -> throw new IllegalArgumentException("Invalid browser option chosen");
            }
        }
        return driver.get();
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    
    public static void removeWebDriver(){
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
