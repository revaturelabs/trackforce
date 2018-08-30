package com.revature.test.admin.cuke;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.revature.test.admin.pom.AssociateView;
import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.admin.pom.ClientListTab;
import com.revature.test.admin.pom.CreateUserTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.admin.pom.Predictions;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateViewCukes {
	
	@Given("^I am on the Associate Home Page$")
	public void i_am_on_the_Associate_Home_Page() throws Throwable {
	System.out.println("I am on the associate homepage");
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/#/associate-view");
	}

	@When("^I attempt to click the \"([^\"]*)\" tab$")
	public void i_attempt_to_click_the_tab(String tab) throws Throwable {
		System.out.println("I click on the " + tab + " tab.");
	    try {
		switch(tab) {
	    case "client": ClientListTab.getClientTab(ServiceHooks.driver).click();break;
	    case "batch": BatchListTab.clickBatchListTab(ServiceHooks.driver); break;
	    case "associate": BatchListTab.clickAssociateListTab(ServiceHooks.driver); break;
	    case "predictions": Predictions.clickPredictionsTab(ServiceHooks.driver); break;
	    case "create user": CreateUserTab.getCreateUserTab(ServiceHooks.driver); break;
	    }
		fail("associate able to access additional tabs");
	    }
	    catch(TimeoutException te) {
	    	assertTrue(true);
	    }
	    
	}

	@Then("^I should not be taken to the page$")
	public void i_should_not_be_taken_to_the_page() throws Throwable {
	    assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/#/associate-view");
	}
	
	@When("^I click the update info button$")
	public void i_click_the_update_info_button() throws Throwable {
	    AssociateView.clickUpdate(ServiceHooks.driver);
	}

	@When("^I enter new name information$")
	public void i_enter_new_name_information() throws Throwable {
	    AssociateView.enterFirstName(ServiceHooks.driver, "Test");
	    AssociateView.enterLastName(ServiceHooks.driver, "Associate");
	    
	}

	@When("^I click the save button$")
	public void i_click_the_save_button() throws Throwable {
	    AssociateView.clickSave(ServiceHooks.driver);
	}

	@Then("^the changes should be reflected$")
	public void the_changes_should_be_reflected() throws Throwable {
		Thread.sleep(2000);
	   assertEquals(AssociateView.getFirstName(ServiceHooks.driver),"Test");
	   assertEquals(AssociateView.getFirstName(ServiceHooks.driver),"Associate");
	}
	
	@When("^I click the interview tab$")
	public void i_click_the_interview_tab() throws Throwable {
	    AssociateView.clickInterviewTab(ServiceHooks.driver);
	}

	@When("^I click the createInterview button$")
	public void i_click_the_createInterview_button() throws Throwable {
	    AssociateView.clickCreateInterview(ServiceHooks.driver);
	    Thread.sleep(2000);
	}

	@When("^I select a client$")
	public void i_select_a_client() throws Throwable {
	    AssociateView.dropDown(ServiceHooks.driver, "None", "Client");
	}

	@When("^I enter an Interview date$")
	public void i_enter_an_Interview_date() throws Throwable {
	    AssociateView.enterInterviewDate(ServiceHooks.driver, "01022017");
	}

	@When("^I enter an Assigned date$")
	public void i_enter_an_Assigned_date() throws Throwable {
		AssociateView.enterAssignedDate(ServiceHooks.driver, "01012017");
	}

	@When("^I select a type$")
	public void i_select_a_type() throws Throwable {
		AssociateView.dropDown(ServiceHooks.driver, "On-site", "Type");
	}

	@When("^I select twenty-four hour notice$")
	public void i_select_twenty_four_hour_notice() throws Throwable {
	    AssociateView.toggleNotice(ServiceHooks.driver);
	}

	@When("^press the add interview button$")
	public void press_the_add_interview_button() throws Throwable {
	    AssociateView.clickInterviewTab(ServiceHooks.driver);
	}

	@Then("^it should be in the interview table$")
	public void it_should_be_in_the_interview_table() throws Throwable {
	   //check table
	}

	@When("^I enter an Interview date that occurs after my Assigned date$")
	public void i_enter_an_Interview_date_that_occurs_after_my_Assigned_date() throws Throwable {
		AssociateView.enterInterviewDate(ServiceHooks.driver, "01012017");
		AssociateView.enterAssignedDate(ServiceHooks.driver, "01022017");
	}

	@When("^I enter dates less than twenty-four hours apart$")
	public void i_enter_dates_less_than_twenty_four_hours_apart() throws Throwable {
		AssociateView.enterInterviewDate(ServiceHooks.driver, "01012017");
		AssociateView.enterAssignedDate(ServiceHooks.driver, "01012017");
	}

	@When("^I enter dates more than twenty-four hours apart$")
	public void i_enter_dates_more_than_twenty_four_hours_apart() throws Throwable {
		AssociateView.enterInterviewDate(ServiceHooks.driver, "01052017");
		AssociateView.enterAssignedDate(ServiceHooks.driver, "01012017");
	}

	
	@Then("^an error popup should display$")
	public void an_error_popup_should_display() throws Throwable {
		//check pop when implemented
	}
}
