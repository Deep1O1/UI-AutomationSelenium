package Listeners;

import BaseUtility.BaseTest;
import UtilityManager.ExtentTestSetup;
import UtilityManager.WebDriverInstanceSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersSetup implements ITestListener{

    private static ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        reports = ExtentTestSetup.getExtentReports();
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
        String screenshotPath = BaseTest.takeScreenshot(WebDriverInstanceSetup.getDriver(), result.getMethod().getMethodName());
        ExtentTestSetup.getTest().addScreenCaptureFromPath(screenshotPath);
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
