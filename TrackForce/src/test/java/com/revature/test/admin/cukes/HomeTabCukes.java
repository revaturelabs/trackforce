package com.revature.test.admin.cukes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.testclasses.AdminSuite;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeTabCukes extends AdminSuite {
	

	static WebElement e = null;

	@Given("^I am on the Home Page$")
	public static boolean i_am_on_the_Home_Page(WebDriver d) throws Throwable {

		try {
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.clickHomeTab(d).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the Home Tab"));
			return false;
		}

	}

	@When("^I click on the telephone link$")
	public static boolean i_click_on_the_telephone_link(WebDriver d) throws Throwable {
		try {
			Thread.sleep(2000);
			HomeTab.phone(wd).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the phone link"));
			return false;
		}

	}

	@Then("^I should see the telephone number link open on a browser$")
	public void i_should_see_the_telephone_number_link_open_on_a_browser() throws Throwable {

	}

	@When("^I click on the email link$")
	public static boolean i_click_on_the_email_link(WebDriver d) throws Throwable {

		try {
			Thread.sleep(2000);
			HomeTab.email(wd).click();
			Thread.sleep(7000);
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the email link"));
			return false;
		}

	}

	@Then("^I should see the email link open on a browser$")
	public void i_should_see_the_email_link_open_on_a_browser() throws Throwable {

	}

	@When("^I click on the website link$")
	public static boolean i_click_on_the_website_link(WebDriver d) throws Throwable {
		
		try {
			Thread.sleep(2000);
			HomeTab.website(wd).click();
			Thread.sleep(7000);
			return true;
		} catch (Throwable e) {
			System.out.println(("Failed click on the website link"));
			return false;
		}
		
	}

	@Then("^I should see the Revature website link open on a browser$")
	public void i_should_see_the_Revature_website_link_open_on_a_browser() throws Throwable {

	}

	
	@When("^I click on the populate database button$")
	public static boolean i_click_on_the_populate_database_button(WebDriver d) throws Throwable {
		
		try {
			Thread.sleep(2000);
			HomeTab.populateDatabase(wd).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the populate database button"));
			return false;
		}
	    
	}

	@Then("^I should see a temporary blue border for the populate database button$")
	public void i_should_see_a_temporary_blue_border_for_the_populate_database_button(WebDriver d) throws Throwable {
	    
		
	}

	@When("^I click on the populate static salesforce button$")
	public static boolean i_click_on_the_populate_static_salesforce_button(WebDriver d) throws Throwable {
		try {
			Thread.sleep(2000);
			HomeTab.populateStaticSalesforce(wd).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the static salesforce button"));
			return false;
		}
	}

	@Then("^I should see a temporary blue border for the populate static salesforce button$")
	public void i_should_see_a_temporary_blue_border_for_the_populate_static_salesforce_button(WebDriver d) throws Throwable {
	   
	}

	@When("^I click on the empty database button$")
	public static boolean i_click_on_the_empty_database_button(WebDriver d) throws Throwable {
		
		try {
			Thread.sleep(2000);
			HomeTab.emptyDatabase(wd).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the empty database button"));
			return false;
		}
	    
	}

	@Then("^I should see a temporary blue border for the empty database button$")
	public void i_should_see_a_temporary_blue_border_for_the_empty_database_button(WebDriver d) throws Throwable {
	    
	}

}
