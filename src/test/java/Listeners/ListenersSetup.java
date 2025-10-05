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

public class ListenersSetup implements ITestListener{

    private static ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        reports = ExtentTestSetup.getExtentReports();

        String directory = "Test-Reports/screenshots";
        Path screenshotDir = Paths.get(directory);

        try {
            if (Files.exists(screenshotDir)) {
                Files.walk(screenshotDir)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete: " + path);
                            }
                        });
            } else {
                Files.createDirectories(screenshotDir);
                System.out.println("Screenshot folder created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String screenshotPath = BaseTest.takeScreenshot(WebDriverInstanceSetup.getDriver(), result.getName());
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
