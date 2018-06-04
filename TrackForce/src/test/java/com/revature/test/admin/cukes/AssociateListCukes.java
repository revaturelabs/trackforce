package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateListCukes {
	
	static WebDriver d = ServiceHooks.driver;
	static WebElement e = null;
	static String searchValue=null; 
	static Set<String> searchValues = new TreeSet<String>();

	public static void i_am_on_the_asssociate_list_page() {

		try {
			Thread.sleep(500);
			AssociateListTab.getAssociateListTab(d).click();
			System.out.println("Clicked Associate List tab");
			
		} catch (Throwable e) {
			fail("Failed to click Associate List tab");
			
		}

	}

	public static void associate_list_tab_loads() {
		try {
			Thread.sleep(500);
			if (AssociateListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/associate-listing")
					|| AssociateListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/associate-list")) {
				
			}
			
		} catch (Throwable e) {
			fail("Failed to get current URL");
			
		}
	}
	
	@Then("^The table is filtered by search")
	public static void The_table_is_filtered_by_search(){
		try {
			List<WebElement> ids = AssociateListTab.associateIdList(d);
			List<WebElement> fNames = AssociateListTab.firstNameList(d);
			List<WebElement> lNames = AssociateListTab.lastNameList(d);
			List<WebElement> clients = AssociateListTab.clientNameList(d);
			List<WebElement> marketingStatus = AssociateListTab.marketingStatusList(d);
			List<WebElement> batchs = AssociateListTab.batchNameList(d);
			//making sure all lists have the same number of elements
			System.out.println(ids.size()+" "+fNames.size()+" "+lNames.size()+" "+marketingStatus.size()+" "+clients.size()+" "+batchs.size());
			assertEquals(ids.size(),fNames.size());
			assertEquals(marketingStatus.size(),clients.size());
			assertEquals(lNames.size(),fNames.size());
			assertEquals(ids.size(),clients.size());
			assertEquals(ids.size(),batchs.size());
			for(int i=0;i<ids.size();i++) {
				System.out.println(ids.get(i).getText()+" "+fNames.get(i).getText()+" "+lNames.get(i).getText()+" "+clients.get(i).getText()+" "+marketingStatus.get(i).getText()+" "+batchs.get(i).getText());
				System.out.println(searchValue);
				if(!(ids.get(i).getText().contains(searchValue)||fNames.get(i).getText().toLowerCase().contains(searchValue.toLowerCase())
						||lNames.get(i).getText().toLowerCase().contains(searchValue.toLowerCase())||clients.get(i).getText().toLowerCase().contains(searchValue.toLowerCase())
						||marketingStatus.get(i).getText().toLowerCase().contains(searchValue.toLowerCase())||batchs.get(i).getText().toLowerCase().contains(searchValue.toLowerCase()))) {
					fail("The table is filtered by search incorrectly");
				}
			}
			e.clear();
		}catch(Throwable t) {
			e.clear();
			fail("The table is filtered by search incorrectly");
		}
	}
	@Given("^I'm on the associate list page$")
	public static void im_on_the_associate_list_page() {
		i_am_on_the_asssociate_list_page();
		associate_list_tab_loads();
	}
	// *************** FILTERING BY SEARCH ***************

	// **************FILTER BY SEARCHING ASSOCIATE ID **********************
	@Given("^I know the associates ids$")
	public static void i_know_the_associates_ids() throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();
		try {
			filteredListElements = AssociateListTab.associateIdList(d);
			for (WebElement e : filteredListElements) {
				Integer.parseInt(e.getText());
			}
		}
		catch (Throwable e) {
			fail("Failed to get filtered associate list");
		}

		
	}

	@When("^I input the associate id in the search by input field$")
	public static void i_input_the_associate_id_in_the_search_by_input_field() throws Throwable {
		try {
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.associateIdList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(100);
				searchValue=filteredListElements.get(filteredListElements.size()-1).getText();
				e.sendKeys(filteredListElements.get(filteredListElements.size()-1).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input id in textfield");
		}

	}

	@Then("^The table is filtered by that associate id$")
	public static void the_table_is_filtered_by_that_associate_id() throws Throwable {
		try {
		int i=0;
		// creates a list of WebElements and populates it with associate ids
		List<WebElement> filteredClients = AssociateListTab.associateIdList(d);
		// loops through the list which was just created to check if the list contains the proper values  
		if(filteredClients.size()>=1) {
			for(WebElement id: filteredClients) {
				if(id.getText().contains(searchValue)) {
					i++;
				}
			}
		}
		e.clear();
		if(i==0) {
			fail();
		}
		}catch(Throwable e) {
			fail();
		}

		
	}

	// ************** FILTER BY SEARCHING FIRST NAME ********************
	@Given("^I know associates first name$")
	public static void i_know_associates_first_name() throws Throwable {
		try {
			AssociateListTab.firstNameList(d);
		}
		catch(Throwable e) {
			fail();
		}
	}

	@When("^I input the associate first name in the search by input field$")
	public static void i_input_the_associate_first_name_in_the_search_by_input_field()
			throws Throwable {

		try {
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.firstNameList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(100);
				searchValue=filteredListElements.get(filteredListElements.size()-1).getText();
				e.sendKeys(filteredListElements.get(filteredListElements.size()-1).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input name in textfield");
		}

	}

	@Then("^The table is filtered by that first name$")
	public static void the_table_is_filtered_by_that_first_name() throws Throwable {
		
		try {
			int i=0;
			// creates a list of WebElements and populates it with associate ids
			List<WebElement> filteredClients = AssociateListTab.firstNameList(d);
			// loops through the list which was just created to check if the list contains the proper values  
			if(filteredClients.size()>=1) {
				for(WebElement name: filteredClients) {
					if(name.getText().contains(searchValue)) {
						i++;
					}
				}
			}
			e.clear();
			if(i==0) {
				fail();
			}
			}catch(Throwable e) {
				fail();
			}

		
	}
	
	// ************** FILTER BY SEARCHING LAST NAME ******************

	@Given("^I know associates last name$")
	public static void i_know_associates_last_name() throws Throwable {
		
		// creates a list of WebElements and populates it with associate last names
		List<WebElement> filteredListElements = new ArrayList<>();

		// populates the list with associate last names
		filteredListElements = AssociateListTab.lastNameList(d);

		for (WebElement e : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(e.getText());
		}
	}

	@When("^I input the associate last name in the search by input field$")
	public static void i_input_the_associate_last_name_in_the_search_by_input_field()
			throws Throwable {
		try {
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.lastNameList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(100);
				searchValue=filteredListElements.get(filteredListElements.size()-1).getText();
				e.sendKeys(filteredListElements.get(filteredListElements.size()-1).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input name in textfield");
		}

	}

	@Then("^The table is filtered by that last name$")
	public static void the_table_is_filtered_by_that_last_name() throws Throwable {
		
		// creates a list of WebElements and populates it with associate last names
		List<WebElement> filteredLastName = AssociateListTab.lastNameList(d);

		// loops through the filteredClients list and checks whether the table is filtered by the last name
		for (WebElement e : filteredLastName) {
			if (!(e.getText().contains(e.getAttribute("value")))) {
				fail();
			}
			System.out.println("Found last name in table");
		}

		
	}

	// ************ FILTER BY SEARCHING MARKETING STATUS ******************

	@Given("^I know associates marketing status$")
	public static void i_know_associates_marketing_status() throws Throwable {
		// creates a list of WebElements and populates it with associate marketing status
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.marketingStatusList(d);

		for (WebElement e : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(e.getText());
		}
	}

	@When("^I input the associate marketing status in the search by input field$")
	public static void i_input_the_associate_marketing_status_in_the_search_by_input_field()
			throws Throwable {
		
		try {
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.marketingStatusList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(100);
				searchValue=filteredListElements.get(0).getText();
				e.sendKeys(filteredListElements.get(0).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input id in textfield");
		}


	}

	@Then("^The table is filtered by that marketing status$")
	public static void the_table_is_filtered_by_that_marketing_status() throws Throwable {
		try {
		List<WebElement> filteredStatus = AssociateListTab.marketingStatusList(d);

		for (WebElement e : filteredStatus) {
			if (!(e.getText().contains(e.getAttribute("value")))) {
				fail();
			}
			System.out.println("Found status in table");
		}
		}
		catch(Throwable e) {
			fail("The table is filtered by that marketing status");
		}

		
	}

	// ************* FILTER BY SEARCHING CLIENT NAME ****************

	@Given("^I know the clients$")
	public static void i_know_the_clients() throws Throwable {
		System.out.println("I know clients");

		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.clientNameList(d);

		for (WebElement e : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(e.getText());
		}
	}

	@When("^I input the client name in the search by input field$")
	public static void i_input_the_client_name_in_the_search_by_input_field() throws Throwable {

		
		try {
			Thread.sleep(2000);
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.clientNameList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(100);
				searchValue=filteredListElements.get(0).getText();
				e.sendKeys(filteredListElements.get(0).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input name in textfield");
		}


	}

	@Then("^The table is filtered by that client$")
	public static void the_table_is_filtered_by_that_client() throws Throwable {
		List<WebElement> filteredClients = AssociateListTab.clientNameList(d);
		e = AssociateListTab.searchByTextInputField(d);

		try {
			for (WebElement e : filteredClients) {

				if (!(e.getText().contains(e.getAttribute("value")))) {
					fail();
				}
				e.clear();
			}
		} catch (Throwable e) {
			fail();
		}
		
	}

	// ************ FILTER BY SEARCHING BATCH NAME *******************
	@Given("^I know associates batch name$")
	public static void i_know_associates_batch_name() throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.batchNameList(d);

		for (WebElement e : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(e.getText());
		}
	}

	@When("^I input the associate batch in the search by input field$")
	public static void i_input_the_associate_batch_in_the_search_by_input_field() throws Throwable {
		
		try {
			e = AssociateListTab.searchByTextInputField(d);
			List<WebElement> filteredListElements = AssociateListTab.batchNameList(d);
			if(!filteredListElements.isEmpty()) {
				e.clear();
				Thread.sleep(200);
				searchValue=filteredListElements.get(0).getText();
				e.sendKeys(filteredListElements.get(0).getText());
			}
		} catch (Throwable t) {
			e.clear();
			fail("Failed to input name in textfield");
		}

	}

	@Then("^The table is filtered by that batch name$")
	public static void the_table_is_filtered_by_that_batch_name() throws Throwable {
		List<WebElement> filteredBatch = AssociateListTab.batchNameList(d);

		for (WebElement e : filteredBatch) {
			if (!(e.getText().contains(e.getAttribute("value")))) {
				fail();
			}
			System.out.println("Found batch name in table");
		}

		
	}

	// ********************************************************

	/*
	 * @When("^I select a marketing status value from the marketing status drop drown$"
	 * ) public static void
	 * i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(
	 * river) throws Throwable { }
	 */

	@When("^I select a curriculum value from the curriculum drop down$")
	public static void i_select_a_curriculum_value_from_the_curriculum_drop_down() throws Throwable {

		try {
			e = AssociateListTab.curriculumDropDown(d);
			e.sendKeys(".NET");
			
		} catch (Throwable e) {
			fail();
		}

	}

	@Then("^the table is filtered by that curriculum$")
	public static void the_table_is_filtered_by_that_curriculum() throws Throwable {
		List<WebElement> filteredBatch = AssociateListTab.batchNameList(d);
		e = AssociateListTab.marketingStatusDropDown(d);
		for (WebElement e : filteredBatch) {
			if (!(e.getText().contains(e.getAttribute("value")))) {
				fail();
			}
			System.out.println("Found batch name in table");
		}
		

	}

	@When("^I select a client value from the client drop down$")
	public static void i_select_a_client_value_from_the_client_drop_down() throws Throwable {
		try {
			Thread.sleep(2000);
			e = AssociateListTab.clientUpdateDropDown(d);
			e.sendKeys("Revature LLC");
			System.out.println("Selected value from Client drop down");
			Thread.sleep(2000);
			
		} catch (Throwable e) {
			fail("Failed to select value from Client drop down");
			
		}

	}
	
	@Then("^The associate table shows all associates with the same client name$")
	public static void the_associate_table_shows_all_associtates_with_the_same_client_name() {
		try {
			List<WebElement> clientNames = AssociateListTab.clientNameList(d);
			String clientName= clientNames.get(0).getText();
			for(WebElement name:clientNames) {
				assertEquals(name.getText(),clientName);
			}
		}catch(Throwable e) {
			fail("did not show all associates with the same client name");
		}
	}
	
	@When("^I select a update by marketing status value from the update by marketing status drop down$")
	public static void i_select_a_update_by_marketing_status_value_from_the_update_by_marketing_status_drop_down(
			) throws Throwable {
		try {
			Thread.sleep(2000);
			e = AssociateListTab.updateByMarketingStatusDropDown(d);
			e.sendKeys("MAPPED: RESERVED");
			System.out.println("Selected value from Marketing Status drop down");
			Thread.sleep(2000);
			
		} catch (Throwable e) {
			fail("Failed to select value from Marketing Status drop down");
			
		}
	}

	@When("^I click an associate checkbox$")
	public static void i_click_an_associate_checkbox() throws Throwable {
		try {
			Thread.sleep(2000);
			e = AssociateListTab.editCheckBox(d);
			e.click();
			System.out.println("Clicked on the edit associate checkbox");
			Thread.sleep(2000);
			
		} catch (Throwable e) {
			fail("Failed to on the edit associate checkbox");
			
		}
	}

	@When("^I click the update button$")
	public static void i_click_the_update_button() throws Throwable {
		try {
			Thread.sleep(2000);
			e = AssociateListTab.updateButton(d);
			e.click();
			System.out.println("Clicked update button");
			Thread.sleep(4000);
			
		} catch (Throwable e) {
			fail("Failed to click update button");
			
		}
	}

	@Then("^the information is updated$")
	public static String the_information_is_updated() throws Throwable {
		String text = AssociateListTab.MarketingStatusText(d).getText();
		return text;

	}
	
	// *******************SORTING CUKES ***************************************

	// ***************** SORT BY ASSOCIATE ID (ASCENDING) ***********************

	@When("^I click the associate id heading on the associate table$")
	public static void i_click_the_associate_id_heading_on_the_associate_table() throws Throwable {

		try {
			e = AssociateListTab.sortByAssociateId(d);
			e.click();
			System.out.println("Clicked sort by associate id");
			
		} catch (Throwable e) {
			fail("Failed to click sort by associate id");
			
		}
	}

	@Then("^The associate table is sorted by the associate's id in ascending order$")
	public static void the_associate_table_is_sorted_by_the_associate_s_id_in_ascending_order()
			throws Throwable {

		ArrayList<Integer> originalList = new ArrayList<>();
		ArrayList<Integer> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.associateIdList(d);

		for (WebElement e : list) {
			s = e.getText();
			originalList.add(Integer.parseInt(s));
			sortedList.add(Integer.parseInt(s));
		}

		Collections.sort(sortedList);
		// System.out.println("sortedList: " + sortedList.toString());
		// System.out.println("originalList: " + originalList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ********************* SORT BY ASSOCIATE ID (DESCENDING)
	// ***************************

	@Then("^The associate table is sorted by associate id in descending order$")
	public static void the_associate_table_is_sorted_by_associate_id_in_descending_order()
			throws Throwable {
		ArrayList<Integer> originalList = new ArrayList<>();
		ArrayList<Integer> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.associateIdList(d);

		for (WebElement e : list) {
			s = e.getText();
			originalList.add(Integer.parseInt(s));
			sortedList.add(Integer.parseInt(s));
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("associate id originalList: " + originalList.toString());
		System.out.println("associate sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ****************** SORT BY FIRST NAME (ASCENDING) ******************

	@When("^I click the first name heading on the associate table$")
	public static void i_click_the_first_name_heading_on_the_associate_table() throws Throwable {

		try {
			e = AssociateListTab.sortByFirstName(d);
			e.click();
			System.out.println("Clicked first name heading");
			
		} catch (Throwable e) {
			fail("Failed to click first name heading");
			
		}
	}

	// Sorted by first name is ascending order
	@Then("^The associate table is sorted by first name in ascending order$")
	public static void the_associate_table_is_sorted_by_first_name_in_ascending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.firstNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase(); // Sorting depends on upper case too.
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		System.out.println("firstname originalList: " + originalList.toString());
		System.out.println("firstname sortedList ascending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ***************** SORT BY FIRST NAME (DESCENDING) ***********************

	@Then("^The associate table is sorted by first name in descending order$")
	public static void the_associate_table_is_sorted_by_first_name_in_descending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.firstNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("firstname originalList: " + originalList.toString());
		System.out.println("firstname sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// **************** SORT BY LAST NAME (ASCENDING) ******************************

	@When("^I click the last name heading on the associate table$")
	public static void i_click_the_last_name_heading_on_the_associate_table() throws Throwable {

		try {
			e = AssociateListTab.sortByLastName(d);
			e.click();
			System.out.println("Clicked last name heading");
			
		} catch (Throwable e) {
			fail("Failed to click last name heading");
			
		}

	}

	// Sort in ascending order by last name
	@Then("^The associate table is sorted by last name in ascending order$")
	public static void the_associate_table_is_sorted_by_last_name_in_ascending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.lastNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		System.out.println("lastname originalList: " + originalList.toString());
		System.out.println("lastname sortedList ascending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ***************** SORTED BY LAST NAME (DESCENDING) *********************

	@Then("^The associate table is sorted by last name in descending order$")
	public static void the_associate_table_is_sorted_by_last_name_in_descending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.lastNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("lastname originalList: " + originalList.toString());
		System.out.println("lastname sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		

	}

	// ************* SORT BY MARKETING STATUS (ASCENDING) ************************

	@When("^I click the marketing status heading on the associate table$")
	public static void i_click_the_marketing_status_heading_on_the_associate_table()
			throws Throwable {

		try {
			e = AssociateListTab.sortByMarketingStatus(d);
			e.click();
			System.out.println("Clicked marketing status");
			
		} catch (Throwable e) {
			fail("Failed to click marketing status");
			
		}
	}

	@Then("^The associate table is sorted by marketing status in ascending order$")
	public static void the_associate_table_is_sorted_by_marketing_status_in_ascending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.marketingStatusList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		// Collections.reverse(sortedList);
		System.out.println("marketing status originalList: " + originalList.toString());
		System.out.println("marketing status sortedList ascending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ***************** SORT BY MARKETING STATUS (DESCENDING) *****************

	@Then("^The associate table is sorted by marketing status in descending order$")
	public static void the_associate_table_is_sorted_by_marketing_status_in_descending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.marketingStatusList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("marketing status originalList: " + originalList.toString());
		System.out.println("marketing status sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ************** SORT BY CLIENT NAME (ASCENDING) *****************************

	@When("^I click the client name heading on the associate table$")
	public static void i_click_the_client_name_heading_on_the_associate_table() throws Throwable {

		try {
			e = AssociateListTab.sortByClient(d);
			e.click();
			System.out.println("Clicked client name heading");
			
		} catch (Throwable e) {
			fail("Failed to click client name heading");
			
		}
	}

	@Then("^The associate table is sorted by client name in ascending order$")
	public static void the_associate_table_is_sorted_by_client_name_in_ascending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.clientNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		// Collections.reverse(sortedList);
		System.out.println("client name originalList: " + originalList.toString());
		System.out.println("client name sortedList ascending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ************* SORT BY CLIENT NAME (DESCENDING) *********************

	@Then("^The associate table is sorted by client name in descending order$")
	public static void the_associate_table_is_sorted_by_client_name_in_descending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.clientNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("client name originalList: " + originalList.toString());
		System.out.println("client name sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// ************** SORT BY BATCH NAME (ASCENDING) **********************

	@When("^I click the batch name heading on the associate table$")
	public static void i_click_the_batch_name_heading_on_the_associate_table() throws Throwable {

		try {
			e = AssociateListTab.sortByBatch(d);
			e.click();
			System.out.println("Clicked batch name heading");
			
		} catch (Throwable e) {
			System.out.println("Failed to click batch name heading");
			fail();
		}
	}

	@Then("^The associate table is sorted by batch name in ascending order$")
	public static void the_associate_table_is_sorted_by_batch_name_in_ascending_order()
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.batchNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		// Collections.reverse(sortedList);
		System.out.println("batch name originalList: " + originalList.toString());
		System.out.println("batch name sortedList ascending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

	// *************** SORT BY BATCH NAME (DESCENDING) ************************

	@Then("^The associate table is sorted by batch name in descending order$")
	public static void the_associate_table_is_sorted_by_batch_name_in_descending_order()
			throws Throwable {

		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.batchNameList(d);

		for (WebElement e : list) {
			s = e.getText().toUpperCase();
			originalList.add(s);
			sortedList.add(s);
		}

		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("batch name originalList: " + originalList.toString());
		System.out.println("batch name sortedList descending: " + sortedList.toString());

		// Comparing the two arraylists
		for (int i = 0; i < originalList.size(); i++) {
			if (!originalList.get(i).equals(sortedList.get(i))) {
				fail();
			}
		}

		
	}

}
