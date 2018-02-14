package com.revature.test.admin.cukes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collections;

public class AssociateListCukes {

	static WebElement element = null;
	static Set<String> searchValues = new TreeSet<String>();

	@Given("^I'm on the asssociate list page$")
	public static boolean i_m_on_the_asssociate_list_page(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.tab(driver);
			element.click();
			System.out.println("Clicked Associate List tab");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Associate List tab");
			return false;
		}

	}

	// *************** FILTERING BY SEARCH ***************

	// **************FILTER BY SEARCHING ASSOCIATE ID **********************
	@Given("^I know the associates ids$")
	public static void i_know_the_associates_ids(WebDriver driver) throws Throwable {

		List<WebElement> filteredListElements = new ArrayList<>();
		filteredListElements = AssociateListTab.associateIdList(driver);

		for (WebElement element : filteredListElements) {
			searchValues.add(element.getText());
		}
	}

	@When("^I input the associate id in the search by input field$")
	public static boolean i_input_the_associate_id_in_the_search_by_input_field(WebDriver driver) throws Throwable {
		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_the_associates_ids(driver);

		try {
			for (String id : searchValues) {
				element.sendKeys(id);
				System.out.println("Filter by searching id: " + id);
				// check to see if table is filtered by id
				isFiltered = the_table_is_filtered_by_that_associate_id(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input id in textfield");
		}

		return isFiltered;
	}

	@Then("^The table is filtered by that associate id$")
	public static boolean the_table_is_filtered_by_that_associate_id(WebDriver driver) throws Throwable {
		List<WebElement> filteredClients = AssociateListTab.associateIdList(driver);

		for (WebElement e : filteredClients) {
			if (!(e.getText().contains(element.getAttribute("value")))) {
				return false;
			}
		}

		return true;
	}

	// ************** FILTER BY SEARCHING FIRST NAME ********************
	@Given("^I know associates first name$")
	public static void i_know_associates_first_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.firstNameList(driver);

		for (WebElement element : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(element.getText());
		}
	}

	@When("^I input the associate first name in the search by input field$")
	public static boolean i_input_the_associate_first_name_in_the_search_by_input_field(WebDriver driver)
			throws Throwable {
		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_associates_first_name(driver);

		try {
			for (String firstName : searchValues) {
				element.sendKeys(firstName);
				System.out.println("Filter by searching first name: " + firstName);
				// check to see if table is filtered by first name
				isFiltered = the_table_is_filtered_by_that_first_name(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input first name in textfield");
		}

		return isFiltered;
	}

	@Then("^The table is filtered by that first name$")
	public static boolean the_table_is_filtered_by_that_first_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredClients = AssociateListTab.firstNameList(driver);

		for (WebElement e : filteredClients) {
			if (!(e.getText().contains(element.getAttribute("value")))) {
				return false;
			}
		}

		return true;
	}

	// ************** FILTER BY SEARCHING LAST NAME ******************

	@Given("^I know associates last name$")
	public static void i_know_associates_last_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.lastNameList(driver);

		for (WebElement element : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(element.getText());
		}
	}

	@When("^I input the associate last name in the search by input field$")
	public static boolean i_input_the_associate_last_name_in_the_search_by_input_field(WebDriver driver)
			throws Throwable {
		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_associates_last_name(driver);

		try {
			for (String lastName : searchValues) {
				element.sendKeys(lastName);
				System.out.println("Filter by searching last name: " + lastName);
				isFiltered = the_table_is_filtered_by_that_last_name(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input last name in textfield");
		}

		return isFiltered;
	}

	@Then("^The table is filtered by that last name$")
	public static boolean the_table_is_filtered_by_that_last_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredLastName = AssociateListTab.lastNameList(driver);

		for (WebElement e : filteredLastName) {
			if (!(e.getText().contains(element.getAttribute("value")))) {
				return false;
			}
			System.out.println("Found last name in table");
		}

		return true;
	}

	// ************ FILTER BY SEARCHING MARKETING STATUS ******************

	@Given("^I know associates marketing status$")
	public static void i_know_associates_marketing_status(WebDriver driver) throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.marketingStatusList(driver);

		for (WebElement element : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(element.getText());
		}
	}

	@When("^I input the associate marketing status in the search by input field$")
	public static boolean i_input_the_associate_marketing_status_in_the_search_by_input_field(WebDriver driver)
			throws Throwable {
		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_associates_marketing_status(driver);

		try {
			for (String status : searchValues) {
				element.sendKeys(status);
				System.out.println("Filter by searching marketing status: " + status);
				isFiltered = the_table_is_filtered_by_that_marketing_status(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input marketing status in textfield");
		}

		return isFiltered;
	}

	@Then("^The table is filtered by that marketing status$")
	public static boolean the_table_is_filtered_by_that_marketing_status(WebDriver driver) throws Throwable {
		List<WebElement> filteredStatus = AssociateListTab.marketingStatusList(driver);

		for (WebElement e : filteredStatus) {
			if (!(e.getText().contains(element.getAttribute("value")))) {
				return false;
			}
			System.out.println("Found status in table");
		}

		return true;
	}

	// ************* FILTER BY SEARCHING CLIENT NAME ****************

	@Given("^I know the clients$")
	public static void i_know_the_clients(WebDriver driver) throws Throwable {
		System.out.println("I know clients");

		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.clientNameList(driver);

		for (WebElement element : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(element.getText());
		}
	}

	@When("^I input the client name in the search by input field$")
	public static boolean i_input_the_client_name_in_the_search_by_input_field(WebDriver driver) throws Throwable {

		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_the_clients(driver);

		try {
			for (String client : searchValues) {
				element.sendKeys(client);
				System.out.println("Filter by searching client name: " + client);
				// check to see if table is filtered by client name
				isFiltered = the_table_is_filtered_by_that_client(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input client in textfield");
		}

		return isFiltered;
	}

	@Then("^the table is filtered by that client$")
	public static boolean the_table_is_filtered_by_that_client(WebDriver driver) throws Throwable {
		List<WebElement> filteredClients = AssociateListTab.clientNameList(driver);
		element = AssociateListTab.searchByTextInputField(driver);

		try {
			for (WebElement e : filteredClients) {

				if (!(e.getText().contains(element.getAttribute("value")))) {
					return false;
				}

			}
		} catch (Throwable e) {

		}
		return true;
	}

	// ************ FILTER BY SEARCHING BATCH NAME *******************
	@Given("^I know associates batch name$")
	public static void i_know_associates_batch_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredListElements = new ArrayList<>();

		filteredListElements = AssociateListTab.batchNameList(driver);

		for (WebElement element : filteredListElements) {
			// Thread.sleep(1000);
			searchValues.add(element.getText());
		}
	}

	@When("^I input the associate batch in the search by input field$")
	public static boolean i_input_the_associate_batch_in_the_search_by_input_field(WebDriver driver) throws Throwable {
		boolean isFiltered = false;
		element = AssociateListTab.searchByTextInputField(driver);

		i_know_associates_batch_name(driver);

		try {
			for (String batch : searchValues) {
				element.sendKeys(batch);
				System.out.println("Filter by searching batch name: " + batch);
				isFiltered = the_table_is_filtered_by_that_batch_name(driver);
				element.clear();
			}

		} catch (Throwable e) {
			System.out.println("Failed to input batch name in textfield");
		}

		return isFiltered;
	}

	@Then("^The table is filtered by that batch name$")
	public static boolean the_table_is_filtered_by_that_batch_name(WebDriver driver) throws Throwable {
		List<WebElement> filteredBatch = AssociateListTab.batchNameList(driver);

		for (WebElement e : filteredBatch) {
			if (!(e.getText().contains(element.getAttribute("value")))) {
				return false;
			}
			System.out.println("Found batch name in table");
		}

		return true;
	}

	// ********************************************************

	@When("^I select a marketing status value from the marketing status drop drown$")
	public static boolean i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(WebDriver driver)
			throws Throwable {
		return AssociateListTab.marketingStatusDropDown(driver);
	}

	@When("^I select a curriculum value from the curriculum drop down$")
	public static boolean i_select_a_curriculum_value_from_the_curriculum_drop_down(WebDriver driver) throws Throwable {
		return AssociateListTab.curriculumDropDown(driver);
	}

	@When("^I select a client value from the client drop down$")
	public static boolean i_select_a_client_value_from_the_client_drop_down(WebDriver driver) throws Throwable {
		return AssociateListTab.clientDropDown(driver);

	}

	@When("^I click an associate checkbox$")
	public static boolean i_click_an_associate_checkbox(WebDriver driver) throws Throwable {
		return AssociateListTab.editCheckBox(driver);
	}

	@When("^I select a update by marketing status value from the update by marketing status drop down$")
	public static boolean i_select_a_update_by_marketing_status_value_from_the_update_by_marketing_status_drop_down(
			WebDriver driver) throws Throwable {
		return AssociateListTab.updateByMarketingStatusDropDown(driver);
	}

	@When("^I click the update button$")
	public static boolean i_click_the_update_button(WebDriver driver) throws Throwable {
		return AssociateListTab.updateButton(driver);
	}

	/*
	 * @Then("^the information is updated$") public static boolean
	 * the_information_is_updated(WebDriver driver) throws Throwable {
	 * 
	 * }
	 */

	// *******************SORTING CUKES ***************************************
	
	// ***************** SORT BY ASSOCIATE ID (ASCENDING) ***********************

	@When("^I click the associate id heading on the associate table$")
	public static boolean i_click_the_associate_id_heading_on_the_associate_table(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.sortByAssociateId(driver);
			element.click();
			System.out.println("Clicked sort by associate id");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click sort by associate id");
			return false;
		}
	}

	@Then("^The associate table is sorted by the associate's id in ascending order$")
	public static boolean the_associate_table_is_sorted_by_the_associate_s_id_in_ascending_order(WebDriver driver)
			throws Throwable {

		ArrayList<Integer> originalList = new ArrayList<>();
		ArrayList<Integer> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.associateIdList(driver);

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
				return false;
			}
		}

		return true;
	}
	
	//********************* SORT BY ASSOCIATE ID (DESCENDING) ***************************

	@Then("^The associate table is sorted by associate id in descending order$")
	public static boolean the_associate_table_is_sorted_by_associate_id_in_descending_order(WebDriver driver)
			throws Throwable {
		ArrayList<Integer> originalList = new ArrayList<>();
		ArrayList<Integer> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.associateIdList(driver);

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
				return false;
			}
		}

		return true;
	}


	//****************** SORT BY FIRST NAME (ASCENDING) ******************
	
	@When("^I click the first name heading on the associate table$")
	public static boolean i_click_the_first_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.sortByFirstName(driver);
			element.click();
			System.out.println("Clicked first name heading");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click first name heading");
			return false;
		}
	}

	// Sorted by first name is ascending order
	@Then("^The associate table is sorted by first name in ascending order$")
	public static boolean the_associate_table_is_sorted_by_first_name_in_ascending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.firstNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	
	//***************** SORT BY FIRST NAME (DESCENDING) ***********************
	
	@Then("^The associate table is sorted by first name in descending order$")
	public static boolean the_associate_table_is_sorted_by_first_name_in_descending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.firstNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	//**************** SORT BY LAST NAME (ASCENDING) ******************************
	
	@When("^I click the last name heading on the associate table$")
	public static boolean i_click_the_last_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.sortByLastName(driver);
			element.click();
			System.out.println("Clicked last name heading");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click last name heading");
			return false;
		}

	}

	// Sort in ascending order by last name
	@Then("^The associate table is sorted by last name in ascending order$")
	public static boolean the_associate_table_is_sorted_by_last_name_in_ascending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.lastNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	//***************** SORTED BY LAST NAME (DESCENDING) *********************
	
	@Then("^The associate table is sorted by last name in descending order$")
	public static boolean the_associate_table_is_sorted_by_last_name_in_descending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.lastNameList(driver);

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
				return false;
			}
		}

		return true;

	}
	
	//************* SORT BY MARKETING STATUS (ASCENDING) ************************

	@When("^I click the marketing status heading on the associate table$")
	public static boolean i_click_the_marketing_status_heading_on_the_associate_table(WebDriver driver)
			throws Throwable {

		try {
			element = AssociateListTab.sortByMarketingStatus(driver);
			element.click();
			System.out.println("Clicked marketing status");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click marketing status");
			return false;
		}
	}

	@Then("^The associate table is sorted by marketing status in ascending order$")
	public static boolean the_associate_table_is_sorted_by_marketing_status_in_ascending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.marketingStatusList(driver);

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
				return false;
			}
		}

		return true;
	}

	//***************** SORT BY MARKETING STATUS (DESCENDING) *****************
	
	@Then("^The associate table is sorted by marketing status in descending order$")
	public static boolean the_associate_table_is_sorted_by_marketing_status_in_descending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.marketingStatusList(driver);

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
				return false;
			}
		}

		return true;
	}

	//************** SORT BY CLIENT NAME (ASCENDING) *****************************
	
	@When("^I click the client name heading on the associate table$")
	public static boolean i_click_the_client_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.sortByClient(driver);
			element.click();
			System.out.println("Clicked client name heading");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click client name heading");
			return false;
		}
	}

	@Then("^The associate table is sorted by client name in ascending order$")
	public static boolean the_associate_table_is_sorted_by_client_name_in_ascending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.clientNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	//************* SORT BY CLIENT NAME (DESCENDING) *********************
	
	@Then("^The associate table is sorted by client name in descending order$")
	public static boolean the_associate_table_is_sorted_by_client_name_in_descending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.clientNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	//************** SORT BY BATCH NAME (ASCENDING) **********************
	
	@When("^I click the batch name heading on the associate table$")
	public static boolean i_click_the_batch_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {

		try {
			element = AssociateListTab.sortByBatch(driver);
			element.click();
			System.out.println("Clicked batch name heading");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click batch name heading");
			return false;
		}
	}

	@Then("^The associate table is sorted by batch name in ascending order$")
	public static boolean the_associate_table_is_sorted_by_batch_name_in_ascending_order(WebDriver driver)
			throws Throwable {
		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.batchNameList(driver);

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
				return false;
			}
		}

		return true;
	}

	// *************** SORT BY BATCH NAME (DESCENDING) ************************
	
	@Then("^The associate table is sorted by batch name in descending order$")
	public static boolean the_associate_table_is_sorted_by_batch_name_in_descending_order(WebDriver driver)
			throws Throwable {

		ArrayList<String> originalList = new ArrayList<>();
		ArrayList<String> sortedList = new ArrayList<>();

		String s = null;

		// List with table elements
		List<WebElement> list = AssociateListTab.batchNameList(driver);

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
				return false;
			}
		}

		return true;
	}

}
