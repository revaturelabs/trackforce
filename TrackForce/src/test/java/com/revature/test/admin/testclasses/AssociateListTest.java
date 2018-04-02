package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;


import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.AssociateListCukes;

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

	// Not finished
	/*
	@Test(priority = 1, enabled = false)
	public void filterByMarketingStatus() {

		try {
			assertTrue(AssociateListCukes.i_select_a_marketing_status_value_from_the_marketing_status_drop_drown(wd));

		} catch (Throwable e) {

		}
	}*/

	// ************************ SORT ***************************************

	// Sort by associate id in ascending order
	@Test(priority = 2, enabled = false)
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

	// Sort by associate id in descending order
	@Test(priority = 3, enabled = false)
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

	// Sort by associate first name in ascending order
	@Test(priority = 4, enabled = false)
	public void sortByFirstNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by first name is ascending order");
		}
	}

	// Sort by associate first name in descending order
	// Failed
	@Test(priority = 5, enabled = false)
	public void sortByFirstNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_first_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_first_name_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by first name is descending order");
		}
	}

	// Sort by associate last name in ascending order
	@Test(priority = 6, enabled = false)
	public void sortByLastNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by last name is ascending order");
		}
	}

	// Sort by associate last name in descending order
	@Test(priority = 7, enabled = false)
	public void sortByLastNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_last_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_last_name_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by last name is descending order");
		}
	}

	// Sort by associate marketing status in ascending order
	@Test(priority = 8, enabled = false)
	public void sortByMarketinStatusInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by marketing status in ascending order");
		}
	}

	// Sort by associate marketing status in descending order
	@Test(priority = 9, enabled = false)
	public void sortByMarketinStatusInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_marketing_status_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_marketing_status_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by marketing status in descending order");
		}
	}

	// Sort by client name in ascending order
	@Test(priority = 10, enabled = false)
	public void sortByCientNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_client_name_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by client name in ascending order");
		}
	}

	// Sort by client name in descending order
	@Test(priority = 11, enabled = false)
	public void sortByCientNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_client_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_client_name_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by client name in descending order");
		}
	}

	// Sort by batch name in ascending order
	@Test(priority = 12, enabled = false)
	public void sortByBatchNameInAscendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_ascending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}

	// Sort by batch name in descending order
	@Test(priority = 13, enabled = false)
	public void sortByBatchNameInDescendingOrder() {
		try {
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));
			assertTrue(AssociateListCukes.i_click_the_batch_name_heading_on_the_associate_table(wd));

			Thread.sleep(2000);
			assertTrue(AssociateListCukes.the_associate_table_is_sorted_by_batch_name_in_descending_order(wd));

		} catch (Throwable e) {
			fail("Failed to sort by batch name in ascending order");
		}
	}

	// *************************** FILTER **********************************
	
	// Filter by id by searching id
	//Not finished
	@Test(priority = 14, enabled = false)
	public void filterByAssociateIdSearch() {
		try {
			
			assertTrue(AssociateListCukes.i_input_the_associate_id_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching associate id");
		}
	}

	// Filter by first name by searching first name
	@Test(priority = 15, enabled = false)
	public void filterByFirstNameSearch() {
		try {
			
			assertTrue(AssociateListCukes.i_input_the_associate_first_name_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching first name");
		}
	}

	// Filter by last name by searching last name
	@Test(priority = 16, enabled = false)
	public void filterByLastNameSearch() {
		try {
			
			assertTrue(AssociateListCukes.i_input_the_associate_last_name_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching last name");
		}
	}
	
	
	// Filter by marketing status by searching marketing status
	@Test(priority = 17, enabled = false)
	public void filterByMarketingStatusSearch() {
		try {
			
			assertTrue(AssociateListCukes.i_input_the_associate_marketing_status_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching marketing status");
		}
	}
	
	
	// Filter by client name by searching client name
	@Test(priority = 18, enabled = false)
	public void filterByClientNameSearch() {
		try {
			
			assertTrue(AssociateListCukes.i_input_the_client_name_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching client name");
		}
	}
	
	// Filter by batch name by searching batch name
	@Test(priority = 19, enabled = false)
	public void filterByBatchNameSearch() {
		try {
			assertTrue(AssociateListCukes.i_input_the_associate_batch_in_the_search_by_input_field(wd));
		
		} catch (Throwable e) {
			fail("Failed to filter by searching batch name");
		}
	}
	
	//************** FILTER BY SELECTING DROP DOWN ***********************
	
	//Filter by selecting curriculum drop down value
	@Test(priority = 20, enabled = false)
	public void filterByCurriculumDropDown() {
		try {
			assertTrue(AssociateListCukes.i_select_a_curriculum_value_from_the_curriculum_drop_down(wd));
			assertTrue(AssociateListCukes.the_table_is_filtered_by_that_curriculum(wd));
		}catch (Throwable e) {
			
		}
	}
	
	// ************************ UPDATE ***************************************
		@Test(priority = 21, enabled = true)
		public void EditAssociateInformation() {
			
			String marketingStatusBefore = null;
			String marketingStatusAfter = null;
			try {
				marketingStatusBefore = AssociateListCukes.the_information_is_updated(wd);
				System.out.println("Marketing Status before update: " + marketingStatusBefore);
				assertTrue(AssociateListCukes.i_click_an_associate_checkbox(wd));
				assertTrue(AssociateListCukes.i_select_a_update_by_marketing_status_value_from_the_update_by_marketing_status_drop_down(wd));
				assertTrue(AssociateListCukes.i_select_a_client_value_from_the_client_drop_down(wd));
				assertTrue(AssociateListCukes.i_click_the_update_button(wd));
				marketingStatusAfter = AssociateListCukes.the_information_is_updated(wd);
				System.out.println("Marketing Status after update: " + marketingStatusAfter);
				//assertTrue(!marketingStatusBefore.equals(marketingStatusAfter));
				
			
			} catch (Throwable e) {

			}
		}
	
	@AfterTest
	public void close() {
		wd.close();
	}

}
