package Practice;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class BruteForceDatePickerAngleMovement {

    public static void datePicker(String date , WebDriver driver){
        String[] split = date.split("/");
        int destMonth = Integer.parseInt(split[0])-1;

        List<String> monthList = List.of("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

        String destYear =  split[2];
        String destDate = split[1];
        String destMonthName = monthList.get(destMonth);
        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        driver.findElement(By.cssSelector("input#datepicker")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ui-datepicker-div")));

        String currentYear = driver.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
        String currentMonthName = driver.findElement(By.cssSelector("span.ui-datepicker-month")).getText();

        while (!currentYear.equals(destYear) || !currentMonthName.equals(destMonthName)) {

            if (Integer.parseInt(currentYear) < Integer.parseInt(destYear) || currentYear.equals(destYear) &&
                    monthList.indexOf(currentMonthName) < destMonth) {
                driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
            } else {
                driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
            }
            currentYear = driver.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
            currentMonthName = driver.findElement(By.cssSelector("span.ui-datepicker-month")).getText();
        }
        driver.findElement(By.xpath("//a[@data-date = '"+ destDate + "']")).click();
    }

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://jqueryui.com/datepicker/");
        datePicker("05/31/2027",driver);

        driver.close();
    }

}

