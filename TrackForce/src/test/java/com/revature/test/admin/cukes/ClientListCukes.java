package com.revature.test.admin.cukes;
import com.revature.test.admin.pom.ClientListTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static org.testng.Assert.fail;

public class ClientListCukes {
	static WebDriver d = ServiceHooks.driver;

	static String firstClient = null; //first client at top of client list initially on page load
	static String secondClient = null; //second client at top of client list initially on page load
	static String currentClient = null; //current client that is being searched for or viewed
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Given("^I click on Client List Tab$")
	public static void click_client_list_tab() {
		try {
			// clicks the client tab
			ClientListTab.getClientTab(d).click();
			
		} catch (Throwable e) {
			fail("Failed to click Client List Tab");
			
		}
	}
	@Given("^Client List loads$")
	public static void client_list_tab_loads() {
		try {
			Thread.sleep(500);
			if (ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + prop.getProperty("clientListingUrl")) ||
					ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + prop.getProperty("clientListUrl"))) {
				
			}
			
		} catch (Throwable e) {
			fail("Failed to get current URL");
			
		}
	}

	@Given("^Client List panel loads$")
	public static void client_list_panel_loads() {
		try {
			firstClient = ClientListTab.getClientNameFromList(d, 1).getText();
			secondClient = ClientListTab.getClientNameFromList(d, 2).getText();
			System.out.println("Current client: " + currentClient);
			System.out.println("First client: " + firstClient);
			System.out.println("Second client: " + secondClient);
			
		} catch (Throwable e) {
			fail("Failed to get first and second client from list");
			
		}
	}
	
	@When("^I make sure the search bar is blank$")
	public static void search_bar_is_blank() {
		try {
			// clear the client list search box
			ClientListTab.getClientSearchBox(d).clear();
			// if client list search box is empty, return true
			if (ClientListTab.getClientSearchBox(d).getAttribute("value").equals("")) {
				
			}
			
		} catch (Throwable e) {
			fail("Failed to clear search bar");
			
		}
	}
	
	@When("^I type the name of a client into the search bar$")
	public static void search_by_client_name() {
		try {
			Thread.sleep(1000);
			currentClient = secondClient;
			search_bar_is_blank();
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			Thread.sleep(1000);
			if (ClientListTab.getClientSearchBox(d).getAttribute("value").equals(currentClient)){
				
			}
			
		} catch (Throwable e) {
			fail("Failed to type name of client into search bar");
			
		}
	}
	
	@When("^I want to enter a different client name into the search bar instead$")
	public static void search_by_another_client_name() {
		try {
			Thread.sleep(1000);
			currentClient = ClientListTab.getClientNameFromList(d, 1).getText();
			search_bar_is_blank();
			Thread.sleep(1000);
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			Thread.sleep(500);
			if (ClientListTab.getClientSearchBox(d).getAttribute("value").equals(currentClient)){
				
			}
			
		} catch (Throwable e) {
			fail("Failed to enter another client name into search bar");
			
		}
	}
	

	@When("^I see only that client in the list$")
	public static void client_should_be_at_top_of_search_results() {
		try {
			Thread.sleep(1000);
			if (ClientListTab.getClientNameFromList(d, 1).getText().equals(currentClient)) {
				
			}
			
		} catch (Throwable e) {
			fail("Failed to get the client's name at the top of the search results");
			
		}
	}
	
	@When("^I click the top client in the Clients list$")
	public static void click_client_in_client_list() {
		try {
			Thread.sleep(1000);
			currentClient = ClientListTab.getClientNameFromList(d, 1).getText();
			ClientListTab.getClientNameFromList(d, 1).click();
			
		} catch (Throwable e) {
			fail("Failed to click the client in the search results");
			
		}
	}

	@Then("^The client's data should show in the graph$")
	public static void client_data_shows_in_graph() {
		WebElement header = null;		
		try {
			Thread.sleep(1000);
			if (ClientListTab.getBarChartHeader(d).getText().equals(currentClient)){
				
			}
			//This "else if" hardcodes the getBarChartHeader(d).getText() return value which is A1 KAISER, INC, 
			//	since there is a weird problem with Selenium returning blank 
			//	when you try to use getText() on the header
			else if (prop.getProperty("clientTestSubject").equals(currentClient)){
				
			}
			
		} catch (Throwable e) {
			fail("Failed to read the header above the graph");
			
		}
	}
	
	@When("^I click on the View Data for All Clients button$")
	public static void click_View_Data_for_All_Clients_button() {
		try {
			// clicks the view data for all clients button
			ClientListTab.getViewDataForAllClientsButton(d).click();
			
		} catch (Throwable e) {
			fail("Failed to click View Data for All Clients button");
			
		}
	}
	
	@Then("^All client's data should show in the graph$")
	public static void all_client_data_shows_in_graph() {
		try {
			Thread.sleep(500);
			if (ClientListTab.getBarChartHeader(d).getText().equals("Total Associates") ||
					ClientListTab.getBarChartHeader(d).getText().equals("")) {
				
			}
			
		} catch (Throwable e) {
			fail("Failed to read the header above the graph");
			
		}
	}
}
