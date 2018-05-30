package com.revature.test.admin.cukes;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Predictions;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.TestConfig;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PredictionsCukes extends AdminSuite {
	
	static int testNumber = 1;
	
	@Given("^I am on the Predictions page$")
	public static boolean i_am_on_the_Predictions_page(WebDriver d)  {
		
		try {
			Thread.sleep(2000);
			// Click on the Predictions Tab
			Predictions.clickPredictionsTab(d).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed click on the Predictions Tab"));
			return false;
		}
	    
	}

	@Given("^Predictions Tab loads$")
	public static boolean predictions_tab_loads(WebDriver d) {
		try {
			Thread.sleep(500);
			if (HomeTab.getCurrentURL(d).equals(TestConfig.getBaseURL()) ||
					HomeTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/predictions")) {
				return true;
			}
			System.out.println("Current URL does not equal the base URL, or does not end with /predictions");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			return false;
		}
	}
	
	@When("^I input a start date$")
	public static boolean i_input_a_start_date(WebDriver d)  {
		
		try {
			Thread.sleep(1000);
			Predictions.startDate(d).sendKeys("02/26/1999");
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed to input a start date"));
			return false;
		}
	    
	}

	@When("^I input a end date$")
	public static boolean i_input_a_end_date(WebDriver d)  {
		
		try {
			Thread.sleep(1000);
			Predictions.endDate(d).sendKeys("09/13/1999");
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed to input a end date"));
			return false;
		}
	    
	}

	@When("^I enter the number of associates needed$")
	public static boolean i_enter_the_number_of_associates_needed(WebDriver d)  {
		
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(d).sendKeys("15");
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed to input the number of associates"));
			return false;
		}
	    
	}

	@When("^I select a select a technology from a drop down$")
	public static boolean i_select_a_select_a_technology_from_a_drop_down(WebDriver d)  {
		
		try {
			Thread.sleep(1000);
			Predictions.filterbyTechnologies(d).click();
			Thread.sleep(1000);
			Predictions.technologyOption(d).click();
			Thread.sleep(1000);
			Predictions.filterbyTechnologies(d).click();
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed to select a technology"));
			return false;
		}
	   
	}
	
	@When("^I click on the Prediction button$")
	public static boolean i_click_on_the_Prediction_button(WebDriver d)  {
		
		try {
			Thread.sleep(1000);
			Predictions.buttonPrediction(d).click();
			Thread.sleep(1000);
			return true;

		} catch (Throwable e) {
			System.out.println(("Failed to click on the Get Prediction button"));
			return false;
		}
	    
	}

	@Then("^I should see a table displaying the results$")
	public static boolean i_should_see_a_table_displaying_the_results(WebDriver d )  {
		try {
			Thread.sleep(2000);
			System.out.println("Technology: " + Predictions.technology(d).getText());
			System.out.println("Requested Associates: " + Predictions.requestedAssociates(d).getText());
			System.out.println("Available Associates: " + Predictions.availableAssociates(d).getText());
			System.out.println("Difference: " + Predictions.difference(d).getText());
			
			if (Predictions.technology(d).getText().equals(".Net") || 
					Predictions.requestedAssociates(d).getText().equals("15") ||
					Predictions.availableAssociates(d).getText().equals("0") ||
					Predictions.difference(d).getText().equals("-15")) {
				return true;
			}
			System.out.println(("Form values do not equal what we intended to input."));
			return false;

		} catch (Throwable e) {
			System.out.println(("Failed to find the table results"));
			return false;
		}
	    
	}


}
