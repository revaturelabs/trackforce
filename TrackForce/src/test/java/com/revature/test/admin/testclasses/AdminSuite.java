package com.revature.test.admin.testclasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.revature.test.admin.pom.Logout;
import com.revature.test.utils.WebDriverUtil;
import com.revature.test.utils.LoginUtil;

//import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class AdminSuite extends AbstractTestNGCucumberTests{

	public static WebDriver wd = WebDriverUtil.getChromeDriver();

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("================== TRACKFORCE TESTS ==================");
		//wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Logging In");
		try {
			//wd.get("http://52.207.66.231:4200");

			wd.get("http://localhost:4200");

			LoginUtil.loginAsAdmin(wd);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Logging out");
		//Logout.logout(wd);
		//wd.quit();
	}
}
