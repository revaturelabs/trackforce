package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.revature.test.admin.cukes.BatchListCukes;

public class BatchListTest extends AdminSuite {

	static int testNumber = 1;
	
	@BeforeTest
	public void NavigateToBatchListTab() {
		System.out.println("============ Initializing Batch List Tests ===============");
		System.out.println("");
		
		// Click Batch List Tab
		try {
			assertTrue(BatchListCukes.the_Batch_List_Tab_is_clicked(wd));
			assertTrue(BatchListCukes.batch_list_tab_loads(wd));
		} catch (Throwable e) {
			fail("Error: Failed to navigate to Batch List Tab");
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void TestInfo() {
		System.out.println("--- Batch List Test #" + testNumber + " ---");
		testNumber++;
	}
	
	@Test(priority = 1)
	// Clicks Create user Tab and looks for the "All Batches" element
	public void FindAllBatchesTag() {
		try {
			assertTrue(BatchListCukes.all_Batches_text_is_visible(wd));

		} catch (Throwable e) {
			fail("Error: Failed to load Batch List Tab");
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	// Clicks the first batch name, Grabs the names of all associates in the list,
	// switches to Associate List tab,
	// compares the names to associates in the first batch clicked.
	public void BatchNameTest() {
		try {
			String batch = BatchListCukes.the_first_batch_is_clicked(wd);
			assertTrue(BatchListCukes.the_list_of_associates_is_grabbed(wd, batch));
		} catch (Throwable e) {
			fail("Error: Batch Name Test Failed");
			e.printStackTrace();
		}
	}

	// Enter a valid date into the From date field. BUG: If you enter 0 into the year date, dates will reset
	@Test(priority = 3)
	public void InputFromDateFields() {
		try {
			assertTrue(BatchListCukes.enter_From_Date(wd));
		} catch (Throwable e) {
			fail("Error: Could not input a valid date into 'from' date field");
		}
	}
	
	// Enter a valid date into the To date field. BUG: If you enter 0 into the year date, dates will reset
	@Test(priority = 4)
	public void InputToDateFields() {
		try {
			assertTrue(BatchListCukes.enter_To_Date(wd));
		} catch (Throwable e) {
			fail("Error: Could not input a valid date into 'to' date field");
		}
	}
	
	@Test(priority = 5)
	// Clicks From date input field and enters data
	public void ClickFromField() {
		try {
			BatchListCukes.the_From_date_is_entered(wd);
		} catch (Throwable e) {
			fail("Can't click the From date arrow");
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	// Clicks To date input field and enters data
	public void ClickToField() {
		try {
			BatchListCukes.the_To_date_is_entered(wd);
		} catch (Throwable e) {
			fail("Can't click the To date arrow");
			e.printStackTrace();
		}
	}

	@Test(priority = 7)
	// Clicks submit button
	public void ClickSubmit() throws Throwable {
		assertTrue(BatchListCukes.the_submit_button_is_clicked(wd));
	}

	@Test(priority = 8)
	// Checks if the batch list is correct after submitting, according to the dates
	// in the To and From fields
	public void areResultsCorrect() throws Throwable {
		assertTrue(BatchListCukes
				.the_batch_list_should_update_to_show_only_the_batches_which_fit_the_entered_criteria(wd));
	}

	@Test(priority = 9)
	// Clicks the reset button
	public void ClickReset() throws Throwable {
		assertTrue(BatchListCukes.the_reset_button_is_clicked(wd));
	}

	@Test(priority = 10)
	// Checks if the batch list is correct after resetting the To and From fields to
	// default values (no restrictions)
	public void allBatchesShowing() throws Throwable {
		assertTrue(BatchListCukes.the_batch_list_should_show_all_batches(wd));
		Thread.sleep(1000);
	}

	@Test(priority = 11)
	//possible use case where a user would enter dates into the From and To fields, 
	//	clicks submit, and sees the proper batches appear 
	public void useCase1() throws Throwable {
		assertTrue(BatchListCukes.the_From_date_is_entered(wd));
		Thread.sleep(1000);
		assertTrue(BatchListCukes.the_To_date_is_entered(wd));
		Thread.sleep(1000);
		assertTrue(BatchListCukes.the_submit_button_is_clicked(wd));
		Thread.sleep(1000);
		assertTrue(BatchListCukes.the_batch_list_should_show_all_batches(wd));
	}

	@AfterTest
	public void afterTest() {
		System.out.println("============ Batch List Tests finished ===============");
		System.out.println("");
	}
}
