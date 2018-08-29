package com.revature.test.runner;

import org.junit.runner.RunWith;
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
		features = {"src/test/resources/AdminFeatureFiles/Login.feature"}
		,glue = {"com.revature.test.utils","com.revature.test.admin.cuke"}
		)
 
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
 
}
