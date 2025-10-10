package HooksSetup;

import BaseUtility.BaseTest;
import UtilityManager.ExtentTestSetup;
import UtilityManager.WebDriverInstanceSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks extends BaseTest {

    private static ExtentReports reports;

    @Before
    public void environmentSetup(Scenario scenario){
        setUp();
        reports = ExtentTestSetup.getExtentReports();
        cleanScreenshotFolder();
        ExtentTest test = reports.createTest(scenario.getName());
        ExtentTestSetup.setTest(test);
    }

    @AfterStep
    public void testDetails(Scenario scenario){
        if(scenario.isFailed()){
            String screenshotPath = takeScreenshot(WebDriverInstanceSetup.getDriver(), scenario.getName());
            ExtentTestSetup.getTest().fail(
                    "Step failed in scenario: " + scenario.getName(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
            );
        }
    }

    @After
    public void testClosure(Scenario scenario){
        if (scenario.isFailed()) {
            ExtentTestSetup.getTest().fail("Scenario failed: " + scenario.getName());
        } else {
            ExtentTestSetup.getTest().pass("Scenario passed: " + scenario.getName());
        }
        reports.flush();
        tearDown();
    }
}
