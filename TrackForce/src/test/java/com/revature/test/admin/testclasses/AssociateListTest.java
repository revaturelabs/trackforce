package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.AssociateListCukes;
import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.CreateUserTab;

public class AssociateListTest extends AdminSuite {

	
	// Click associate tab on navbar
	@BeforeTest
	public void goToAssociateListTab() {
		try {

			assertTrue(AssociateListCukes.i_m_on_the_asssociate_list_page(wd));
		} catch (Throwable e) {
			fail("Error: Failed to go to Associate List tab");
			e.printStackTrace();
		}
	}

	//Not finished
	@Test(priority = 1, enabled = false)
	public void filterByMarketingStatus() {

		try {
			assertTrue(AssociateListCukes.i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(wd));

		} catch (Throwable e) {

		}
	}
	
	//************************ SORT ***************************************

	//Sort by associate id in ascending order
	@Test(priority = 2, enabled = false)
	public void sortByAssociateIdInAscendingOrder() {
		try {
			// Click twice to sort in ascending order
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));

			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_the_associate_s_id_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by associate id in ascending order");
		}
	}

	//Sort by associate id in descending order
	@Test(priority = 3, enabled = false)
	public void sortByAssociateIdInDescendingOrder() {
		try {
			// One click sorts in descending order
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));

			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_associate_id_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by associate id in descending order");
		}
	}
	
	
	//Sort by associate first name in ascending order
	@Test(priority = 4, enabled = false)
	public void sortByFirstNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by first name is ascending order");
		}
	}
	
	//Sort by associate first name in descending order
	//Failed
	@Test(priority = 5, enabled = false)
	public void sortByFirstNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by first name is descending order");
		}
	}
	
	//Sort by associate last name in ascending order
	@Test(priority = 6, enabled = false)
	public void sortByLastNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by last name is ascending order");
		}
	}
	
	//Sort by associate last name in descending order
	@Test(priority = 7, enabled = false)
	public void sortByLastNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by last name is descending order");
		}
	}
	
	//Sort by associate marketing status in ascending order
	@Test(priority = 8, enabled = false)
	public void sortByMarketinStatusInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by marketing status in ascending order");
		}
	}
	
	//Sort by associate marketing status in descending order
	@Test(priority = 9, enabled = false)
	public void sortByMarketinStatusInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by marketing status in descending order");
		}
	}
	
	//Sort by client name in ascending order
	@Test(priority = 10, enabled = false)
	public void sortByCientNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_client_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by client name in ascending order");
		}
	}
	
	//Sort by client name in descending order
	@Test(priority = 11, enabled = false)
	public void sortByCientNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_client_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by client name in descending order");
		}
	}
	
	//Sort by batch name in ascending order
	@Test(priority = 12, enabled = false)
	public void sortByBatchNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}
	
	//Sort by batch name in descending order
	@Test(priority = 13, enabled = false)
	public void sortByBatchNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			
			//Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}
	
	
	
	//*************************** FILTER **********************************
	
	//Filter by client name by entering in search text
	@Test(priority = 14, enabled = false)
	public void filterByClientNameSearch() {
		WebElement element;
		boolean isFiltered = false;
		try {
			//Thread.sleep(2000);
			Set<String> clientSet = AssociateListCukes.i_know_the_clients(wd);
			for(String s : clientSet) {
			
				isFiltered = AssociateListCukes.i_input_the_client_name_in_the_search_by_input_field(wd, s);
		
				//isFiltered = AssociateListCukes.the_table_is_filtered_by_that_client(wd, element);
			}
			assertTrue(isFiltered);
			/*
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_descending_order(wd));*/
			
		}catch(Throwable e) {
			fail("Failed to filter by searching client name");
		}
	}
	
	//Filter by client name by entering in search text
	@Test(priority = 15, enabled = false)
	public void filterByClientMenu() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_dropdown_menu(wd));
			assertTrue(AssociateListCukes.the_associate_table_shows_all_associates_with_the_same_client_name(wd));
			
		}catch(Throwable e) {
			fail("Failed to filter by searching client name");
		}
	}
	
	@AfterTest
	public void close() {
		wd.close();
	}

}
