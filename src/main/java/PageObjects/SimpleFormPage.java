package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ReusableUtility.ReusableComponents;

public class SimpleFormPage extends ReusableComponents{

    public SimpleFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String input = "Welcome to LambdaTest";

    
    @FindBy(css = "#user-message")
    private WebElement userInputField;

    @FindBy(id = "showInput")
    private WebElement valueButton;

    @FindBy(css = "#message")
    private WebElement updatedMessage;



    public void enterUserText(){
        userInputField.sendKeys(input);
    }

    public void clickCheckedValueButton(){
        valueButton.click();
    }

    public boolean validateMessageContent(){
        wait.until(ExpectedConditions.textToBePresentInElement(updatedMessage, input));
        return updatedMessage.getText().equals(input);
    }
    
}
