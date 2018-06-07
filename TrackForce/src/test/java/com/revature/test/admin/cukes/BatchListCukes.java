package com.revature.test.admin.cukes;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchListCukes {

	private static String baseURL = TestConfig.getBaseURL(); //gets the website URL
	public static Alert alert = null; //creates object to interact with alerts in order to cancel pop ups
	private static WebDriver wd = ServiceHooks.driver;
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Given("^I am logged in$")
	public static void I_am_logged_in() throws Throwable{
		wd.get(baseURL);
		LoginUtil.loginAsAdmin(wd);
		alert = wd.switchTo().alert();
	}
	
	@When("^The Batch List Tab is clicked$")
	public static void the_Batch_List_Tab_is_clicked() throws Throwable {
		Thread.sleep(1500);
		BatchListTab.clickBatchListTab(wd).click();
		if (!(wd.getCurrentUrl().contains(prop.getProperty("batchListingUrl")))) {
			throw new PendingException();
		}
	}

	@Given("^Batch List Tab loads$")
	public static void batch_list_tab_loads() {
		try {
			String url = BatchListTab.getCurrentURL(wd);
			Thread.sleep(500);
			if (!(url.equals(TestConfig.getBaseURL() + prop.getProperty("batchListingUrl"))
					|| url.equals(TestConfig.getBaseURL() + prop.getProperty("batchListUrl")))) {
				throw new PendingException();
			}
		}  catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("^All Batches text is visible$")
	public static void all_Batches_text_is_visible() throws Throwable {

		try {
			Thread.sleep(1000);
			// Find the Header to verify that you are in the Batch List Tab
			if(!(BatchListTab.findAllBatchesHeader(wd).equals(prop.getProperty("batchHeaderText")))) {
				throw new PendingException();
			}
		}  catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Given("^the first batch is clicked$")
	public static String the_first_batch_is_clicked() throws Throwable {
		// Click the first batch in the list
		String batchName = "";
		try {
			// Get the batch name to verify everyone in that batch is in correct batch
			batchName = BatchListTab.getFirstBatchName(wd).getText();
		} catch (Exception e) {
			fail("Failed to find first batch name");
		}
		try {
			// Click the first batch information
			BatchListTab.getFirstBatchName(wd).click();
		} catch (Exception e) {
			fail("Failed to click first batch name");
		}
		return batchName;
	}

	@When("^the list of associates is grabbed$")
	public static void the_list_of_associates_is_grabbed(String batchName) throws Throwable {
		try {
			// Gets the rows of associates and sends to helper function
			List<WebElement> associates = BatchListTab.getAssociatesInfo(wd);
			associates_should_match_the_associate_list(batchName, associates);

		} catch (Exception e) {
			fail("Could not get Associate list");
		}
	}

	@Then("^associates should match the associate list$")
	public static void associates_should_match_the_associate_list(String batchName,
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
				}
			}
			// Go back to Batch List tab
			BatchListTab.clickBatchListTab(wd).click();
			// Verify that the page is Batch List Tab
			if (BatchListTab.getCurrentURL(wd).equals(TestConfig.getBaseURL() + "/batch-listing")) {
			}
		} catch (Exception e) {
			fail("Can't grab associate list ID's");
		}
	}

	public static void enter_From_Date() {
//		BatchListTab.fromDateField(wd).sendKeys("12");
//		BatchListTab.fromDateField(wd).sendKeys("25");
//		BatchListTab.fromDateField(wd).sendKeys("2017");
		BatchListTab.fromDateField(wd).sendKeys("12252017");
	}
	
	public static void enter_To_Date() {
//		BatchListTab.toDateField(wd).sendKeys("2");
//		BatchListTab.toDateField(wd).sendKeys("25");
//		BatchListTab.toDateField(wd).sendKeys("2018");
		BatchListTab.toDateField(wd).sendKeys("2252018");
	}
	
	@When("^the From date is entered$")
	public static void the_From_date_is_entered() throws Throwable {
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
			// enters 9 as the month value
			BatchListTab.fromDateField(wd).sendKeys("9");
			// enters 15 as the day value
			BatchListTab.fromDateField(wd).sendKeys("15");
		} catch (Exception e) {
			System.out.println("Failed to enter data into From date field");
		}
	}

	@Given("^the To date is entered$")
	public static void the_To_date_is_entered() throws Throwable {
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
			// enters 9 as the month value
			BatchListTab.fromDateField(wd).sendKeys("9");
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

		} catch (Exception e) {
			System.out.println("Failed to find All Batches header");
		}
	}

	@When("^the submit button is clicked$")
	public static void the_submit_button_is_clicked() throws Throwable {
		try {
			// clicks the submit button
			BatchListTab.submitButton(wd).click();
		} catch (Exception e) {
			fail("Submit button was not clicked");
		}
	}

	@Then("^the batch list should update to show only the batches which fit the entered criteria$")
	public static void the_batch_list_should_update_to_show_only_the_batches_which_fit_the_entered_criteria(
			) throws Throwable {
		BatchListTab.correctResults(BatchListTab.getStartDates(wd), BatchListTab.getEndDates(wd), wd);
	}

	@When("^the reset button is clicked$")
	public static void the_reset_button_is_clicked() throws Throwable {
		try {
			// clicks the reset button 
			BatchListTab.resetButton(wd).click();
		} catch (Exception e) {
			System.out.println("Reset button was not clicked");
		}
	}

	@Given("^batches are showing$")
	public static void batches_are_showing() throws Throwable {
	    enter_From_Date();
	    enter_To_Date();
		if (!(BatchListTab.fromDateField(wd).getText().contains("12/25/2017"))) {
		    throw new PendingException();
		}
	}
	
	@When("^I click on a specific batch name$")
	public static void i_click_on_a_specific_batch_name() throws Throwable {
		try {
			wd.findElement(By.linkText(prop.getProperty("batchTestBatchName"))).click();
		} catch(Exception e) {
			fail("Failed to click on batch name");
		}
//		if(!(wd.getCurrentUrl().contains("batch-details/49"))) {
//			throw new PendingException();
//		}
	}
	
	@Then("^I should be taken to the appropriate details page$")
	public static void i_should_be_taken_to_the_appropriate_details_page() throws Throwable {
		if(!(wd.getCurrentUrl().contains(prop.getProperty("batchTestBatchUrl")))) {
			throw new PendingException();
		}
	}
	
	@Given("^I am looking at batch details$")
	public static void i_am_looking_at_batch_details() throws Throwable {
		if(!(wd.getCurrentUrl().contains(prop.getProperty("batchTestBatchUrl")))) {
			throw new PendingException();
		}
	}

	@When("^I click on an associate ID$")
	public static void i_click_on_an_associate_ID() throws Throwable {
		try {
		wd.findElement(By.linkText(prop.getProperty("batchTestAssociateId"))).click();
		} catch(Exception e) {
			fail("Failed to click associate ID");
		}
	}

	@Then("^I should be taken to the appropriate information page$")
	public static void i_should_be_taken_to_the_appropriate_information_page() throws Throwable {
		if(!(wd.getCurrentUrl().contains(prop.getProperty("batchTestAssociateUrl")))) {
			throw new PendingException();
		}
	}
	
	//Enter values, click submit, ng-reflect-model changes when that happens
	@Then("^the From field should not contain default values$")
	public void the_From_field_should_not_contain_default_values() throws Throwable {
	    if((BatchListTab.fromDateContents(wd)) == prop.getProperty("batchDefaultStartDate")) {
	    	throw new PendingException();
	    }
	}

	@Then("^the To field should not contain default values$")
	public void the_To_field_should_not_contain_default_values() throws Throwable {
	    if((BatchListTab.toDateContents(wd)) == prop.getProperty("batchDefaultEndDate")) {
	    	throw new PendingException();
	    }
	}
}
