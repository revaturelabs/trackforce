package com.revature.test.admin.testclasses;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
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
