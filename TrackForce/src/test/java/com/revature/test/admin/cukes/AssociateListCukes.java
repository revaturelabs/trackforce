package com.revature.test.admin.cukes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.AssociateListTab;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateListCukes {

@Given("^I want to search by text$")
public static boolean i_want_to_search_by_text(WebDriver driver) throws Throwable {
    
    return AssociateListTab.tab(driver);
}

@When("^I input text in the search by text input field$")
public static boolean i_input_text_in_the_search_by_text_input_field(WebDriver driver) throws Throwable {
    
    return AssociateListTab.searchByTextInputField(driver);
}

/*
@Then("^The item is retrieved$")
public static boolean the_item_is_retrieved(WebDriver driver) throws Throwable {
    
    
}*/

/*
@Given("^I want to filter by marketing status$")
public static boolean i_want_to_filter_by_marketing_status(WebDriver driver) throws Throwable {
    
    
}*/

@When("^I select a marketing status value from the marketing status drop drown$")
public static boolean i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(WebDriver driver) throws Throwable {
    return AssociateListTab.marketingStatusDropDown(driver);
    
}

/*
@Then("^the table is filtered by that marketing status$")
public static boolean the_table_is_filtered_by_that_marketing_status(WebDriver driver) throws Throwable {
    
    
}*/

/*
@Given("^I want to filter by curriculum$")
public static boolean i_want_to_filter_by_curriculum(WebDriver driver) throws Throwable {
    
    
}*/

@When("^I select a curriculum value from the curriculum drop down$")
public static boolean i_select_a_curriculum_value_from_the_curriculum_drop_down(WebDriver driver) throws Throwable {
    return AssociateListTab.curriculumDropDown(driver);
    
}

/*
@Then("^the table is filtered by that curriculum$")
public static boolean the_table_is_filtered_by_that_curriculum(WebDriver driver) throws Throwable {
     
}*/

/*
@Given("^I want to filter by client$")
public static boolean i_want_to_filter_by_client(WebDriver driver) throws Throwable {
    
}*/

@When("^I select a client value from the client drop down$")
public static boolean i_select_a_client_value_from_the_client_drop_down(WebDriver driver) throws Throwable {
    return AssociateListTab.clientDropDown(driver);
    
}

/*
@Then("^the table is filtered by that client$")
public static boolean the_table_is_filtered_by_that_client(WebDriver driver) throws Throwable {
    
    
}*/

/*
@Given("^I want to edit an associates status$")
public static boolean i_want_to_edit_an_associates_status(WebDriver driver) throws Throwable {
    
    
}*/

@When("^I click an associate checkbox$")
public static boolean i_click_an_associate_checkbox(WebDriver driver) throws Throwable {
    return AssociateListTab.editCheckBox(driver);
}

@When("^I select a update by marketing status value from the update by marketing status drop down$")
public static boolean i_select_a_update_by_marketing_status_value_from_the_update_by_marketing_status_drop_down(WebDriver driver) throws Throwable {
    return AssociateListTab.updateByMarketingStatusDropDown(driver);
}

@When("^I click the update button$")
public static boolean i_click_the_update_button(WebDriver driver) throws Throwable {
    return AssociateListTab.updateButton(driver);
}

/*
@Then("^the information is updated$")
public static boolean the_information_is_updated(WebDriver driver) throws Throwable {
    
}*/

/*
@Given("^I want to sort the associate's table by associate id in ascending order$")
public static boolean i_want_to_sort_the_associate_s_table_by_associate_id_in_ascending_order(WebDriver driver) throws Throwable {
    
}*/

@When("^I click the associate id heading on the associate table$")
public static boolean i_click_the_associate_id_heading_on_the_associate_table(WebDriver driver) throws Throwable {
    
   return AssociateListTab.sortByAssociateId(driver); 
}


@Then("^The associate table is sorted by the associate's id in ascending order$")
public static boolean the_associate_table_is_sorted_by_the_associate_s_id_in_ascending_order(WebDriver driver) throws Throwable { 
	
	ArrayList<Integer> originalList = new ArrayList<>();
	ArrayList<Integer> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.associateIdList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText();
    	 originalList.add(Integer.parseInt(s));
    	 sortedList.add(Integer.parseInt(s));
    }
    
    Collections.sort(sortedList);
    //System.out.println("sortedList: " + sortedList.toString());
   // System.out.println("originalList: " + originalList.toString());
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by associate id in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_associate_id_in_descending_order(WebDriver driver) throws Throwable {
    
    
}*/


@Then("^The associate table is sorted by associate id in descending order$")
public static boolean the_associate_table_is_sorted_by_associate_id_in_descending_order(WebDriver driver) throws Throwable {
	ArrayList<Integer> originalList = new ArrayList<>();
	ArrayList<Integer> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.associateIdList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText();
    	 originalList.add(Integer.parseInt(s));
    	 sortedList.add(Integer.parseInt(s));
    }
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("associate id originalList: " + originalList.toString());
    System.out.println("associate sortedList descending: " + sortedList.toString());
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by first name in ascending order$")
public static boolean i_want_to_sort_the_associate_table_by_first_name_in_ascending_order(WebDriver driver) throws Throwable {
    
    
}*/

@When("^I click the first name heading on the associate table$")
public static boolean i_click_the_first_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {
    return AssociateListTab.sortByFirstName(driver);
}

//Sorted by first name is ascending order
@Then("^The associate table is sorted by first name in ascending order$")
public static boolean the_associate_table_is_sorted_by_first_name_in_ascending_order(WebDriver driver) throws Throwable { 
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.firstNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase(); //Sorting depends on upper case too.
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    System.out.println("firstname originalList: " + originalList.toString());
    System.out.println("firstname sortedList ascending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by first name in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_first_name_in_descending_order(WebDriver driver) throws Throwable {
    
}*/


@Then("^The associate table is sorted by first name in descending order$")
public static boolean the_associate_table_is_sorted_by_first_name_in_descending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.firstNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("firstname originalList: " + originalList.toString());
    System.out.println("firstname sortedList descending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by last name in ascending order$")
public static boolean i_want_to_sort_the_associate_table_by_last_name_in_ascending_order(WebDriver driver) throws Throwable {
    
}*/

@When("^I click the last name heading on the associate table$")
public static boolean i_click_the_last_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {
    return AssociateListTab.sortByLastName(driver);
    
}

//Sort in ascending order by last name
@Then("^The associate table is sorted by last name in ascending order$")
public static boolean the_associate_table_is_sorted_by_last_name_in_ascending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.lastNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    System.out.println("lastname originalList: " + originalList.toString());
    System.out.println("lastname sortedList ascending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by last name in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_last_name_in_descending_order(WebDriver driver) throws Throwable {
    
}*/


@Then("^The associate table is sorted by last name in descending order$")
public static boolean the_associate_table_is_sorted_by_last_name_in_descending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.lastNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("lastname originalList: " + originalList.toString());
    System.out.println("lastname sortedList descending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
    
}

/*
@Given("^I want to sort the associate table by marketing status in ascending order$")
public static boolean i_want_to_sort_the_associate_table_by_marketing_status_in_ascending_order(WebDriver driver) throws Throwable {

}*/

@When("^I click the marketing status heading on the associate table$")
public static boolean i_click_the_marketing_status_heading_on_the_associate_table(WebDriver driver) throws Throwable {
    return AssociateListTab.sortByMarketingStatus(driver);
}


@Then("^The associate table is sorted by marketing status in ascending order$")
public static boolean the_associate_table_is_sorted_by_marketing_status_in_ascending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.marketingStatusList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    //Collections.reverse(sortedList);
    System.out.println("marketing status originalList: " + originalList.toString());
    System.out.println("marketing status sortedList ascending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by marketing status in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_marketing_status_in_descending_order(WebDriver driver) throws Throwable {
}*/


@Then("^The associate table is sorted by marketing status in descending order$")
public static boolean the_associate_table_is_sorted_by_marketing_status_in_descending_order(WebDriver driver) throws Throwable { 
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.marketingStatusList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("marketing status originalList: " + originalList.toString());
    System.out.println("marketing status sortedList descending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by client name in ascending order$")
public static boolean i_want_to_sort_the_associate_table_by_client_name_in_ascending_order(WebDriver driver) throws Throwable {  
}*/

@When("^I click the client name heading on the associate table$")
public static boolean i_click_the_client_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {
    return AssociateListTab.sortByClient(driver);
}


@Then("^The associate table is sorted by client name in ascending order$")
public static boolean the_associate_table_is_sorted_by_client_name_in_ascending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.clientNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    //Collections.reverse(sortedList);
    System.out.println("client name originalList: " + originalList.toString());
    System.out.println("client name sortedList ascending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by client name in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_client_name_in_descending_order(WebDriver driver) throws Throwable {    
}*/


@Then("^The associate table is sorted by client name in descending order$")
public static boolean the_associate_table_is_sorted_by_client_name_in_descending_order(WebDriver driver) throws Throwable { 
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.clientNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("client name originalList: " + originalList.toString());
    System.out.println("client name sortedList descending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by batch name in ascending order$")
public static boolean i_want_to_sort_the_associate_table_by_batch_name_in_ascending_order(WebDriver driver) throws Throwable {
}*/


@When("^I click the batch name heading on the associate table$")
public static boolean i_click_the_batch_name_heading_on_the_associate_table(WebDriver driver) throws Throwable {
	return AssociateListTab.sortByBatch(driver);
}


@Then("^The associate table is sorted by batch name in ascending order$")
public static boolean the_associate_table_is_sorted_by_batch_name_in_ascending_order(WebDriver driver) throws Throwable {
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.batchNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    //Collections.reverse(sortedList);
    System.out.println("batch name originalList: " + originalList.toString());
    System.out.println("batch name sortedList ascending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}

/*
@Given("^I want to sort the associate table by batch name in descending order$")
public static boolean i_want_to_sort_the_associate_table_by_batch_name_in_descending_order(WebDriver driver) throws Throwable {
}*/


@Then("^The associate table is sorted by batch name in descending order$")
public static boolean the_associate_table_is_sorted_by_batch_name_in_descending_order(WebDriver driver) throws Throwable {
	
	ArrayList<String> originalList = new ArrayList<>();
	ArrayList<String> sortedList = new ArrayList<>();
	
	String s = null;
	
	//List with table elements 
    List<WebElement> list = AssociateListTab.batchNameList(driver);
    
    for(WebElement e : list) {
    	 s = e.getText().toUpperCase();
    	 originalList.add(s);
    	 sortedList.add(s);
    }
    
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println("batch name originalList: " + originalList.toString());
    System.out.println("batch name sortedList descending: " + sortedList.toString());
   
    
    //Comparing the two arraylists
    for(int i = 0; i < originalList.size(); i++) {
    	if(!originalList.get(i).equals(sortedList.get(i))){
    		return false;
    	}
    }
    
    return true;
}


	

}
