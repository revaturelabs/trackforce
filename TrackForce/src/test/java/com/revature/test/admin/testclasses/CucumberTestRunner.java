package com.revature.test.admin.testclasses;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
/**
 * Test runner to run all of the features in the features attribute and to match them with the
 * appropriate step definitions specified by the glue. There is no setup or teardown
 * Reviewed by Jesse
 * @since 6.18.06.07
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty", "html:target/cucumber"},
		features = {"src/test/resources/AdminFeatureFiles/AssociateList.feature",
				"src/test/resources/AdminFeatureFiles/HomeTab.feature",
				"src/test/resources/AdminFeatureFiles/CreateUser.feature",
				"src/test/resources/AdminFeatureFiles/ClientList.feature"}
		,glue = {"com.revature.test.utils","com.revature.test.admin.cukes"}
		)
 
public class CucumberTestRunner {
 
}
