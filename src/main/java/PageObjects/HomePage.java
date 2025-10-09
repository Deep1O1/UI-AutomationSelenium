package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ReusableUtility.ReusableComponents;


public class HomePage extends ReusableComponents {
    

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'text-center')]/h1")
    private WebElement centerText;

    @FindBy(xpath = "(//div[@class = 'container__selenium'])[2]/ul/li/a[text() = 'Simple Form Demo']")
    private WebElement simpleFormDemoElement;

    @FindBy(linkText = "Simple Form Demo")
    private WebElement simpleFormDemo;

    @FindBy(linkText = "Drag & Drop Sliders")
    private WebElement dragDropSlider;

    @FindBy(linkText = "Input Form Submit")
    private WebElement inputForm;


    public void validateHomepage(){
        wait.until(ExpectedConditions.visibilityOf(centerText));
    }

    public boolean validateURLContent(){
        String url = simpleFormDemoElement.getAttribute("href");
        return url.contains("simple-form-demo");
    }

    public void selectSimpleFormDemo(){
        simpleFormDemo.click();
    }

    public void selectDragDropSlider(){
        dragDropSlider.click();
    }

    public void selectInputForm(){
        inputForm.click();
    }
}
