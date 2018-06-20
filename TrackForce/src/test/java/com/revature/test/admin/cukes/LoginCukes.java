package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
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
		driver.manage().window().maximize();
		driver.get(TestConfig.getBaseURL());
		ServiceHooks.driver = driver;
	}
	
	@Given("^the login page loads$")
	public void the_login_page_loads() throws Throwable {
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/login");
	}
	
	@When("^I enter the correct \"([^\"]*)\" login information$")
	public void i_enter_the_correct_login_information(String role) throws Throwable {
	    String info = "";
		switch(role) {
	    case"admin":info = "TestAdmin"; break;
	    case"delivery":info = "TestSales";break;
	    case"associate":info = "TestAssociate";break;
	    case"trainer":info = "TestTrainer";break;
	    case"manager":info = "TestManager";break;
	    }
	    Login.login(info,info,ServiceHooks.driver);
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
	@When("^I enter the correct delivery login information$")
	public void i_enter_the_correct_sales_login_information() throws Throwable {
	   Login.login("TestSales","TestSales",ServiceHooks.driver);
	}

	@When("^I click Submit$")
	public void i_click_Submit() throws Throwable {
		Login.getSignin(ServiceHooks.driver).click();
		Thread.sleep(5000);
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

	@When("^I enter a correct password without a username$")
	public void i_enter_a_correct_password_without_a_username() throws Throwable {
	    Login.login("", "TestAdmin", ServiceHooks.driver);
	}
	
	@When("^if I click Log out$")
	public void if_I_click_Log_out() throws Throwable {
		ServiceHooks.driver.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[1]/a")).click();
		//Logout.logout(ServiceHooks.driver);
	}

	@Then("^I should remain on the login page$")
	public void i_should_remain_on_the_login_page() throws Throwable {
		Thread.sleep(1500);
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver),"http://34.227.178.103:8090/NGTrackForce/login");
	}

	@Then("^I should be taken to the home page$")
	public void i_should_be_taken_to_the_home_page() throws Throwable {
		Thread.sleep(1000);
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver),"http://34.227.178.103:8090/NGTrackForce/app-home");
	}
	
	@Then("^I should be taken to the associate home page$")
	public void i_should_be_taken_to_the_associate_home_page() throws Throwable {
		Thread.sleep(5000);
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/associate-view/56052");
	}

	@Then("^I should be on the login page$")
	public void i_should_be_on_the_login_page() throws Throwable {
		Thread.sleep(7000);
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/login");
	}
	
	@After
	public void close() {
		ServiceHooks.driver.close();
	}
	
	
}
