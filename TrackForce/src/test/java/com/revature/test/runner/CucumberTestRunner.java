package com.revature.test.runner;

import static com.revature.utils.LogUtil.logger;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
 
/**
 * Test runner to run all of the features in the features attribute and to match them with the
 * appropriate step definitions specified by the glue. There is no setup or teardown
 * Reviewed by Jesse
 * @since 6.18.06.07
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:src/test/resources/cucumber.json"},
        features = {"src/test/resources/AdminFeatureFiles/"},
        glue = {"com.revature.test.utils","com.revature.test.cuke"}
        )

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    
    @AfterSuite
    public static void teardown() {
        
        CucumberResultsOverview results = new CucumberResultsOverview();
        results.setOutputDirectory("target");
        results.setOutputName("cucumber-results");
        results.setSourceFile("./src/test/resources/cucumber.json");
        try {
            results.executeFeaturesOverviewReport();
        } catch (Exception e) {
        	logger.error("Cucumber report issue" + e.getMessage());
        }
        
    }

}
