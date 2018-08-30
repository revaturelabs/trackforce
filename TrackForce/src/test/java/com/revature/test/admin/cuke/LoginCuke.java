package com.revature.test.admin.cuke;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WebDriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LoginCuke {
	
	public static WebDriver driver;
	static WebDriverWait wait; 
	
	@Given("^I connect to caliber$")
	public void i_connect_to_caliber(){
		driver = WebDriverUtil.getChromeDriver();
		driver.manage().window().maximize();
		driver.get(TestConfig.getBaseURL());
		wait = new WebDriverWait(driver,15);
	}
	
	@And("^the login page loads$")
	public void the_login_page_loads() throws Throwable {
		assertEquals(driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/#/login");
	}

	@When("^I enter the correct admin login information$")
	public void i_enter_the_correct_admin_login_information() throws Throwable {
		System.out.println("Entering correct admin login info");
		Login.login(LoginUtil.getPropertyValue("adminUN"),LoginUtil.getPropertyValue("adminPW"),driver);
	}
	@When("^I enter the correct associate login information$")
	public void i_enter_the_correct_associate_login_information() throws Throwable {
		System.out.println("Entering correct associate login info");
	   Login.login(LoginUtil.getPropertyValue("associateUN"),LoginUtil.getPropertyValue("associatePW"),driver);
	}
	@When("^I enter the correct manager login information$")
	public void i_enter_the_correct_manager_login_information() throws Throwable {
		System.out.println("Entering correct testmanager login info");
	   Login.login(LoginUtil.getPropertyValue("stagingManagerUN"),LoginUtil.getPropertyValue("stagingManagerPW"),driver);
	}
	@When("^I enter the correct trainer login information$")
	public void i_enter_the_correct_trainer_login_information() throws Throwable {
		System.out.println("Entering correct trainer login info");
	   Login.login(LoginUtil.getPropertyValue("trainerUN"),LoginUtil.getPropertyValue("trainerPW"),driver);
	}
	@When("^I enter the correct delivery login information$")
	public void i_enter_the_correct_sales_login_information() throws Throwable {
		System.out.println("Entering correct sales login info");
	   Login.login(LoginUtil.getPropertyValue("salesDeliveryUN"),LoginUtil.getPropertyValue("salesDeliveryPW"),driver);
	}

	@When("^I click Submit$")
	public void i_click_Submit() throws Throwable {
		System.out.println("Clicking submit (login)");
		Login.getSignin(driver).click();
	}
	
	@When("^I enter a correct username without a password$")
	public void i_enter_a_correct_username_without_a_password() throws Throwable {
		System.out.println("Entering correct username with no password");
		Login.login("TestAdmin", "", driver);
	}
	
	@When("^I enter a correct password with an incorrect username$")
	public void i_enter_a_correct_password_with_an_incorrect_username() throws Throwable {
		System.out.println("Entering incorrect username with correct password");
		Login.login("NotAUsername", "TestAdmin", driver);
	}

	@When("^I enter an incorrect password with an incorrect username$")
	public void i_enter_an_incorrect_password_with_an_incorrect_username() throws Throwable {
		Login.login("NotAUsername", "NotAPassword", driver);
	}
	
	@When("^I enter a correct username with an incorrect password$")
	public void i_enter_a_correct_username_with_an_incorrect_password() throws Throwable {
		Login.login("TestAdmin","NotAPassword", driver);
	}

	@When("^I enter a correct password without a username$")
	public void i_enter_a_correct_password_without_a_username() throws Throwable {
	    Login.login("", "TestAdmin", driver);
	}
	
	@When("^if I click Log out$")
	public void if_I_click_Log_out() throws Throwable {
		System.out.println("clicking logout");
		driver.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[1]/a")).click();
	}

	@Then("^I should remain on the login page$")
	public void i_should_remain_on_the_login_page() throws Throwable {
		wait.until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/#/login"));
		assertEquals(HomeTab.getCurrentURL(driver),"http://34.227.178.103:8090/NGTrackForce/#/login");
	}

	@Then("^I should be taken to the home page$")
	public void i_should_be_taken_to_the_home_page() throws Throwable {
		wait.until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/#/app-home"));
		assertEquals(HomeTab.getCurrentURL(driver),"http://34.227.178.103:8090/NGTrackForce/#/app-home");
	}
	
	@Then("^I should be taken to the trainer home page$")
	public void i_should_be_taken_to_the_trainer_home_page() throws Throwable {
		wait.until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/#/trainer-view"));
		assertEquals(HomeTab.getCurrentURL(driver),"http://34.227.178.103:8090/NGTrackForce/#/trainer-view");
	}
	
	@Then("^I should be taken to the associate home page$")
	public void i_should_be_taken_to_the_associate_home_page() throws Throwable {
		wait.until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/#/associate-view"));
		assertEquals(driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/#/associate-view");
	}

	@Then("^I should be on the login page$")
	public void i_should_be_on_the_login_page() throws Throwable {
		wait.until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/#/login"));
		assertEquals(driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/#/login");
	}
	
	@After
	public void close() {
		driver.quit();
	}
	
	
}
