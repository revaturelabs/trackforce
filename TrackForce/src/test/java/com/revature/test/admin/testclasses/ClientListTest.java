package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.pom.ClientListTab;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClientListTest extends AdminSuite
{
	static WebDriver e = null;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Running Client List Tab Tests");
	}
	
	@Test
	@Given("^I am on the Client List Tab$")
	public void i_am_on_the_Client_List_Tab() throws Throwable {
	    ClientListTab.getClientTab(wd).click();	    
	    
	    assertTrue(ClientListTab.getClientTabHeader(wd).getText().equals("Clients"));
	}

	@Test
	@When("^I click on the Search by Client Name box$")
	public void i_click_on_the_Search_by_Client_Name_box() throws Throwable {
		ClientListTab.getClientSearchBox(wd).click();
		assertTrue(true);
	}

	@Test
	@When("^Enter some valid client name$")
	public void enter_some_valid_client_name() throws Throwable {
		ClientListTab.getClientSearchBox(wd).sendKeys("Infosys");
		
		assertTrue(ClientListTab.getClientSearchBox(wd).getText().equals("Infosys"));
	}

	@Then("^I should see only that client name in the list$")
	public void i_should_see_only_that_client_name_in_the_list() throws Throwable {
		assertTrue(ClientListTab.getClientNameFromList(wd, 1).equals("Infosys"));
	}

	@When("^I click on the View Data for All Clients button$")
	public void i_click_on_the_View_Data_for_All_Clients_button() throws Throwable {
	    ClientListTab.getViewDataForAllClientsButton(wd).click();
	    assertTrue(true);
	}

	@Then("^I should see Total Associates at the top of the bar graph$")
	public void i_should_see_Total_Associates_at_the_top_of_the_bar_graph() throws Throwable {
	    assertTrue(ClientListTab.getBarChartHeader(wd).equals("Total Associates"));
	}

	@When("^I click on a client in the Client List$")
	public void i_click_on_a_client_in_the_Client_List() throws Throwable {
		ClientListTab.getClientSearchBox(wd).clear();
		String clientName = ClientListTab.getClientNameFromList(wd, 3).getText();
		ClientListTab.getClientNameFromList(wd, 3).click();
		
		assertTrue(clientName.equals(ClientListTab.getBarChartHeader(wd).getText()));
	}

	@Then("^I should see that client name at the top of the bar graph$")
	public void i_should_see_that_client_name_at_the_top_of_the_bar_graph() throws Throwable {
		String barChartHeader = ClientListTab.getBarChartHeader(wd).getText();

		assertTrue(ClientListTab.getClientNameFromList(wd, 3).getText().equals(barChartHeader));
	}
}
