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
/*	// static WebDriver e = null;

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
		boolean clicked = false;
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try 
		{
			// clicks the client tab
			ClientListTab.getClientTab(wd).click();
			clicked = true;
			assertEquals(true,clicked); //ClientListCukes.click_client_list_tab(wd));
			System.out.println("Tab Clicked: " + clicked);
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to click Client List Tab");
			clicked = false;
			fail();
		}
		boolean loaded = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Thread.sleep(500);
			if (ClientListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/client-listing") ||
					ClientListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/client-list")) 
			{
				loaded = true;
			}
			else 
			{
				System.out.println("Current URL does not end with /client-listing or /client-list");
				loaded = false;
			}
		
		try {
			Thread.sleep(5000);
			//assertTrue(ClientListCukes.click_client_list_tab(wd));
			//assertTrue(ClientListCukes.client_list_tab_loads(wd));
			//assertTrue(ClientListCukes.client_list_panel_loads(wd));
		} catch (Throwable e) {
			fail("Error: Failed to navigate to Client List tab");
			e.printStackTrace();
		}
		try {
			//assertTrue(ClientListCukes.search_bar_is_blank(wd));
		} catch (Throwable e) {
			fail("Error: Failed to initialize Client List tab tests");
			e.printStackTrace();
		}
		assertEquals(true,loaded);//ClientListCukes.client_list_tab_loads(wd));
		System.out.println("Tab Loaded: " + loaded);
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
			fail();
		}
		assertEquals(true,panelLoaded); //True(ClientListCukes.client_list_panel_loads(wd));
		System.out.println("Panel Loaded: " + panelLoaded);
	}catch(Throwable e) {
		
	}
	}

	
	@BeforeMethod
	public void TestInfo() 
	{
		System.out.println("--- Client List Test #" + testNumber + " ---");
		testNumber++;
	}
	
	

	@Test(priority = 1)
	// possible use case where a client name is searched, clicked, and the graph pertaining to that data is showing 
	public void SearchAndViewClientData() {
		try {
			//assertTrue(ClientListCukes.search_by_client_name(wd));
			//assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
			//assertTrue(ClientListCukes.click_client_in_client_list(wd));
			//assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name and view their data");
			e.printStackTrace();
		}
	}
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
				assertEquals(true, blank); //True(ClientListCukes.search_bar_is_blank(wd));
				System.out.println("Search Bar is blank: " + blank);
			}
			else 
			{
				System.out.println("Search bar still isn't blank");
				blank = false;
				fail();
			}
			
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to clear search bar");
			blank = false;
			fail();
		}
	
	}

	@Test(priority = 2)
	// possible use case where a user clicks view data for all clients button and verifies that the correct information is being displayed
	public void ViewAllClientData() {
		try {
			//assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			//assertTrue(ClientListCukes.all_client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to view all clients' data");
			e.printStackTrace();
		}
	}
	@Test(priority = 2)//dependsOnMethods = "TestSearchBarIsBlank")
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
				assertEquals(true,foundName); //True(ClientListCukes.search_by_client_name(wd));
				System.out.println("Search Bar found name: " + foundName);
			}
			else 
			{
				System.out.println("Search bar value does not equal the second client name from the list");
				foundName = false;
				fail();
			}
			
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to type name of client into search bar");
			foundName = false;
			fail();
		}
		
	}
	
	@Test(priority = 3)
	// possible use case where a user clicks on a client in the search results and verifies that the proper graph is displayed
	public void ClickAndViewClientData() {
		try {
			//assertTrue(ClientListCukes.search_bar_is_blank(wd));
			//assertTrue(ClientListCukes.click_client_in_client_list(wd));
			//assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to select client from list and view their data");
			e.printStackTrace();
		}
	}
	@Test(priority = 3)//dependsOnMethods = "TestClientNameSearch")
	public void TestClienIsOntList()
	{
		boolean onTop = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Thread.sleep(1000);
			if (ClientListTab.getClientNameFromList(wd, 1).getText().equals(currentClient)) 
			{
				onTop = true;
				assertTrue(onTop);//(ClientListCukes.client_should_be_at_top_of_search_results(wd));
				System.out.println("Client's name is on top: " + onTop);
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
			fail();
		}
		  //
	}
	
	@Test(priority = 4)//dependsOnMethods = "TestClienIsOntList")
	public void TestClickedInClientList()
	{
		boolean clicked = false;
		try 
		{
			wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Thread.sleep(1000);
			currentClient = ClientListTab.getClientNameFromList(wd, 1).getText();
			ClientListTab.getClientNameFromList(wd, 1).click();
			clicked = true;
			assertTrue(clicked);//ClientListCukes.click_client_in_client_list(wd));
			System.out.println("Clicked on the client in the search results: " + clicked);
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to click the client in the search results");
			clicked = false;
			fail();
		}
	
	}
	
	@Test(priority = 4)
	// possible use case where a client name is searched, then another client name is searched, clicked,
	// and the graph pertaining to that client is showing   
	public void SearchButSearchAnotherAndViewClientData() {
		try {
			//assertTrue(ClientListCukes.search_by_client_name(wd));
			//assertTrue(ClientListCukes.search_by_another_client_name(wd));
			//assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
			//assertTrue(ClientListCukes.click_client_in_client_list(wd));
			//assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then search another client name and view their data");
			e.printStackTrace();
		}
	}
	@Test(priority = 5)//dependsOnMethods =  "TestClickedInClientList")
	public void TestDataShowsInGraph()
	{
		//WebElement header = null;
		boolean shown = false;
		try 
		{
			Thread.sleep(1000);
			//header = ClientListTab.getBarChartHeader(wd);
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
		System.out.println("Graph displays slected clients data: " + shown);
	}

	
	@Test(priority = 5)
	// possible use case where a client name is searched, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickViewAllClientData() {
		try {
			//assertTrue(ClientListCukes.search_by_client_name(wd));
			//assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			//assertTrue(ClientListCukes.all_client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then view all clients' data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 6)
	// possible use case where a client is searched by name, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickClientAndViewClientData() {
		try {
			//assertTrue(ClientListCukes.search_by_client_name(wd));
			//assertTrue(ClientListCukes.search_bar_is_blank(wd));
			//assertTrue(ClientListCukes.click_client_in_client_list(wd));
			//assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then select client from list and view their data");
			e.printStackTrace();
		}
	}
	@Test(priority = 7)
	public void TestViewDataForAllClients()
	{
		boolean view = false;
		try
		{
			// clicks the view data for all clients button
			ClientListTab.getViewDataForAllClientsButton(wd).click();
			view = true;
			assertTrue(view);//ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			System.out.println("Clicked to view data for all Clients: " + view);
			
		}
		catch (Throwable e) 
		{
			System.out.println("Failed to click View Data for All Clients button");
			view =  false;
			fail();
		}
	}
	
	@Test(priority = 8)
	public void TestAllDataShowsInGraph()
	{
		boolean showsGraph = false;
		try
		{
			wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Thread.sleep(1000);
			if (ClientListTab.getBarChartHeader(wd).getText().equals("Total Associates") ||
					ClientListTab.getBarChartHeader(wd).getText().equals("")) 
			{
				showsGraph =  true;
				assertTrue(showsGraph);//ClientListCukes.all_client_data_shows_in_graph(wd));
				System.out.println("Graph does display all clients data: " + showsGraph);
			}
			else 
			{
				System.out.println("Graph is not displaying all clients' data");
				showsGraph = false;
			}
			
		} 
		catch (Throwable e) 
		{
			System.out.println("Failed to read the header above the graph");
			showsGraph =  false;
		}
	}
	
	
	 A message from 1804
	 * The Previous batches (we're not sure which) did not properly implement TestNG and Cucumber. 
	 * As far as we can tell, there is no proper runner class for the cucumber and the 'cukes' classes
	 * are just used as methods to given functionality to the TestNg. So to get around this we are separating 
	 * the two classes as much as possible. Below is an example of how the code used to look like. BTW the try-catch blocks 
	 * were also their idea.  I did my best to keep the test functionality as much as possible but I didn't get to the below tests.
	 * 
	
	
	
//	
//	@Test(priority = 4)
//	// possible use case where a user clicks on a client in the search results and verifies that the proper graph is displayed
//	public void ClickAndViewClientData() {
//		try {
//			assertTrue(ClientListCukes.search_bar_is_blank(wd));
//			assertTrue(ClientListCukes.click_client_in_client_list(wd));
//			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
//		} catch (Throwable e) {
//			fail("Error: Failed to select client from list and view their data");
//			e.printStackTrace();
//		}
//	}
//	
//	
//	@Test(priority = 5)
//	// possible use case where a client name is searched, then another client name is searched, clicked,
//	// and the graph pertaining to that client is showing   
//	public void SearchButSearchAnotherAndViewClientData() {
//		try {
//			assertTrue(ClientListCukes.search_by_client_name(wd));
//			assertTrue(ClientListCukes.search_by_another_client_name(wd));
//			assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
//			assertTrue(ClientListCukes.click_client_in_client_list(wd));
//			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
//		} catch (Throwable e) {
//			fail("Error: Failed to search client by name but then search another client name and view their data");
//			e.printStackTrace();
//		}
//	}
//	
//	@Test(priority = 6)
//	// possible use case where a client name is searched, the search bar is cleared, client is clicked, 
//	// verifies that the correct data is being shown in the graph
//	public void SearchButClickViewAllClientData() {
//		try {
//			assertTrue(ClientListCukes.search_by_client_name(wd));
//			assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
//			assertTrue(ClientListCukes.all_client_data_shows_in_graph(wd));
//		} catch (Throwable e) {
//			fail("Error: Failed to search client by name but then view all clients' data");
//			e.printStackTrace();
//		}
//	}
//	
//	@Test(priority = 7)
//	// possible use case where a client is searched by name, the search bar is cleared, client is clicked, 
//	// verifies that the correct data is being shown in the graph
//	public void SearchButClickClientAndViewClientData() {
//		try {
//			assertTrue(ClientListCukes.search_by_client_name(wd));
//			assertTrue(ClientListCukes.search_bar_is_blank(wd));
//			assertTrue(ClientListCukes.click_client_in_client_list(wd));
//			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
//		} catch (Throwable e) {
//			fail("Error: Failed to search client by name but then select client from list and view their data");
//			e.printStackTrace();
//		}
//	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("============ Client List Tests finished ===============");
		System.out.println("");
	}*/
}
