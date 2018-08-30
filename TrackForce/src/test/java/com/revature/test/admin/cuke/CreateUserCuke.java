package com.revature.test.admin.cuke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.pom.AdminCreateUser;
import com.revature.pom.AdminNavbarElements;
import com.revature.pom.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateUserCuke {

	public static WebDriver wd;
	public static WebDriverWait wait;

	@Given("^I login as an Administrator$")
	public void i_login_as_an_Administrator() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.username(wd)));
		LoginPage.username(wd).sendKeys("TestAdmin");
		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.password(wd)));
		LoginPage.password(wd).sendKeys("TestAdmin");
	}

	@Given("^Create User Tab loads$")
	public void create_User_Tab_loads() throws Throwable {

		wait.until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.clientList(wd)));
		AdminNavbarElements.createUser(wd).click();

	}

	@When("^I type in a valid username \"([^\"]*)\"$")
	public void i_type_in_a_valid_username(String arg1) throws Throwable {

		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.username(wd)));
		AdminCreateUser.username(wd).sendKeys(arg1);

	}

	@When("^I type in a valid password\"([^\"]*)\"$")
	public void i_type_in_a_valid_password(String arg1) throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.password(wd)));
		AdminCreateUser.password(wd).sendKeys(arg1);
	}

	@When("^I confirm the password\"([^\"]*)\"$")
	public void i_confirm_the_password(String arg1) throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.confirmPassword(wd)));
		AdminCreateUser.confirmPassword(wd).sendKeys(arg1);
	}

	@When("^I check the \"([^\"]*)\" role$")
	public void i_check_the_role(String arg1) throws Throwable {

		switch (arg1) {
		case "Administrator":
			wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.adminRadio(wd)));
			AdminCreateUser.adminRadio(wd).click();
			break;
		case "Trainer":
			wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.trainerRadio(wd)));
			AdminCreateUser.trainerRadio(wd).click();
			break;
		case "Delivery/Sales":
			wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.deliveryRadio(wd)));
			AdminCreateUser.deliveryRadio(wd).click();
			break;
		case "Associate":
			wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.associateRadio(wd)));
			AdminCreateUser.associateRadio(wd).click();
			break;
		case "Staging Manager":
			wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.stagingRadio(wd)));
			AdminCreateUser.stagingRadio(wd).click();
			break;
		}
	}

	@When("^I press submit$")
	public void i_press_submit() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.submitBTN(wd)));
		AdminCreateUser.submitBTN(wd).click();
	}

	@Then("^A new User should be created$")
	public void a_new_User_should_be_created() throws Throwable {

	}
}