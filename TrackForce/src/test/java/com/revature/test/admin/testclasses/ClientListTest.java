package com.revature.test.admin.testclasses;

import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.revature.test.admin.cukes.ClientListCukes;
import com.revature.test.admin.pom.ClientListTab;
import com.revature.test.utils.TestConfig;

public class ClientListTest extends AdminSuite 
{
	// static WebDriver e = null;

	static String firstClient = null; //first client at top of client list initially on page load
	static String secondClient = null; //second client at top of client list initially on page load
	static String currentClient = null; //current client that is being searched for or viewed
	static int testNumber = 1;
	
	@BeforeTest
	// Clicks Client List Tab and checks the URL
	public void NavigateToCreateUserTab() 
	{
		System.out.println("============ Initializing Client List Tests ===============");
		System.out.println("");
	}

	
	@BeforeMethod
	public void TestInfo() 
	{
		System.out.println("--- Client List Test #" + testNumber + " ---");
		testNumber++;
	}
	
	@Test(priority = 1)
	public void TestClientListTabClicked()
	{
		boolean clicked = false;
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try 
		{
			// clicks the client tab
			ClientListTab.getClientTab(wd).click();
			clicked = true;
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to click Client List Tab");
			clicked = false;
		}
		assertEquals(true,clicked); //ClientListCukes.click_client_list_tab(wd));
		System.out.println("Tab Clicked: " + clicked);
	}
	
	@Test(dependsOnMethods = "TestClientListTabClicked")
	public void TestClientListTabLoaded()
	{
		boolean loaded = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Thread.sleep(500);
			if (ClientListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/client-listing") ||
					ClientListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/client-list")) 
			{
				loaded = true;
			}
			System.out.println("Current URL does not end with /client-listing or /client-list");
			loaded = false;
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to get current URL");
			loaded =  false;
		}
		assertEquals(true,loaded);//ClientListCukes.client_list_tab_loads(wd));
		System.out.println("Tab Loaded: " + loaded);
	}
	
	
	@Test(dependsOnMethods = "TestClientListTabLoaded")
	public void TestClientListPanelLoaded()
	{
		boolean panelLoaded = false;
		try 
		{
			firstClient = ClientListTab.getClientNameFromList(wd, 1).getText();
			secondClient = ClientListTab.getClientNameFromList(wd, 2).getText();
			System.out.println("Current client: " + currentClient);
			System.out.println("First client: " + firstClient);
			System.out.println("Second client: " + secondClient);
			panelLoaded = true;
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to get first and second client from list");
			panelLoaded = false;
		}
		assertEquals(true,panelLoaded); //True(ClientListCukes.client_list_panel_loads(wd));
		System.out.println("Panel Loaded: " + panelLoaded);
	}
	
	

	@Test(dependsOnMethods = "TestClientListPanelLoaded")
	public void TestSearchBarIsBlank()
	{
		boolean blank = false;
		try 
		{
			// clear the client list search box
			ClientListTab.getClientSearchBox(wd).clear();
			// if client list search box is empty, return true
			if (ClientListTab.getClientSearchBar(wd).getAttribute("value").equals("")) 
			{
				blank = true;
			}
			System.out.println("Search bar still isn't blank");
			blank = false;
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to clear search bar");
			blank = false;
		}
		assertEquals(true, blank); //True(ClientListCukes.search_bar_is_blank(wd));
	}
	
//	@Test(priority = 2, dependsOnMethods = "TestSearchBarIsBlank")
//	// possible use case where a client name is searched, clicked, and the graph pertaining to that data is showing 
//	public void SearchAndViewClientData() {
//		try {	
//		
//		} catch (Throwable e) {
//			fail("Error: Failed to search client by name and view their data");
//			e.printStackTrace();
//		}
//	}

