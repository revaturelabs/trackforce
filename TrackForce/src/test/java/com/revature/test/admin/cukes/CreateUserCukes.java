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
	
	private static String user = "username";
	private static String pass = "password";
	
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
	
	public static boolean inputUsername(WebDriver d) {
		try {
			CreateUserTab.getUsername(d).sendKeys(user);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			return false;
		}
	}
	
	public static boolean inputPassword(WebDriver d) {
		try {
			CreateUserTab.getPassword(d).sendKeys(pass);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			return false;
		}
	}

	public static boolean inputPasswordConfirm(WebDriver d) {
		try {
			CreateUserTab.getPasswordConfirm(d).sendKeys(user);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			return false;
		}
	}
	
	public static boolean clickAdminRadio(WebDriver d) {
		try {
			CreateUserTab.getAdminRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Admin radio button");
			return false;
		}
	}
	
	public static boolean clickManagerRadio(WebDriver d) {
		try {
			CreateUserTab.getManagerRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Manager radio button");
			return false;
		}
	}
	
	public static boolean clickVPRadio(WebDriver d) {
		try {
			CreateUserTab.getVPRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click VP radio button");
			return false;
		}
	}
}
