package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/FeatureFiles/Demo",
        glue = {"Cucumber" , "HooksSetup"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:cucumberTarget/cucumber.html"}
)

public class TestNGRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
