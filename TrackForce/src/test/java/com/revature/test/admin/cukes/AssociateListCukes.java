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

	
	  
	  @Given("^I know the clients$")
	  public static Set<String> i_know_the_clients(WebDriver driver) throws Throwable {
			
			WebElement e1 = null;
			WebElement inputText = AssociateListTab.searchByTextInputField(driver);
			Set<String> clientNames = new TreeSet<String>();
			List<WebElement> filteredListElements = new ArrayList<>();
			

			filteredListElements = AssociateListTab.clientNameList(driver);
			
			for(WebElement fe : filteredListElements) {
				clientNames.add(fe.getText());
			}

			return clientNames;
	  }

	  @When("^I input the client name in the search by input field$")
	  public static boolean i_input_the_client_name_in_the_search_by_input_field(WebDriver driver, String client) throws Throwable {
		  
		  	boolean isFiltered = false;
			WebElement inputText = AssociateListTab.searchByTextInputField(driver);
		  
			try {
					inputText.sendKeys(client);
					isFiltered = the_table_is_filtered_by_that_client(driver, inputText);
					inputText.clear();
				
			} catch (Throwable e) {
				System.out.println("Failed to Input value in Search By text input field");
			}
			
			return isFiltered;
	  }
	 
		//Input client matches client in table
		  @Then("^the table is filtered by that client$") public static boolean
		  the_table_is_filtered_by_that_client(WebDriver driver, WebElement txtField) throws Throwable {
		 
			  List <WebElement> filteredClients = AssociateListTab.clientNameList(driver);
			  
			  for(WebElement e : filteredClients) {
				  if(!txtField.getText().equals(e.getText())) {
					  return false;
				  }
				 // txtField.clear();
			  }
			  
			  return true;
		 }
		 
	  
	  
	  

	@When("^I select a marketing status value from the marketing status drop drown$")
	public static boolean i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(WebDriver driver)
			throws Throwable {
		return AssociateListTab.marketingStatusDropDown(driver);
	}

	/*
	 * @Then("^the table is filtered by that marketing status$") public static
	 * boolean the_table_is_filtered_by_that_marketing_status(WebDriver driver)
	 * throws Throwable {
	 * 
	 * 
	 * }
	 */

	@When("^I select a curriculum value from the curriculum drop down$")
	public static boolean i_select_a_curriculum_value_from_the_curriculum_drop_down(WebDriver driver) throws Throwable {
		return AssociateListTab.curriculumDropDown(driver);
	}

	/*
	 * @Then("^the table is filtered by that curriculum$") public static boolean
	 * the_table_is_filtered_by_that_curriculum(WebDriver driver) throws Throwable {
	 * 
	 * }
	 */

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

	// *******************SORTING CUKES ****************************************

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
	
	@When("^I click the client dropdown menu$")
	public static boolean i_click_the_client_dropdown_menu(WebDriver wd) throws Throwable {
		return AssociateListTab.clientDropDown(wd);
	}

	@Then("^The associate table shows all associates with the same client name$")
	public static boolean the_associate_table_shows_all_associates_with_the_same_client_name(WebDriver wd) throws Throwable {
		List<WebElement> clients = AssociateListTab.clientNameList(wd);
		for (WebElement e : clients) {
			if (!(e.getText().equals("Accenture"))) {
				return false;
			}
		}
		return true;
	}

}
