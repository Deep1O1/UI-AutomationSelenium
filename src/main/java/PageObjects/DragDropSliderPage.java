package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ReusableUtility.ReusableComponents;


public class DragDropSliderPage extends ReusableComponents{
    
    public DragDropSliderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#slider3 div input")
    private WebElement sliderElement;

    @FindBy(css = "#slider3 div output")
    private WebElement sliderOutput;


    public void moveElementSlider(int xOffset, int yOffset){
        moveSlider(sliderElement, xOffset , yOffset);
    }

    public boolean sliderMovementRangeValue(String value){
        wait.until(ExpectedConditions.textToBePresentInElement(sliderOutput, value));
        return sliderOutput.getText().equals(value);
    }

}
