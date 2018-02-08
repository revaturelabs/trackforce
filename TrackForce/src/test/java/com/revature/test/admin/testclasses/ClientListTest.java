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
	String nameFromList = null;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Running Client List Tab Tests");
	}
	
	@Test (priority = 1)
	@Given("^I am on the Client List Tab$")
	public void i_am_on_the_Client_List_Tab() throws Throwable {
	    ClientListTab.getClientTab(wd).click();	    
	    
	    assertTrue(ClientListTab.getClientTabHeader(wd).getText().equals("Clients"));
	}

	@Test (priority = 2)
	@When("^I click on the Search by Client Name box$")
	public void i_click_on_the_Search_by_Client_Name_box() throws Throwable {
		ClientListTab.getClientSearchBox(wd).click();
		
		assertTrue(true);
	}

	@Test (priority = 3)
	@When("^Enter some valid client name$")
	public void enter_some_valid_client_name() throws Throwable {
		nameFromList = ClientListTab.getClientNameFromList(wd, 2).getText();
		
		//gets the 2nd client from list
		// test will fail if there are less than 2 clients
		ClientListTab.getClientSearchBox(wd).sendKeys(nameFromList);
		
		String searchVal = ClientListTab.getInputTextPathFromClientSearch(wd).getAttribute("value");
		
		assertTrue(searchVal.equals(nameFromList));
	}

	@Test (priority = 4)
	@Then("^I should see only that client name in the list$")
	public void i_should_see_only_that_client_name_in_the_list() throws Throwable {
		assertTrue(ClientListTab.getClientNameFromList(wd, 1).getText().equals(nameFromList));
		
		ClientListTab.getClientNameFromList(wd, 1).click();
	}

	@Test (priority = 5)
	@When("^I click on the View Data for All Clients button$")
	public void i_click_on_the_View_Data_for_All_Clients_button() throws Throwable {
	    ClientListTab.getViewDataForAllClientsButton(wd).click();
	    
	    //ViewDataForAllClients button makes the bar chart header into "Total Associates"
	    assertTrue(ClientListTab.getBarChartHeader(wd).getText().equals("Total Associates"));
	}

	@Test (priority = 6)
	@Then("^I should see Total Associates at the top of the bar graph$")
	public void i_should_see_Total_Associates_at_the_top_of_the_bar_graph() throws Throwable {
		//pretty much same test as i_click_on_the_View_Data_for_All_Clients_button
	    assertTrue(ClientListTab.getBarChartHeader(wd).getText().equals("Total Associates"));
	}

	@Test (priority = 7)
	@When("^I click on a client in the Client List$")
	public void i_click_on_a_client_in_the_Client_List() throws Throwable {
		ClientListTab.getClientSearchBox(wd).clear();
		ClientListTab.getClientSearchBox(wd).sendKeys("a\b");
		String clientName = ClientListTab.getClientNameFromList(wd, 3).getText();
		ClientListTab.getClientNameFromList(wd, 3).click();
		
		//get 3rd client from the list and click on it, the bar chart should have
		// the same header
		//this test will fail if there are less than 3 clients
		assertTrue(clientName.equals(ClientListTab.getBarChartHeader(wd).getText()));
	}
	
	
	@Test (priority = 8)
	@Then("^I should see that client name at the top of the bar graph$")
	public void i_should_see_that_client_name_at_the_top_of_the_bar_graph() throws Throwable {
		String barChartHeader = ClientListTab.getBarChartHeader(wd).getText();
		String clientName = ClientListTab.getClientNameFromList(wd, 3).getText();
		
		//pretty much the same test as "i_click_on_a_client_in_the_Client_List"
		assertTrue(clientName.equals(barChartHeader));
	}
}
