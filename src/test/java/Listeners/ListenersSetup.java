package Listeners;

import BaseUtility.BaseTest;
import UtilityManager.ExtentTestSetup;
import UtilityManager.WebDriverInstanceSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListenersSetup extends BaseTest implements ITestListener {

    private static ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        reports = ExtentTestSetup.getExtentReports();
        cleanScreenshotFolder();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = reports.createTest(result.getMethod().getMethodName());
        ExtentTestSetup.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestSetup.getTest().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestSetup.getTest().fail(result.getThrowable());
        String screenshotPath = takeScreenshot(WebDriverInstanceSetup.getDriver(), result.getName());
        ExtentTestSetup.getTest().fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        //ExtentTestSetup.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestSetup.getTest().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestSetup.getExtentReports().flush();
    }
}
