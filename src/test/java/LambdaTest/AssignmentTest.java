package LambdaTest;
import BaseUtility.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.DragDropSliderPage;
import PageObjects.HomePage;
import PageObjects.InputFormPage;
import PageObjects.SimpleFormPage;
import ReusableUtility.configReader;

public class AssignmentTest extends BaseTest {
    

    @Test
    public void testScenario1(){
        HomePage hp = new HomePage(driver);
        hp.validateHomepage();
        Assert.assertTrue(hp.validateURLContent());
        hp.selectSimpleFormDemo();
        SimpleFormPage sf = new SimpleFormPage(driver);
        sf.validateChildPage();
        sf.enterUserText();
        sf.clickCheckedValueButton();
        Assert.assertTrue(sf.validateMessageContent());
    }

    @Test
    public void testScenario2(){
        HomePage hp = new HomePage(driver);
        hp.validateHomepage();
        hp.selectDragDropSlider();
        DragDropSliderPage dd = new DragDropSliderPage(driver);
        dd.validateChildPage();
        dd.moveElementSlider(214, 0);
        Assert.assertTrue(dd.sliderMovementRangeValue("95"));
    }

    @Test
    public void testScenario3(){
        HomePage hp = new HomePage(driver);
        hp.validateHomepage();
        hp.selectInputForm();
        InputFormPage ip = new InputFormPage(driver);
        ip.validateChildPage();
        ip.clickOnSubmitButton();
        String validationMsg = ip.getValidationMessage();
        Assert.assertTrue(validationMsg.contains("Please fill"),
                "Unexpected validation message: " + validationMsg);
        ip.selectCountry(configReader.getProperty("country"));
        ip.fillInputForm();
        ip.clickOnSubmitButton();
        Assert.assertEquals(ip.getSuccessMessage(), "Thanks for contacting us, we will get back to you shortly.");
    }
    
}
