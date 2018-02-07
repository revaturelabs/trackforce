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
			BatchListTab.findAllBatchesHeader(wd);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}
	
	@Given("^the first batch is clicked$")
	public static void the_first_batch_is_clicked(WebDriver wd) throws Throwable {
		BatchListTab.getFirstBatchName(wd).click();
	}

	@When("^the list of associates is grabbed$")
	public static void the_list_of_associates_is_grabbed(WebDriver wd) throws Throwable {
		associates_should_match_the_associate_list(wd, BatchListTab.getAssociateFirstNames(wd), BatchListTab.getAssociateLastNames(wd));
//		List<WebElement> compareFirst = BatchListTab.getAssociateFirstNames(wd);
//		List<WebElement> compareLast = BatchListTab.getAssociateLastNames(wd);
//		for (int i = 0; i < compareFirst.size(); i++) {
//			System.out.println(compareFirst.get(i).getText() + " " + compareLast.get(i).getText());
//		}
	}

	@Then("^associates should match the associate list$")
	public static void associates_should_match_the_associate_list(WebDriver wd, List<WebElement> fName, List<WebElement> lName) throws Throwable {
		// Switch to Associate List Tab
		WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[4]"), 10).click();
//		List<WebElement> compareFirst = BatchListTab.matchFirstNames(wd);
//		List<WebElement> compareLast = BatchListTab.matchLastNames(wd);
//		for (int i = 0; i < compareFirst.size(); i++) {
//			System.out.println(compareFirst.get(i) + " " + compareLast.get(i));
//		}
		
	}
}
