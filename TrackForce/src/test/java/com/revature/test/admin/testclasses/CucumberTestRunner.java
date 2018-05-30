package com.revature.test.admin.testclasses;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/AdminFeatureFiles/HomeTab.feature"
		,glue ="com.revature.test.admin.cukes"
		)
 
public class CucumberTestRunner {
 
}
