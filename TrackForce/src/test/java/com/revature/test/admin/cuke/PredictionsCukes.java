package com.revature.test.admin.cuke;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.openqa.selenium.WebElement;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Predictions;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PredictionsCukes{

	static int testNumber = 1,beforeSize;
	static String requestedAssociates,newTech;
	static List<String> techs;

	@Given("^Predictions Tab loads$")
	public static void predictions_tab_loads() {
		try {
			Thread.sleep(1500);
			if (HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL())
					|| HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/predictions")) {
			}
			fail("Current URL does not equal the base URL, or does not end with /predictions");
		} catch (Throwable e) {
			fail("Failed to get current URL");
		}
	}

	@Given("^I click on the prediction tab$")
	public void i_click_on_the_prediction_tab() throws Throwable {
		System.out.println("Clicking on the prediction tab");
		Thread.sleep(1500);
		Predictions.clickPredictionsTab(ServiceHooks.driver).click();
	}

	@When("^I input a start date\"([^\"]*)\"$")
	public void i_input_a_start_date(String date) throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.startDate(ServiceHooks.driver).sendKeys(date);
		} catch (Throwable e) {
			fail(("Failed to input a start date"));
		}
	}

	@When("^I input an end date\"([^\"]*)\"$")
	public void i_input_an_end_date(String date) throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.endDate(ServiceHooks.driver).sendKeys(date);
		} catch (Throwable e) {
			fail(("Failed to input a end date"));
		}
	}

	@When("^I enter a valid date combination$")
	public void i_enter_a_valid_date_combination() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.startDate(ServiceHooks.driver).sendKeys("01021990");
		} catch (Throwable e) {
			fail(("Failed to input date"));
		}
	}

	@When("^I input an invalid date combination$")
	public void i_input_an_invalid_date_combination() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.endDate(ServiceHooks.driver).sendKeys("01021990");
		} catch (Throwable e) {
			fail(("Failed to input date"));
		}
	}
	
	@When("^I enter the number of associates needed\"([^\"]*)\"$")
	public void i_enter_the_number_of_associates_needed(String numAssociates) throws Throwable {

		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys(numAssociates);
			requestedAssociates = numAssociates;
		} catch (Throwable e) {
			fail(("Failed to input the number of associates"));

		}
	}

	@When("^I enter a number of associates$")
	public void i_enter_a_number_of_associates() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys("100");
			requestedAssociates = "100";
		} catch (Throwable e) {
			fail(("Failed to input the number of associates"));

		}
	}

	@When("^I enter no associates$")
	public void i_enter_no_associates() throws Throwable {
	}

	@When("^I enter a negative number of associates$")
	public void i_enter_a_negative_number_of_associates() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys("-100");
		} catch (Throwable e) {
			fail(("Failed to input the number of associates"));

		}
	}

	@When("^I enter e for associates$")
	public void i_enter_e_for_associates() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys("e");
		} catch (Throwable e) {
			fail(("Failed to input the number of associates"));

		}
	}

	@When("^I filter by technology$")
	public void i_filter_by_technology(DataTable select) throws Throwable {
		List<String> selected = select.asList(String.class);
		techs = selected;
		try {
			Predictions.filterbyTechnologies(ServiceHooks.driver).click();
			for (String tech : selected) {
				if (Predictions.technologies.containsKey(tech)) {
					Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get(tech));
				}
			}
			Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		} catch (Throwable e) {
			fail(("Failed to select a technology"));
		}
	}

	@When("^I enter no techonologies$")
	public void i_enter_no_techonologies() throws Throwable {
	}

	@When("^I click on the Prediction button$")
	public static void i_click_on_the_Prediction_button() {

		try {
			WaitToLoad.waitForClickable(ServiceHooks.driver, Predictions.buttonPrediction(ServiceHooks.driver), 5);
			Predictions.buttonPrediction(ServiceHooks.driver).click();

		} catch (Throwable e) {
			fail(e.getMessage());
			fail(("Failed to click on the Get Prediction button"));
		}

	}

	@Then("^I should be on the prediction page$")
	public void i_should_be_on_the_prediction_page() throws Throwable {
		System.out.println("I should be on the prediction page");
		Thread.sleep(1500);
		assertEquals(Predictions.getCurrentURL(ServiceHooks.driver),
				"http://34.227.178.103:8090/NGTrackForce/#/predictions");
	}

	@Then("^if i chose an additional technology$")
	public void if_i_chose_an_additional_technology() throws Throwable {
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
			if (Predictions.technologies.containsKey("Java")) {
				Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get("Java"));
			}
		newTech = "Java";
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^if i remove a technology$")
	public void if_i_remove_a_technology() throws Throwable {
		Thread.sleep(1500);
		beforeSize = Predictions.getTechnologies(ServiceHooks.driver).size();
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		if (Predictions.technologies.containsKey("JTA")) {
			Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get("JTA"));
		}
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^it should be removed$")
	public void it_should_be_removed() throws Throwable {
		Thread.sleep(10000);
		assertTrue(beforeSize >Predictions.getTechnologies(ServiceHooks.driver).size());
		}

	@Then("^if i remove all technology$")
	public void if_i_remove_all_technology() throws Throwable {
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		if (Predictions.technologies.containsKey("PEGA")) {
			Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get("PEGA"));
		}
		if (Predictions.technologies.containsKey("JTA")) {
			Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get("JTA"));
		}
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^I should see a table displaying the results$")
	public static void i_should_see_a_table_displaying_the_results() {
		try {
			Thread.sleep(15000);
			for(int i = 0; i < techs.size();i++) {
				assertEquals(Predictions.getTechnologies(ServiceHooks.driver).get(i).getText(),techs.get(i));
			}
			for(int i = 0; i < techs.size();i++) {
			assertEquals(Predictions.getRequestedAssociates(ServiceHooks.driver).get(i).getText(),requestedAssociates);
			int difference = Integer.parseInt(Predictions.getAvailableAssociates(ServiceHooks.driver).get(i).getText()) - Integer.parseInt(Predictions.getRequestedAssociates(ServiceHooks.driver).get(i).getText());
			assertEquals(Predictions.getDifference(ServiceHooks.driver).get(i).getText(),String.valueOf(difference));
			}
		} catch (Throwable e) {
			fail("Table doesn't match expected");
		}

	}

	@Then("^it should be displayed$")
	public void it_should_be_displayed() throws Throwable {
		List<WebElement> techs = Predictions.getTechnologies(ServiceHooks.driver);
			for(WebElement tech : techs) {
				if(tech.getText().equals(newTech)) {
					assertEquals(techs.get(techs.size()-1).getText(),newTech);
				}
			}
	}

	@Then("^nothing should be displayed$")
	public void nothing_should_be_displayed() throws Throwable {
		assertEquals(Predictions.getTechnologies(ServiceHooks.driver).size(),0);
	}

	@Then("^they should all be removed$")
	public void they_should_all_be_removed() throws Throwable {
		Thread.sleep(15000);
		assertEquals(Predictions.getTechnologies(ServiceHooks.driver).size(),0);
	}

}
