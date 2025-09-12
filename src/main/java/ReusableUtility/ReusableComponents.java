package ReusableUtility;

import java.time.Duration;

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
