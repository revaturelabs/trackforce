package com.revature.test.admin.cukes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.revature.test.admin.pom.ClientListTab;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.TestConfig;

public class ClientListCukes extends AdminSuite {
	static WebElement e = null;
	
	// ClientListCukes requires at least 2 clients in the list to work properly
    //	static String firstClient = ClientListTab.getClientNameFromList(wd, 1).getText();
    //	static String secondClient = ClientListTab.getClientNameFromList(wd, 2).getText();
	static String firstClient = null;
	static String secondClient = null;
	static String currentClient = null; //current client that is being searched for or viewed
	
	@Given("^I click on Client List Tab$")
	public static boolean click_client_list_tab(WebDriver d) {
		try {
			// clicks the client tab
			ClientListTab.getClientTab(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Client List Tab");
			return false;
		}
	}

	@Given("^Client List Tab loads$")
	public static boolean client_list_tab_loads(WebDriver d) {
		try {
			if (ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/client-listing") ||
					ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/client-list")) {
				return true;
			}
			System.out.println("Current URL does not end with /client-listing or /client-list");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			return false;
		}
	}

	@Given("^Client List panel loads$")
	public static boolean client_list_panel_loads(WebDriver d) {
		try {
			firstClient = ClientListTab.getClientNameFromList(wd, 1).getText();
			secondClient = ClientListTab.getClientNameFromList(wd, 2).getText();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to get first and second client from list");
			return false;
		}
	}
	
	
	
	@When("^I make sure the search bar is blank$")
	public static boolean search_bar_is_blank(WebDriver d) {
		try {
			// clear the client list search box
			ClientListTab.getClientSearchBox(d).clear();
			// if client list search box is empty, return true
			if (ClientListTab.getClientSearchBar(d).getAttribute("value").equals("")) {
				return true;
			}
			System.out.println("Search bar still isn't blank");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to clear search bar");
			return false;
		}
	}
	
	@When("^I type the name of a client into the search bar$")
	public static boolean search_by_client_name(WebDriver d) {
		try {
			currentClient = secondClient;
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			Thread.sleep(500);
			if (ClientListTab.getClientSearchBar(d).getAttribute("value").equals(currentClient)){
				return true;
			}
			System.out.println("Search bar value does not equal the second client name from the list");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to type name of client into search bar");
			return false;
		}
	}
	
	@When("^I want to enter a different client name into the search bar instead$")
	public static boolean search_by_another_client_name(WebDriver d) {
		try {
			currentClient = firstClient;
			search_bar_is_blank(d);
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			if (ClientListTab.getClientSearchBar(d).getAttribute("value").equals(currentClient)){
				return true;
			}
			System.out.println("Search bar value does not equal the first client name from the list");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to enter another client name into search bar");
			return false;
		}
	}
	

	@When("^I see only that client in the list$")
	public static boolean client_should_be_at_top_of_search_results(WebDriver d) {
		try {
			// if the client name at the top of the list matches the value of secondClient, return true
			if (ClientListTab.getClientNameFromList(d, 1).getText().equals(currentClient)) {
				return true;
			}
			System.out.println("The currently searched/viewed client name from the list did not appear at the top of the search results");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to get the client's name at the top of the search results");
			return false;
		}
	}
	
	@When("^I click the top client in the Clients list$")
	public static boolean click_client_in_search_results(WebDriver d) {
		try {
			// click the first client name from list
			currentClient = ClientListTab.getClientNameFromList(wd, 1).getText();
			ClientListTab.getClientNameFromList(d, 1).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click the client in the search results");
			return false;
		}
	}
	
	@Then("^The client's data should show in the graph$")
	public static boolean client_data_shows_in_graph(WebDriver d) {
		try {

			Thread.sleep(500);
			if (ClientListTab.getBarChartHeader(d).getText().equals(currentClient)){
				return true;
			}
			else { //Wait even longer, then check to see if header is updated now
				Thread.sleep(1000);
				if (ClientListTab.getBarChartHeader(d).getText().equals("Total Associates")) {
					return true;
				}
			}
			System.out.println("Graph is not displaying the selected client's data");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to read the header above the graph");
			return false;
		}
	}
	
	@When("^I click on the View Data for All Clients button$")
	public static boolean click_View_Data_for_All_Clients_button(WebDriver d) {
		try {
			// clicks the view data for all clients button
			ClientListTab.getViewDataForAllClientsButton(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click View Data for All Clients button");
			return false;
		}
	}
	
	@Then("^All clients' data should show in the graph$")
	public static boolean Total_Associates_header_is_visible(WebDriver d) {
		try {
			// if the bar chart header contains "Total Associates", return true
			Thread.sleep(500);
			if (ClientListTab.getBarChartHeader(d).getText().equals("Total Associates")) {
				return true;
			}
			else { //Wait even longer, then check to see if header is updated now
				Thread.sleep(1000);
				if (ClientListTab.getBarChartHeader(d).getText().equals("Total Associates")) {
					return true;
				}
			}
			System.out.println("Graph is not displaying all clients' data");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to read the header above the graph");
			return false;
		}
	}
}
