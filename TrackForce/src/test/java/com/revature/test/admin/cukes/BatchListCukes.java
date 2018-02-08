package com.revature.test.admin.cukes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchListCukes {
	@Given("^The Batch List Tab is clicked$")
	public static void the_Batch_List_Tab_is_clicked(WebDriver wd) throws Throwable {
		BatchListTab.clickBatchListTab(wd).click();
	}

	@Then("^All Batches text is visible$")
	public static boolean all_Batches_text_is_visible(WebDriver wd) throws Throwable {

		try {
			// Find the Header to verify that you are in the Batch List Tab
			BatchListTab.findAllBatchesHeader(wd);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}
	
	@Given("^the first batch is clicked$")
	public static void the_first_batch_is_clicked(WebDriver wd) throws Throwable {
		// Click the first batch in the list
		BatchListTab.getFirstBatchName(wd).click();
	}

	@When("^the list of associates is grabbed$")
	public static boolean the_list_of_associates_is_grabbed(WebDriver wd) throws Throwable {
		// Grab the associates IDs to be compared
		List<WebElement> compareID = BatchListTab.getAssociatesIDs(wd);
		// Get the batch name to verify everyone in that batch is in correct batch
		String batchName = BatchListTab.getFirstBatchName(wd).getText();
		// Sends to helper function
		return associates_should_match_the_associate_list(wd, batchName, compareID);
	}

	@Then("^associates should match the associate list$")
	public static boolean associates_should_match_the_associate_list(WebDriver wd, String batchName, List<WebElement> IDs) throws Throwable {
		// Switch to Associate List Tab
		WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-batch-details/app-navbar/nav/div/ul[1]/li[4]/a"), 10).click();
		
		// Verify that the IDs grabbed from first batch are all in the correct batch by looking at the associate list
		List<WebElement> associates = BatchListTab.grabAssociates(wd);
		// Search through the List of Associates taken from Associate List Tab
		for (WebElement e : IDs) {
			System.out.println(e.getText());
			// Match the associate IDs and see if they're in the correct batch. Return false if they don't match up
			for (int i = 0; i < associates.size(); i++) {
				System.out.println(associates.get(i).getText());
				// Go to the row where the ID is
				if (associates.get(i).getText().contains(e.getText())) {
					// If the ID is found, see if they belong in the batch that we clicked
					if (!(associates.get(i).getText().contains(batchName))) {
					System.out.println("Associate ID "+e+" not found in " + batchName);
					return false;
					}
				}
			}
		}
		return true;
	}
}
