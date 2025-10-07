package Cucumber;

import BaseUtility.BaseTest;
import PageObjects.HomePage;
import PageObjects.SimpleFormPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepDefinitions extends BaseTest {

    private HomePage hp;
    private SimpleFormPage sf;

    @Given("User is in the home page")
    public void User_is_in_the_home_page(){
        setUp();
        hp = new HomePage(driver);
        hp.validateHomepage();
        Assert.assertTrue(hp.validateURLContent());
    }

    @Given("User is in the simple form demo page")
    public void User_is_in_the_simple_form_demo_page(){
        hp.selectSimpleFormDemo();
        sf = new SimpleFormPage(driver);
        sf.validateChildPage();
    }

    @When("User enters text on the checkbox")
    public void User_enters_text_on_the_checkbox(){
        sf.enterUserText();
    }

    @And("User clicks on checked value button")
    public void User_clicks_on_checked_value_button(){
        sf.clickCheckedValueButton();
    }

    @Then("Message should get displayed")
    public void Message_should_get_displayed(){
        Assert.assertTrue(sf.validateMessageContent());
        tearDown();
    }
}
