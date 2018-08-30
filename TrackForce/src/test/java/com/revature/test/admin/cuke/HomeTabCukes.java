package com.revature.test.admin.cuke;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeTabCukes{
	
	@Given("^I navigate to the Home Page$")
	public static void i_navigate_to_the_Home_Page() throws InterruptedException  {
		System.out.println("Navigating to the home page");	
	}
	
	@Given("^I am on the Home Page$")
	public static void i_am_on_the_Home_Page() throws InterruptedException  {
			assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/app-home");
	}

	@Given("^Home Tab loads$")
	public static void home_tab_loads() {
		try {
			if (HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL()) || 
					HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/") || 
					HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/root")) {
				
			}
			fail("Current URL does not equal the base URL, or does not end with / or /root");
			
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			fail("Failed to get current URL");
			
		}
	}
	
	@When("^I click on the telephone link$")
	public static void i_click_on_the_telephone_link()  {
		try {
			HomeTab.phone(ServiceHooks.driver).click();
		} catch (Throwable e) {
			System.out.println(("Failed click on the phone link"));
			fail(("Failed click on the phone link"));
		}

	}

	@Then("^I should see the telephone number link open on a browser$")
	public void i_should_see_the_telephone_number_link_open_on_a_browser(){
		
		/*
		WaitToLoad.AlertToBePresent(ServiceHooks.driver,5);
		Alert alert = ServiceHooks.driver.switchTo().alert();
		assertEquals(alert.getText(),"Open Pick an app?");	
	    */
	}

	@When("^I click on the email link$")
	public static void i_click_on_the_email_link()  {

		try {
			HomeTab.email(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the email link"));
			fail(("Failed click on the email link"));
			
		}

	}

	@Then("^I should see the email link open on a browser$")
	public void i_should_see_the_email_link_open_on_a_browser() throws InterruptedException  {
		//Email opens in a location based on where the default email service is on the computer
	}

	@When("^I click on the website link$")
	public static void i_click_on_the_website_link()  {
		
		try {
			//ServiceHooks.driver.findElement(By.xpath("/html/body/app-component/app-footer/footer/div/div/div[3]/ul/li[2]/a")).click();
			HomeTab.website(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the website link"));
			fail(("Failed click on the website link"));
		}
		
	}

	@Then("^I should see the Revature website link open on a browser$")
	public void i_should_see_the_Revature_website_link_open_on_a_browser() throws InterruptedException  {
		try {
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (ServiceHooks.driver.getWindowHandles());
		//switch to new tab
		ServiceHooks.driver.switchTo().window(browserTabs .get(1));
		//check is it correct page opened or not (e.g. check page's title)
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"https://revature.com/");
		//then close tab and get back
		ServiceHooks.driver.close();
		ServiceHooks.driver.switchTo().window(browserTabs.get(0));
		}catch(Throwable e) {
			fail("I should see Revature Website link open");
		}
	}
	
	
}

