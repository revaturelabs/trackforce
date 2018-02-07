package com.revature.test.admin.cukes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BatchListCukes {
	@Given("^The Batch List Tab is clicked$")
	public static void the_Batch_List_Tab_is_clicked(WebDriver wd) throws Throwable {
		BatchListTab.clickBatchListTab(wd).click();
	}

	@Then("^All Batches text is visible$")
	public static boolean all_Batches_text_is_visible(WebDriver wd) throws Throwable {

		try {
			BatchListTab.findAllBatchesHeader(wd);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}
}