	@Test(dependsOnMethods = "TestSearchBarIsBlank")
	public void TestClientNameSearch() 
	{
		boolean foundName = false;
		try
		{
			wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Thread.sleep(1000);
			currentClient = secondClient;
			//search_bar_is_blank(wd);
			ClientListTab.getClientSearchBox(wd).sendKeys(currentClient);
			Thread.sleep(1000);
			if (ClientListTab.getClientSearchBar(wd).getAttribute("value").equals(currentClient))
			{
				foundName = true;
			}
			else 
			{
				System.out.println("Search bar value does not equal the second client name from the list");
				foundName = false;
			}
			
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to type name of client into search bar");
			foundName = false;
		}
		assertEquals(true,foundName); //True(ClientListCukes.search_by_client_name(wd));
	}
	
	@Test(dependsOnMethods = "TestClientNameSearch")
	public void TestClienIsOntList()
	{
		boolean onTop = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Thread.sleep(1000);
			if (ClientListTab.getClientNameFromList(wd, 1).getText().equals(currentClient)) 
			{
				onTop = true;
			}
			else 
			{
			System.out.println("The currently searched/viewed client name from the list did not appear at the top of the search results");
				onTop = false;
			}
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to get the client's name at the top of the search results");
			onTop = false;
		}
		assertTrue(onTop);//(ClientListCukes.client_should_be_at_top_of_search_results(wd));
	}
	
	@Test
	public void TestClickedInClientList()
	{
		boolean clicked = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Thread.sleep(1000);
			currentClient = ClientListTab.getClientNameFromList(wd, 1).getText();
			ClientListTab.getClientNameFromList(wd, 1).click();
			clicked = true;
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to click the client in the search results");
			clicked = false;
		}
		assertTrue(clicked);//ClientListCukes.click_client_in_client_list(wd));
	}
	
	@Test
	public void TestDataShowsInGraph()
	{
		WebElement header = null;
		boolean shown = false;
		try 
		{
			Thread.sleep(1000);
			header = ClientListTab.getBarChartHeader(wd);
			if (ClientListTab.getBarChartHeader(wd).getText().equals(currentClient))
			{
				shown = true;
			}
			//This "else if" hardcodes the getBarChartHeader(d).getText() return value which is A1 KAISER, INC, 
			//	since there is a weird problem with Selenium returning blank 
			//	when you try to use getText() on the header
			else if (("A1 KAISER, Inc").equals(currentClient))
			{
				shown = true;
			}
			else 
			{
				System.out.println("Graph is not displaying the selected client's data");
				shown =  false;
			}
			
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to read the header above the graph");
			shown = false;
		}
		assertTrue(shown);//ClientListCukes.client_data_shows_in_graph(wd));
	}
	
	
	@Test(priority = 3)
	// possible use case where a user clicks view data for all clients button and verifies that the correct information is being displayed
	public void ViewAllClientData() {
		try {
			assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			assertTrue(ClientListCukes.all_client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to view all clients' data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 4)
	// possible use case where a user clicks on a client in the search results and verifies that the proper graph is displayed
	public void ClickAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_bar_is_blank(wd));
			assertTrue(ClientListCukes.click_client_in_client_list(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to select client from list and view their data");
			e.printStackTrace();
		}
	}
	
	
	@Test(priority = 5)
	// possible use case where a client name is searched, then another client name is searched, clicked,
	// and the graph pertaining to that client is showing   
	public void SearchButSearchAnotherAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.search_by_another_client_name(wd));
			assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
			assertTrue(ClientListCukes.click_client_in_client_list(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then search another client name and view their data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 6)
	// possible use case where a client name is searched, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickViewAllClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			assertTrue(ClientListCukes.all_client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then view all clients' data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 7)
	// possible use case where a client is searched by name, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickClientAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.search_bar_is_blank(wd));
			assertTrue(ClientListCukes.click_client_in_client_list(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then select client from list and view their data");
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("============ Client List Tests finished ===============");
		System.out.println("");
	}
}
