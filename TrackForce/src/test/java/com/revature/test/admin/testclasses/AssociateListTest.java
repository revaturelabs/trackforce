package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.revature.test.admin.cukes.AssociateListCukes;
import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.CreateUserTab;

public class AssociateListTest extends AdminSuite {

	@Test(priority = 1, enabled = true)
	// Click associate tab on navbar
	public void goToAssociateListTab() {
		try {

			assertTrue(AssociateListTab.tab(wd));
		} catch (Throwable e) {
			fail("Error: Failed to go to Associate List tab");
			e.printStackTrace();
		}
	}

	//Not finished
	@Test(priority = 2, enabled = false)
	public void filterByMarketingStatus() {

		try {
			assertTrue(AssociateListCukes.i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(wd));

		} catch (Throwable e) {

		}
	}

	//Sort by associate id in ascending order
	@Test(priority = 3, enabled = false)
	public void sortByAssociateIdInAscendingOrder() {
		try {
			// Click twice to sort in ascending order
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			 assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_the_associate_s_id_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by associate id in ascending order");
		}
	}

	//Sort by associate id in descending order
	@Test(priority = 4, enabled = false)
	public void sortByAssociateIdInDescendingOrder() {
		try {
			// One click sorts in descending order
			assertTrue(AssociateListCukes.i_click_the_associate_id_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_associate_id_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by associate id in descending order");
		}
	}
	
	
	//Sort by associate first name in ascending order
	@Test(priority = 5, enabled = false)
	public void sortByFirstNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by first name is ascending order");
		}
	}
	
	//Sort by associate first name in descending order
	//Failed
	@Test(priority = 6, enabled = false)
	public void sortByFirstNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by first name is descending order");
		}
	}
	
	//Sort by associate last name in ascending order
	@Test(priority = 7, enabled = false)
	public void sortByLastNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by last name is ascending order");
		}
	}
	
	//Sort by associate last name in descending order
	@Test(priority = 8, enabled = false)
	public void sortByLastNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by last name is descending order");
		}
	}
	
	//Sort by associate marketing status in ascending order
	@Test(priority = 9, enabled = false)
	public void sortByMarketinStatusInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by marketing status in ascending order");
		}
	}
	
	//Sort by associate marketing status in descending order
	@Test(priority = 10, enabled = false)
	public void sortByMarketinStatusInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by marketing status in descending order");
		}
	}
	
	//Sort by client name in ascending order
	@Test(priority = 11, enabled = false)
	public void sortByCientNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
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
			
			Thread.sleep(2000);
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
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_ascending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}
	
	//Sort by batch name in descending order
	@Test(priority = 13, enabled = true)
	public void sortByBatchNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			
			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_descending_order(wd));
			
		}catch(Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}
	
	@Test(priority = 14, enabled = true)
	public void close() {
		wd.close();
	}

}
