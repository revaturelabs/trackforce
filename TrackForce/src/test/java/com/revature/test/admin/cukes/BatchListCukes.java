package com.revature.test.admin.cukes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.utils.TestConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchListCukes {

	@Given("^The Batch List Tab is clicked$")
	public static boolean the_Batch_List_Tab_is_clicked(WebDriver wd) throws Throwable {

		try {
			Thread.sleep(1500);
			BatchListTab.clickBatchListTab(wd).click();
			if (BatchListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/batch-listing")) {
				return true;
			}
			System.out.println("Current URL does not end with /batch-listing");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to confirm current URL ends in /batch-listing");
			return false;
		}
	}

	@Given("^Batch List Tab loads$")
	public static boolean batch_list_tab_loads(WebDriver d) {
		try {
			Thread.sleep(500);
			if (BatchListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/batch-listing")
					|| BatchListTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/batch-list")) {
				return true;
			}
			System.out.println("Current URL does not end with /batch-listing or /batch-list");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			return false;
		}
	}

	@Then("^All Batches text is visible$")
	public static boolean all_Batches_text_is_visible(WebDriver wd) throws Throwable {

		try {
			Thread.sleep(1000);
			// Find the Header to verify that you are in the Batch List Tab
			BatchListTab.findAllBatchesHeader(wd);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}

	@Given("^the first batch is clicked$")
	public static String the_first_batch_is_clicked(WebDriver wd) throws Throwable {
		// Click the first batch in the list
		String batchName = "";
		try {
			// Get the batch name to verify everyone in that batch is in correct batch
			batchName = BatchListTab.getFirstBatchName(wd).getText();
		} catch (Throwable e) {
			System.out.println("Failed to find first batch name");
		}
		try {
			// Click the first batch information
			BatchListTab.getFirstBatchName(wd).click();
		} catch (Throwable e) {
			System.out.println("Failed to click first batch name");
		}
		return batchName;
	}

	@When("^the list of associates is grabbed$")
	public static boolean the_list_of_associates_is_grabbed(WebDriver wd, String batchName) throws Throwable {
		try {
			// Gets the rows of associates and sends to helper function
			List<WebElement> associates = BatchListTab.getAssociatesInfo(wd);
			return associates_should_match_the_associate_list(wd, batchName, associates);

		} catch (Throwable e) {
			System.out.println("Could not get Associate list");
			return false;
		}
	}

	@Then("^associates should match the associate list$")
	public static boolean associates_should_match_the_associate_list(WebDriver wd, String batchName,
			List<WebElement> associates) throws Throwable {
		try {
			// Changes format to match the associates list in order to use .contains
			List<String> associatesList = new ArrayList<String>();
			for (WebElement e : associates) {
				associatesList.add(e.getText() + " " + batchName);
			}
			// Switch to Associate List Tab
			BatchListTab.clickAssociateListTab(wd).click();
			Thread.sleep(1500);

			// Grab the rows of associates in Associates List Tab
			List<WebElement> associateRows = BatchListTab.grabAssociatesBatchInfo(wd);
			// Use hashSet for faster lookup
			Set<String> listHash = new HashSet<String>();
			for (WebElement val : associateRows) {
				listHash.add(val.getText());
			}

			// Check associates list to see if the information in the Batch List matches up
			// with information in Associate List tab
			for (String info : associatesList) {
				if (!(listHash.contains(info))) {
					System.out.println(info + " not found");
					return false;
				}
			}
			// Go back to Batch List tab
			BatchListTab.clickBatchListTab(wd).click();
			// Verify that the page is Batch List Tab
			if (BatchListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/batch-listing")) {
				return true;
			}
			return true;
		} catch (Throwable e) {
			System.out.println("Can't grab associate list ID's");
			return false;
		}

	}

	
	public static boolean enter_From_Date(WebDriver wd) {
		try {
			BatchListTab.fromDateField(wd).sendKeys("9");
			BatchListTab.fromDateField(wd).sendKeys("15");
			BatchListTab.fromDateField(wd).sendKeys("2017");
			if (!(BatchListTab.fromDateField(wd).getText().contains("09/15/2017"))) {
				return false;
			}
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to enter data into From date field");
			return false;
		}
	}
	
	public static boolean enter_To_Date(WebDriver wd) {
		try {
			BatchListTab.toDateField(wd).sendKeys("11");
			BatchListTab.toDateField(wd).sendKeys("15");
			BatchListTab.toDateField(wd).sendKeys("2017");
			if (!(BatchListTab.fromDateField(wd).getText().contains("11/15/2017"))) {
				return false;
			}
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to enter data into To date field");
			return false;
		}
	}
	
	@When("^the From date is entered$")
	public static boolean the_From_date_is_entered(WebDriver wd) throws Throwable {
		try {
			// click the From date field
			BatchListTab.fromDateField(wd).click();
			// !!! BUG: ENTERING 0 IN YEAR RESETS THE DATE FIELD 
			BatchListTab.fromDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.fromDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.fromDateField(wd).sendKeys("0");		
			// enters 2017 as the year value
			BatchListTab.fromDateField(wd).sendKeys("2017");
			// automatically push the left arrow on the keyboard
			BatchListTab.fromDateField(wd).sendKeys(Keys.LEFT);
			// automatically push the left arrow on the keyboard
			BatchListTab.fromDateField(wd).sendKeys(Keys.LEFT);
			// enters 11 as the month value
			BatchListTab.fromDateField(wd).sendKeys("9");
			// enters 15 as the day value
			BatchListTab.fromDateField(wd).sendKeys("15");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to enter data into From date field");
			return false;
		}
	}

	@Given("^the To date is entered$")
	public static boolean the_To_date_is_entered(WebDriver wd) throws Throwable {
		try {
			// click to Date field
			BatchListTab.toDateField(wd).click();
			// !!! BUG: ENTERING 0 IN YEAR RESETS THE DATE FIELD 
			BatchListTab.fromDateField(wd).sendKeys("1");	
			BatchListTab.fromDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.fromDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.fromDateField(wd).sendKeys("0");	
			// enters 2017 as the year value
			BatchListTab.fromDateField(wd).sendKeys("2017");
			// automatically push the left arrow on the keyboard
			BatchListTab.fromDateField(wd).sendKeys(Keys.LEFT);
			// automatically push the left arrow on the keyboard
			BatchListTab.fromDateField(wd).sendKeys(Keys.LEFT);
			// enters 11 as the month value
			BatchListTab.fromDateField(wd).sendKeys("09");
			// enters 15 as the day value
			BatchListTab.fromDateField(wd).sendKeys("15");
			BatchListTab.fromDateField(wd).sendKeys(Keys.TAB);
			
			// !!! BUG: ENTERING 0 IN YEAR RESETS THE DATE FIELD 
			BatchListTab.toDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.toDateField(wd).sendKeys(Keys.RIGHT);
			BatchListTab.toDateField(wd).sendKeys("0");	
			// enters 2017 as the year value
			BatchListTab.toDateField(wd).sendKeys("2017");
			// automatically push the left arrow on the keyboard
			BatchListTab.toDateField(wd).sendKeys(Keys.LEFT);
			// automatically push the left arrow on the keyboard
			BatchListTab.toDateField(wd).sendKeys(Keys.LEFT);
			// enters 11 as the month value
			BatchListTab.toDateField(wd).sendKeys("11");
			// enters 15 as the day value
			BatchListTab.toDateField(wd).sendKeys("15");

			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}

	@When("^the submit button is clicked$")
	public static boolean the_submit_button_is_clicked(WebDriver wd) throws Throwable {
		try {
			// clicks the submit button
			BatchListTab.submitButton(wd).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Submit button was not clicked");
			return false;
		}
	}

	@Then("^the batch list should update to show only the batches which fit the entered criteria$")
	public static boolean the_batch_list_should_update_to_show_only_the_batches_which_fit_the_entered_criteria(
			WebDriver wd) throws Throwable {
		return BatchListTab.correctResults(BatchListTab.getStartDates(wd), BatchListTab.getEndDates(wd), wd);
	}

	@When("^the reset button is clicked$")
	public static boolean the_reset_button_is_clicked(WebDriver wd) throws Throwable {
		try {
			// clicks the reset button 
			BatchListTab.resetButton(wd).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Reset button was not clicked");
			return false;
		}
	}

	@Then("^the batch list should show all batches$")
	public static boolean the_batch_list_should_show_all_batches(WebDriver wd) throws Throwable {
		//verifies that all batches are showing 
		return BatchListTab.allBatchesAfterReset(BatchListTab.getStartDates(wd), BatchListTab.getEndDates(wd), wd);
	}
}
