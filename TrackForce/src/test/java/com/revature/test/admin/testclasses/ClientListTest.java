package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.ClientListCukes;
import com.revature.test.admin.cukes.AssociateListCukes;

public class ClientListTest extends AdminSuite {
	// static WebDriver e = null;

	@BeforeTest
	// Clicks Client List Tab and checks the URL
	public void beforeTest() {
		System.out.println("Running Client List Tab Tests");
		
		try {
			assertTrue(ClientListCukes.click_client_list_tab(wd));
			assertTrue(ClientListCukes.client_list_tab_loads(wd));
			assertTrue(ClientListCukes.client_list_panel_loads(wd));
		} catch (Throwable e) {
			fail("Error: Failed to navigate to Client List tab");
			e.printStackTrace();
		}
		try {
			assertTrue(ClientListCukes.search_bar_is_blank(wd));
		} catch (Throwable e) {
			fail("Error: Failed to initialize Client List tab tests");
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	// possible use case where a client name is searched, clicked, and the graph pertaining to that data is showing 
	public void SearchAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
			assertTrue(ClientListCukes.click_client_in_search_results(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name and view their data");
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	// possible use case where a user clicks view data for all clients button and verifies that the correct information is being displayed
	public void ViewAllClientData() {
		try {
			assertTrue(ClientListCukes.click_View_Data_for_All_Clients_button(wd));
			assertTrue(ClientListCukes.Total_Associates_header_is_visible(wd));
		} catch (Throwable e) {
			fail("Error: Failed to view all clients' data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	// possible use case where a user clicks on a client in the search results and verifies that the proper graph is displayed
	public void ClickAndViewClientData() {
		try {
			assertTrue(ClientListCukes.click_client_in_search_results(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to select client from list and view their data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 4)
	// possible use case where a client name is searched, then another client name is searched, clicked,
	// and the graph pertaining to that client is showing   
	public void SearchButSearchAnotherAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.search_by_another_client_name(wd));
			assertTrue(ClientListCukes.client_should_be_at_top_of_search_results(wd));
			assertTrue(ClientListCukes.click_client_in_search_results(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then search another client name and view their data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 5)
	// possible use case where a client name is searched, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickViewAllClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.search_bar_is_blank(wd));
			assertTrue(ClientListCukes.click_client_in_search_results(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then select client from list and view their data");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 6)
	// possible use case where a client is searched by name, the search bar is cleared, client is clicked, 
	// verifies that the correct data is being shown in the graph
	public void SearchButClickClientAndViewClientData() {
		try {
			assertTrue(ClientListCukes.search_by_client_name(wd));
			assertTrue(ClientListCukes.search_bar_is_blank(wd));
			assertTrue(ClientListCukes.click_client_in_search_results(wd));
			assertTrue(ClientListCukes.client_data_shows_in_graph(wd));
		} catch (Throwable e) {
			fail("Error: Failed to search client by name but then select client from list and view their data");
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("============ Client List Tests finished ===============");
	}
}
