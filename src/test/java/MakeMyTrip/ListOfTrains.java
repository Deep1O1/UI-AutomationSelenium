package MakeMyTrip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListOfTrains extends BaseTestMMT {

    @FindBy(css = "[data-cy = 'CommonModal_2']")
    WebElement loginModal;

    @FindBy(css = "div[data-cy = 'RegularSaleWrapper']")
    WebElement sellWrapper;

    @FindBy(css = "[data-cy = 'CommonModal_2'] span[data-cy = 'closeModal']")
    WebElement closeModal;

    @FindBy(css = "[data-cy = 'menu_Trains']")
    WebElement trainOptions;

    @FindBy(css = "[data-cy = 'fromCity']")
    WebElement fromCity;

    @FindBy(css = "[title = 'From']")
    WebElement fromInputField;

    @Test
    public void getListOfTrains() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginModal));

        closeModal.click();

        Assert.assertEquals(driver.getTitle(), "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");

        trainOptions.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-cy='RailSearchWidget_338']")));

        fromCity.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title = 'From']")));

        fromInputField.sendKeys("Howrah");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[role = 'listbox']")));
        Thread.sleep(2000);

        List<WebElement> availableOptionList = driver.findElements(
                By.xpath("//ul[@role='listbox']/li/div/span"));

        for (WebElement e : availableOptionList) {
            if (e.getText().trim().equalsIgnoreCase("HWH")) {
                wait.until(ExpectedConditions.elementToBeClickable(e)).click();
                break;
            }
        }

    }
}
