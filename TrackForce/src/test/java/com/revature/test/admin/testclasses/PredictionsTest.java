package com.revature.test.admin.testclasses;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.BatchListCukes;
import com.revature.test.admin.cukes.PredictionsCukes;


public class PredictionsTest extends AdminSuite {
	
	static int testNumber = 1;
	
	@BeforeTest
	public void NavigateToPredictionsTab() {
		System.out.println("============ Initializing Predictions Tests ===============");
		System.out.println("");
		
		try {
			assertTrue(PredictionsCukes.i_am_on_the_Predictions_page(wd));
			assertTrue(PredictionsCukes.predictions_tab_loads(wd));
		} catch (Throwable e) {
			fail("Error: Failed to navigate to Predictions Tab");
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void TestInfo() {
		System.out.println("--- Predictions Test #" + testNumber + " ---");
		testNumber++;
	}

	@Test(priority = 1)
	// Clicks Predictions Tab
	public void ClickPredictionsTab() {
		try {
			assertTrue(PredictionsCukes.i_input_a_start_date(wd));
			assertTrue(PredictionsCukes.i_input_a_end_date(wd));
			assertTrue(PredictionsCukes.i_enter_the_number_of_associates_needed(wd));
			assertTrue(PredictionsCukes.i_select_a_select_a_technology_from_a_drop_down(wd));
		} catch (Throwable e) {
			fail("Error: Failed to fill out form");
			e.printStackTrace();
		}
		
		try {
			assertTrue(PredictionsCukes.i_click_on_the_Prediction_button(wd));
			assertTrue(PredictionsCukes.i_should_see_a_table_displaying_the_results(wd));
		} catch (Throwable e) {
			fail("Error: Failed to load the table results");
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		System.out.println("============ Predictions Tests finished ===============");
	}
}
