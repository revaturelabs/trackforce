package com.revature.test.cuke;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.test.pom.AssociateHome;
import com.revature.test.pom.MyInterviews;
import com.revature.test.pom.NavBar;
import com.revature.test.utils.ServiceHooks;
import com.revature.utils.EnvManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateViewCukes {
	

	String baseUrl = EnvManager.NGTrackForce_URL;
	
	@Given("^I am on the Associate Home Page$")
	public void i_am_on_the_Associate_Home_Page() throws Throwable {
	System.out.println("I am on the associate homepage");
	ServiceHooks.wait.until(ExpectedConditions.urlToBe(baseUrl + "#/associate-view"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(),baseUrl + "#/associate-view");
	}

	@Then("^I should not be taken to the page$")
	public void i_should_not_be_taken_to_the_page() throws Throwable {
	    assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "#/associate-view");
	}
	
	@When("^I click the update info button$")
	public void i_click_the_update_info_button() throws Throwable {
		System.out.println("Clicking the update info button");
		AssociateHome.getSaveButton(ServiceHooks.driver).click();
	}

	@When("^I enter new name information$")
	public void i_enter_new_name_information() throws Throwable {
		AssociateHome.getResetButton(ServiceHooks.driver).click();
	    AssociateHome.getFirstNameInputField(ServiceHooks.driver).sendKeys("TestFirstName");
	    AssociateHome.getLastNameInputField(ServiceHooks.driver).sendKeys("TestLastName");
	    
	}

	@When("^I click the save button$")
	public void i_click_the_save_button() throws Throwable {
	    AssociateHome.getSaveButton(ServiceHooks.driver).click();
	}

	//Fix hard-coding
	@Then("^the changes should be reflected$")
	public void the_changes_should_be_reflected() throws Throwable {
	  assertEquals(AssociateHome.getCurrentFirstName(ServiceHooks.driver).getText(), "TestFirstName");
	  assertEquals(AssociateHome.getCurrentLastName(ServiceHooks.driver).getText(), "TestLastName");
	}
	
	@When("^I click the interview tab$")
	public void i_click_the_interview_tab() throws Throwable {
	    NavBar.getMyInterviews(ServiceHooks.driver);
	}

	@When("^I select a client$")
	public void i_select_a_client() throws Throwable {
	    MyInterviews.getClientSelect(ServiceHooks.driver).click();
	    // Currently hard coded to select the second option in the drop down.
	    MyInterviews.getClientSelectOptionsByIndex(ServiceHooks.driver, 1).click();
	}

	@When("^I enter an Interview date$")
	public void i_enter_an_Interview_date() throws Throwable {
	    // The MyInterview page does not have anywhere to input the interview date
		// Inputting interview date deeds to be reimplemented on front-end
	}

	@When("^I enter an Assigned date$")
	public void i_enter_an_Assigned_date() throws Throwable {
		// The MyInterview page does not have anywhere to input the assigned date.
		// Inputing Assigned date needs to be reimplemented on front-end
	}
	
	@When("^I select an interview type$")
	public void i_select_an_interview_type() throws Throwable {
		MyInterviews.getTypeSelect(ServiceHooks.driver).click();
		MyInterviews.getTypeSelectOptionsByIndex(ServiceHooks.driver, 1).click();
	}

	@When("^I select twenty-four hour notice$")
	public void i_select_twenty_four_hour_notice() throws Throwable {
	    MyInterviews.get24HrNoticeCheckbox(ServiceHooks.driver).click();
	}

	@When("^press the add interview button$")
	public void press_the_add_interview_button() throws Throwable {
	    MyInterviews.getAddInterviewButton(ServiceHooks.driver).click();
	}

	@Then("^it should be in the interview table$")
	public void it_should_be_in_the_interview_table() throws Throwable {
	   //Checks table to confirm the correct information was added.
	}

	@When("^I enter an Interview date that occurs after my Assigned date$")
	public void i_enter_an_Interview_date_that_occurs_after_my_Assigned_date() throws Throwable {
		// The MyInterview page does not have anywhere to input the assigned date.
	}

	@When("^I enter dates less than twenty-four hours apart$")
	public void i_enter_dates_less_than_twenty_four_hours_apart() throws Throwable {
		// The MyInterview page does not have anywhere to input the assigned date or interview date.
	}

	@When("^I enter dates more than twenty-four hours apart$")
	public void i_enter_dates_more_than_twenty_four_hours_apart() throws Throwable {
		// The MyInterview page does not have anywhere to input the assigned date or interview date.
	}

	
	@Then("^an error popup should display$")
	public void an_error_popup_should_display() throws Throwable {
		// There are currently no error popups implemented on the MyInterview page.
	}
}
