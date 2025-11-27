package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class findAllBrokenLinks {
    public static void main(String[] args) throws IOException, URISyntaxException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        driver.get("https://www.wikipedia.org/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class = 'other-projects']")));

        List<WebElement> bottomLinks = driver.findElements(By.xpath("//nav[@class = 'other-projects']//a"));

        for (WebElement e : bottomLinks){

            String URL = e.getAttribute("href");

            URI uri = new URI(URL);
            HttpURLConnection conn = (HttpURLConnection) (uri.toURL().openConnection());

            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            if(responseCode >= 200 && responseCode <= 303){
                System.out.println("Website " + URL + " is working fine with status code : " + responseCode);
            }else {
                System.out.println("Website " + URL + " is not working and has status code : " + responseCode);
            }
        }
    }
}
