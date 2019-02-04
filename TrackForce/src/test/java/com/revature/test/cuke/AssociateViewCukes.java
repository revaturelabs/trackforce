package com.revature.test.cuke;

import static com.revature.test.cuke.ConstantsCukeTestUtil.getAssociateView;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getBaseUrl;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getInterviewDate;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getInterviewType;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getLogout;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getMyInterviewView;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getTestFirstName;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getTestLastName;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getUsernameUpdate;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getPasswordUpdate;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.test.pom.AssociateHome;
import com.revature.test.pom.MyInterviews;
import com.revature.test.pom.NavBar;
import com.revature.test.pom.PasswordUpdate;
import com.revature.test.pom.UsernameUpdate;
import com.revature.test.utils.ServiceHooks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateViewCukes {
	//Background for each test, occurs after Background described in LoginCuke
	@Given("^I am on the Associate Home Page$")
	public void i_am_on_the_Associate_Home_Page()  {
		ServiceHooks.wait.until(ExpectedConditions.urlToBe(getBaseUrl() + getAssociateView()));
		assertEquals(ServiceHooks.driver.getCurrentUrl(),getBaseUrl() + getAssociateView());
	}
	//End Background for Associate View Tests
	
	//Scenario: Associate is attempting to update information on their home page
	// refers to the update information form fields on the associate-view page home tab
	@When("^I enter new name information$")
	public void i_enter_new_name_information() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(AssociateHome.getResetButton(ServiceHooks.driver)));
		AssociateHome.getResetButton(ServiceHooks.driver).click();
		setOFN(AssociateHome.getCurrentFirstName(ServiceHooks.driver).getText());
		setOLN(AssociateHome.getCurrentLastName(ServiceHooks.driver).getText());
	    AssociateHome.getFirstNameInputField(ServiceHooks.driver).sendKeys(getTestFirstName());
	    AssociateHome.getLastNameInputField(ServiceHooks.driver).sendKeys(getTestLastName());
	}

	@When("^I click the save button$")
	public void i_click_the_save_button() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(AssociateHome.getSaveButton(ServiceHooks.driver)));
		AssociateHome.getSaveButton(ServiceHooks.driver).click();
	}

	@Then("^the changes should be reflected$")
	public void the_changes_should_be_reflected() throws Throwable {
		assertEquals(AssociateHome.getCurrentFirstName(ServiceHooks.driver).getText(),getTestFirstName());
		assertEquals(AssociateHome.getCurrentLastName(ServiceHooks.driver).getText(), getTestLastName());
		//reset test changes after validation
		reset_associate_information();
	}
	//End Scenario: Associate is attempting to update information

	//Scenario: update password
	@When("^I click on the update password option$")
	public void i_click_on_the_update_password_option() throws Throwable {
	    ServiceHooks.wait.until(ExpectedConditions.urlContains(getBaseUrl() + getAssociateView()));
	    NavBar.getWelcomeDropdown(ServiceHooks.driver).click();
		ServiceHooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getPasswordUpdate())));
		NavBar.getPasswordUpdate(ServiceHooks.driver).click();
	}
	
	@When("^I enter my current password$")
	public void i_enter_my_current_password() throws Throwable {
	    ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(PasswordUpdate.getOldPassword(ServiceHooks.driver)));
	    PasswordUpdate.getOldPassword(ServiceHooks.driver).sendKeys(/*currentPassword*/);
	}

	@When("^enter a new password$")
	public void enter_a_new_password() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(PasswordUpdate.getNewPassword(ServiceHooks.driver)));
	    PasswordUpdate.getNewPassword(ServiceHooks.driver).sendKeys(/*newPassword*/);
	}

	@When("^confirm the new password$")
	public void confirm_the_new_password() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(PasswordUpdate.getConfirmPassword(ServiceHooks.driver)));
	    PasswordUpdate.getConfirmPassword(ServiceHooks.driver).sendKeys(/*newPassword*/);
	}
	
	@When("^click the update button$")
	public void click_the_update_button() throws Throwable {
	    ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(UsernameUpdate.getUpdateButton(ServiceHooks.driver)));
	    PasswordUpdate.getUpdateButton(ServiceHooks.driver).click();
	    //This method is used for the Username update as well. 
	    //The buttons on each page can be found the exact same way, so this will not fail to find the button. 
	    //@Michael Tinning, Batch 1811
	}

	@Then("^A success message should appear on the page$")
	public void a_success_message_should_appear_on_the_page() throws Throwable {
		//Note that the success alert is not a true alert pop-up but a small message that appears on the page
		//If implementation changes to expect an Alert, use ExpCond.alertIsPresent(), which throws a NoAlertPresentExc. if there is no alert
		try {
			ServiceHooks.wait.until(ExpectedConditions.visibilityOf(MyInterviews.getSuccessAlert(ServiceHooks.driver)));
		} catch (TimeoutException e) {
			fail("Success message did not appear, update password failed");
		}
		/*Note: Checking the update persists in the system should either
		 *   for Username: reload the page and check for an update on the greeting in the NavBar. 
		 *   for Password: log out and log back in. 
		 * Changes are currently NOT IMPLEMENTED on the back-end, so the changes do not persist. 
		 */
	}
	//End Scenario: update password
	
	//Scenario: update Username
	@When("^I click on the update username option$")
	public void i_click_on_the_update_username_option() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(getBaseUrl() + getAssociateView()));
	    NavBar.getWelcomeDropdown(ServiceHooks.driver).click();
		ServiceHooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getPasswordUpdate())));
		NavBar.getUsernameUpdate(ServiceHooks.driver).click();
	}

	@When("^I enter a new username$")
	public void i_enter_a_new_username() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(UsernameUpdate.getNewUsername(ServiceHooks.driver)));
	    UsernameUpdate.getNewUsername(ServiceHooks.driver).sendKeys(/*newUsername*/);
	}

	@When("^I enter my password$")
	public void i_enter_my_password() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(UsernameUpdate.getNewUsername(ServiceHooks.driver)));
	    UsernameUpdate.getNewUsername(ServiceHooks.driver).sendKeys(/*userPassword*/);
	}

	//Should check for the username in the NavBar, currently not used 
	//Will need to implement this change on the backend and reload the page to check the change. 
