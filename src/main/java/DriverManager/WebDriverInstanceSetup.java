package DriverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import ReusableUtility.configReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverInstanceSetup {
    
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverInstanceSetup() {

    }

    public static WebDriver getWebDriverInstance(String browserName , String browserVersion , String platform) throws MalformedURLException{
        if(driver.get() == null){

            String execUrl = "https://" + configReader.getProperty("username") + ":" + configReader.getProperty("accessKey") + "@hub.lambdatest.com/wd/hub";

            DesiredCapabilities rules = new DesiredCapabilities();

            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("browserName", browserName);
            ltOptions.put("browserVersion", browserVersion);
            ltOptions.put("platformName", platform);
            ltOptions.put("visual", true);
            ltOptions.put("video", true);
            ltOptions.put("network", true);
            ltOptions.put("console", "true");
            ltOptions.put("w3c", true);
            ltOptions.put("build", "Selenium101");

            rules.setCapability("LT:Options", ltOptions);

        try{
            URI gridUri = new URI(execUrl);
            URL gridUrl = gridUri.toURL();
            WebDriver webDriver = new RemoteWebDriver(gridUrl, rules);
            driver.set(webDriver);
           }catch(URISyntaxException e) {
                throw new MalformedURLException("Invalid grid URL: " + execUrl);
           }         
        }
        return driver.get();
    }

    
    public static void removeWebDriver(){
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
