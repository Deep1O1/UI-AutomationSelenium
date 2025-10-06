package ReusableUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReusableComponents {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    public ReusableComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        this.action = new Actions(driver);
    }

    @FindBy(xpath = "//div[@class = 'container']/div/h1[contains(@class, 'text-center')]")
    private WebElement childPageCenterElement;

    
    
    public void validateChildPage(){
        wait.until(ExpectedConditions.visibilityOf(childPageCenterElement));
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void rightClickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.contextClick(element);
    }

    public void doubleClickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.doubleClick(element);
    }

    public void hoverOverElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        action.moveToElement(element);
    }

    public String getElementText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void switchToFirstChildWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println("Switched to Child Window: " + driver.getTitle());
                return;
            }
        }
        System.out.println("No child window found!");
    }



    public void moveSlider(WebElement element , int xOffset , int yOffset){
        action.clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    public void selectDropdownByText(WebElement element , String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
}
