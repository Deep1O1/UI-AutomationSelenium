package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = "Cucumber",
        monochrome = true,
        plugin = {
                "pretty",
                "html:cucumberTarget/cucumber.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
