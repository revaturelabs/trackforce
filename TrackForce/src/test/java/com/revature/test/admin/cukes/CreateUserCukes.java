package com.revature.test.admin.cukes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.revature.test.admin.pom.CreateUserTab;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.WaitToLoad;

public class CreateUserCukes extends AdminSuite {
	static WebElement e = null;
	
	@Given("^We are on Create User Tab$")
	public static boolean clickCreateUserTab(WebDriver d) {
		try {
			//Thread.sleep(5000);
			CreateUserTab.getCreateUserTab(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			return false;
		}
	}

	public static boolean onCreateUserTab(WebDriver d) {
		try {
			//Thread.sleep(2000);
			e = CreateUserTab.getCreateNewUserHeader(d);
			if (e.getText().contains("Create New User")) {
				return true;
			}
			System.out.println("Header did not contain 'Create New User'");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to find 'Create New User' element");
			return false;
		}
	}

}
