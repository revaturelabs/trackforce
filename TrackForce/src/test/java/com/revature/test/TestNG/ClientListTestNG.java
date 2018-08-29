package com.revature.test.TestNG;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.revature.test.admin.pom.ClientListTab;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;

import cucumber.api.java.en.When;

public class ClientListTestNG {
	static WebElement e = null;
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
	
	@BeforeSuite
	public void launchChromeDriver() {
		
	}
	
	@Test
	public static void click_client_list_tab() {
		try {
			//clicks the client tab
			ClientListTab.getClientTab(d).click();
			
		} catch (Throwable e) {
			fail("Failed to click Client List Tab");
			
		}
	}
	
	@Test
	public static void client_list_tab_loads() {
		Assert.assertEquals(ClientListTab.getCurrentURL(d),
				TestConfig.getBaseURL() + prop.getProperty("clientListingUrl"), "Failed to get current URL");
		
		if (ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + prop.getProperty("clientListingUrl")) ||
				ClientListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + prop.getProperty("clientListUrl"))) {
				
		}
	}
	
	@Test
	public static void client_list_panel_loads() {
		firstClient = ClientListTab.getClientNameFromList(d, 1).getText();
		secondClient = ClientListTab.getClientNameFromList(d, 2).getText();

		Assert.assertEquals(firstClient, 
				ClientListTab.getClientNameFromList(d, 1).getText(), "Failed to get first client from list");
			
		Assert.assertEquals(secondClient, 
				ClientListTab.getClientNameFromList(d, 2).getText(), "Failed to get second client from list");
	}
	
	@Test
	public static void search_bar_is_blank() {
		// clear the client list search box
		ClientListTab.getClientSearchBox(d).clear();
			
		Assert.assertEquals("Failed to clear search bar", 
				ClientListTab.getClientSearchBox(d).getAttribute("value"), "");
	}
	
	@Test
	public static void search_by_client_name() {
			currentClient = secondClient;
			search_bar_is_blank();
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			Assert.assertEquals(ClientListTab.getClientSearchBox(d).getAttribute("value"), 
					currentClient, "Failed to type name of client into search bar");
	}
	
	@Test
	public static void search_by_another_client_name() {
			currentClient = ClientListTab.getClientNameFromList(d, 1).getText();
			search_bar_is_blank();
			ClientListTab.getClientSearchBox(d).sendKeys(currentClient);
			
			Assert.assertEquals(ClientListTab.getClientSearchBox(d).getAttribute("value"),
					currentClient, "Failed to enter another client name into search bar");
	}
	
	@Test
	public static void client_should_be_at_top_of_search_results() {
		Assert.assertEquals(ClientListTab.getClientNameFromList(d, 1).getText(), 
				currentClient, "Failed to get the client's name at the top of the search results");
	}
	
	@Test
	public static void click_client_in_client_list() {
		Assert.assertEquals(ClientListTab.getClientNameFromList(d, 1).getText(), 
				currentClient, "Failed to click the client in the search results");
	}
	
	@AfterSuite
	public void closeChromeDriver() {
		
	}
}
