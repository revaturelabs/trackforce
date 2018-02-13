package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.revature.test.admin.cukes.BatchListCukes;

public class BatchListTest extends AdminSuite {
	
	
	@BeforeTest
	public void beforeTest() {
		try {
			// Click Batch List Tab
			assertTrue(BatchListCukes.the_Batch_List_Tab_is_clicked(wd));
			
		} catch (Throwable e) {
			fail("Can't navigate to Batch List Tab");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	// Clicks Create user Tab and looks for the "All Batches" element
	public void FindAllBatchesTag() {
		try {
			assertTrue(BatchListCukes.all_Batches_text_is_visible(wd));
			
		} catch (Throwable e) {
			fail("Error: Failed to switch to Batch List Tab");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2) 
	// Clicks the first batch name, Grabs the names of all associates in the list, switches to Associate List tab, 
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
	
	@Test(priority = 3)
	// Clicks From date arrow
	public void ClickFromField() {
		try {
			// Click From date input field and enter data
			BatchListCukes.the_From_date_is_entered(wd);
		} catch (Throwable e) {
			fail("Can't click the From date arrow");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 4)
	// Clicks To date input field
	public void ClickToArrow() {
		try {
			// Click Batch List Tab
			BatchListCukes.the_To_date_is_entered(wd);
		} catch (Throwable e) {
			fail("Can't click the To date arrow");
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		System.out.println("============ Batch List Tests finished ===============");
	}
}
