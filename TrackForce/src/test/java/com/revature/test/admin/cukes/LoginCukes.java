package com.revature.test.admin.cukes;

import static org.junit.Assert.fail;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WebDriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginCukes {
	@Given("^I connect to caliber$")
	public void i_connect_to_caliber(){
		WebDriver driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		ServiceHooks.driver = driver;
	}
	
	@Given("^the login page loads$")
	public void the_login_page_loads() throws Throwable {
		//checkLoginPage
	}

	@When("^I enter the correct admin login information$")
	public void i_enter_the_correct_admin_login_information() throws Throwable {
	   Login.login("TestAdmin","TestAdmin",ServiceHooks.driver);
	}
	@When("^I enter the correct associate login information$")
	public void i_enter_the_correct_associate_login_information() throws Throwable {
	   Login.login("TestAssociate","TestAssociate",ServiceHooks.driver);
	}
	@When("^I enter the correct manager login information$")
	public void i_enter_the_correct_manager_login_information() throws Throwable {
	   Login.login("TestManager","TestManager",ServiceHooks.driver);
	}
	@When("^I enter the correct trainer login information$")
	public void i_enter_the_correct_trainer_login_information() throws Throwable {
	   Login.login("TestTrainer","TestTrainer",ServiceHooks.driver);
	}
	@When("^I enter the correct sales login information$")
	public void i_enter_the_correct_sales_login_information() throws Throwable {
	   Login.login("TestSales","TestSales",ServiceHooks.driver);
	}

	@When("^I click Submit$")
	public void i_click_Submit() throws Throwable {
	    Login.getSignin(ServiceHooks.driver).click();
	}

	@Then("^I should be taken to the home page$")
	public void i_should_be_taken_to_the_home_page() throws Throwable {
		try {
		//WaitToLoad.findDynamicElements(ServiceHooks.driver,By.id(prop.getProperty("homePie")),5);
		}
		catch(TimeoutException te) {
			fail("didn't navigate to homepage");
		}
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver),"http://34.227.178.103:8090/NGTrackForce/app-home");
	}

	@When("^I enter a correct password without a username$")
	public void i_enter_a_correct_password_without_a_username() throws Throwable {
	    Login.login("", "TestAdmin", ServiceHooks.driver);
	}

	@Then("^I should remain on the login page$")
	public void i_should_remain_on_the_login_page() throws Throwable {
	    assertEquals(Login.getTitle(ServiceHooks.driver),"Login");
	}

	@When("^I enter a correct username without a password$")
	public void i_enter_a_correct_username_without_a_password() throws Throwable {
		Login.login("TestAdmin", "", ServiceHooks.driver);
	}
	
	@When("^I enter a correct password with an incorrect username$")
	public void i_enter_a_correct_password_with_an_incorrect_username() throws Throwable {
		Login.login("NotAUsername", "TestAdmin", ServiceHooks.driver);
	}

	@When("^I enter an incorrect password with an incorrect username$")
	public void i_enter_an_incorrect_password_with_an_incorrect_username() throws Throwable {
		Login.login("NotAUsername", "NotAPassword", ServiceHooks.driver);
	}
	
	@When("^I enter a correct username with an incorrect password$")
	public void i_enter_a_correct_username_with_an_incorrect_password() throws Throwable {
		Login.login("TestAdmin","NotAPassword", ServiceHooks.driver);
	}
	@After
	public void close() {
		ServiceHooks.driver.close();
	}
}
