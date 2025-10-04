package UtilityManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestSetup {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> ExtentTest = new ThreadLocal<>();

    private ExtentTestSetup(){

    }

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            //String reportPath = "Test-Reports/ExtentReport_" + System.currentTimeMillis() + ".html"; // This is used to create unique reports each time
            String reportPath = "Test-Reports/ExtentReport.html"; // Here we are overwriting the existing report
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("UI Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("QA", "Subhadeep");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return ExtentTest.get();
    }

    public static void setTest(ExtentTest test) {
        ExtentTest.set(test);
    }
}
