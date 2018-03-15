package com.revature.test.admin.testclasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.revature.test.utils.WebDriverUtil;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.TestConfig;

//import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@CucumberOptions(features="src/test/resources/AdminFeatureFiles")
public class AdminSuite extends AbstractTestNGCucumberTests{

	public static WebDriver wd = WebDriverUtil.getChromeDriver();
	private String baseURL = TestConfig.getBaseURL();
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("================== TRACKFORCE TESTS ==================");
		//wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Logging In");
		try {
			wd.get(baseURL);
			LoginUtil.loginAsAdmin(wd);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Logging out");
		//Logout.logout(wd);
		wd.quit();

	}
}