//	@Then("^my username change should be reflected$")
//	public void my_username_change_should_be_reflected() throws Throwable {
//	    //Reload the page
		//ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(ServiceHooks.driver)));
		//Check the element's text() if it matches the change
		//Should also change the name back to the old name for good practice
//	}
	//End Scenario : Update Username
	
	//Scenario: navigate to the MyInterview tab
	@When("^I click the interview tab$")
	public void i_click_the_interview_tab() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(NavBar.getMyInterviews(ServiceHooks.driver)));
		NavBar.getMyInterviews(ServiceHooks.driver).click();
	}

	@Then("^I am on the interview view$")
	public void i_am_on_the_interview_view() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(getBaseUrl() + getMyInterviewView()));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), getBaseUrl() + getMyInterviewView());
	}
	//End Scenario: navigate to the MyInterview tab
	
	//Scenario: Creating a valid Interview
	@When("^I select a client$")
	public void i_select_a_client() throws Throwable {
		MyInterviews.getClientSelect(ServiceHooks.driver).click();
	    // Currently hard coded to select the second option in the drop down. i.e. 22nd Century Staffing Inc
	    MyInterviews.getClientSelectOptionsByIndex(ServiceHooks.driver, 1).click();
	}

	@When("^I select an interview type$")
	public void i_select_an_interview_type() throws Throwable {
		MyInterviews.getTypeSelect(ServiceHooks.driver).click();
		MyInterviews.getTypeSelectOptionsByValue(ServiceHooks.driver, getInterviewType()).click();
	}
	
	@When("^I enter an Interview date$")
	public void i_enter_an_Interview_date()  {
	    ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(MyInterviews.getInterviewDate(ServiceHooks.driver)));
	    MyInterviews.getInterviewDate(ServiceHooks.driver).sendKeys(getInterviewDate());
	}

	@When("^I select twenty-four hour notice$")
	public void i_select_twenty_four_hour_notice() throws Throwable {
		MyInterviews.get24HrNoticeCheckbox(ServiceHooks.driver).click();
	}

	@When("^press the add interview button$")
	public void press_the_add_interview_button() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(getBaseUrl() + getMyInterviewView()));
		setNumberOfInterviews();
		MyInterviews.getAddInterviewButton(ServiceHooks.driver).click();
	}

	@Then("^it should be in the interview table$")
	public void it_should_be_in_the_interview_table() throws Throwable {
		try {
			//Note that success alert is not an alert but a small message that appears on the page
			ServiceHooks.wait.until(ExpectedConditions.visibilityOf(MyInterviews.getSuccessAlert(ServiceHooks.driver)));
			assertTrue(compareNumberOfInterviews());
		} catch (TimeoutException e) {
			fail("Success message did not appear in a reasonable amount of time");
		}
	}
	//End Scenario: Creating a valid Interview
	
	//This step is re-used as the failure check for a majority of tests
	@Then("^an error popup should display$")
	public void an_error_popup_should_display() throws Throwable {
		try {
			//If the below throws an error, the alert did not appear
			ServiceHooks.wait.until(ExpectedConditions.alertIsPresent());
			ServiceHooks.driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException | TimeoutException e) {
			fail("Failure alert did not appear on the page");
		}
	}
	
	
	//Used to reset info update tests. 
	private String originalFirstName = "";
	private String originalLastName = "";
	private String getOFN() {
		return originalFirstName;
	}
	private String getOLN() {
		return originalLastName;
	}
	private void setOFN(String ofn) {
		this.originalFirstName = ofn;
	}
	private void setOLN(String oln) {
		this.originalLastName = oln;
	}
	private void reset_associate_information() {
		AssociateHome.getFirstNameInputField(ServiceHooks.driver).sendKeys(getOFN());
	    AssociateHome.getLastNameInputField(ServiceHooks.driver).sendKeys(getOLN());
	    AssociateHome.getSaveButton(ServiceHooks.driver).click();
	    setOFN("");
	    setOLN("");
	}
	
	//Used to detect a new row in the Interview table after positive submission
	private static Integer numInterviews = MyInterviews.getNumberOfInterviews(ServiceHooks.driver);
	private void setNumberOfInterviews() {
		numInterviews = MyInterviews.getNumberOfInterviews(ServiceHooks.driver);
	}
	private Boolean compareNumberOfInterviews() {
		return (numInterviews < MyInterviews.getNumberOfInterviews(ServiceHooks.driver)) ? true : false;
	}
}
