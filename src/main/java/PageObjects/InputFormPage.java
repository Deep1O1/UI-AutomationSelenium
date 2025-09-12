package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReusableUtility.ReusableComponents;
import ReusableUtility.configReader;


public class InputFormPage extends ReusableComponents{
    
    public InputFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@type = 'submit'])[2]")
    WebElement submitButton;

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(id = "inputEmail4")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passwordField;

    @FindBy(css = "input[placeholder = 'Company']")
    private WebElement companyField;

    @FindBy(id = "websitename")
    private WebElement websiteField;

    @FindBy(name = "country")
    private WebElement countryList;

    @FindBy(id = "inputCity")
    private WebElement cityField;

    @FindBy(name = "address_line1")
    private WebElement addressField1;

    @FindBy(name = "address_line2")
    private WebElement addressField2;

    @FindBy(id = "inputState")
    private WebElement stateField;

    @FindBy(css = "input[placeholder = 'Zip code']")
    private WebElement zipCodeField;

    @FindBy(css = "p[class = 'success-msg hidden']")
    WebElement successMessageElement;



    public void clickOnSubmitButton(){
        submitButton.click();
    }

    public String getValidationMessage(){
        return nameField.getAttribute("validationMessage");
    }

    public void selectCountry(String countryName){
        selectDropdownByText(countryList, countryName);
    }

    public void fillInputForm(){
        nameField.sendKeys(configReader.getProperty("name"));
        emailField.sendKeys(configReader.getProperty("email"));
        passwordField.sendKeys(configReader.getProperty("password"));
        companyField.sendKeys(configReader.getProperty("company"));
        websiteField.sendKeys(configReader.getProperty("website"));
        cityField.sendKeys(configReader.getProperty("city"));
        addressField1.sendKeys(configReader.getProperty("address1"));
        addressField2.sendKeys(configReader.getProperty("address2"));
        stateField.sendKeys(configReader.getProperty("state"));
        zipCodeField.sendKeys(configReader.getProperty("zip"));
    }

    public String getSuccessMessage(){
        return successMessageElement.getText();
    }
}
