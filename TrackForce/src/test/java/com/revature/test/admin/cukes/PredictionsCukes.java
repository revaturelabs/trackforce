package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;

import java.util.List;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Predictions;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PredictionsCukes extends AdminSuite {

	static int testNumber = 1;

	@Given("^Predictions Tab loads$")
	public static void predictions_tab_loads() {
		try {
			Thread.sleep(1500);
			if (HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL())
					|| HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/predictions")) {
			}
			System.out.println("Current URL does not equal the base URL, or does not end with /predictions");
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
		}
	}

	@When("^I click on the prediction tab$")
	public void i_click_on_the_prediction_tab() throws Throwable {
		Thread.sleep(1500);
		Predictions.clickPredictionsTab(ServiceHooks.driver).click();
	}

	@When("^I input a start date\"([^\"]*)\"$")
	public void i_input_a_start_date(String date) throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.startDate(ServiceHooks.driver).sendKeys(date);
		} catch (Throwable e) {
			System.out.println(("Failed to input a start date"));
		}
	}

	@When("^I input an end date\"([^\"]*)\"$")
	public void i_input_an_end_date(String date) throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.endDate(ServiceHooks.driver).sendKeys(date);
		} catch (Throwable e) {
			System.out.println(("Failed to input a end date"));
		}
	}

	@When("^I enter a valid date combination$")
	public void i_enter_a_valid_date_combination() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.startDate(ServiceHooks.driver).sendKeys("01021990");
		} catch (Throwable e) {
			System.out.println(("Failed to input date"));
		}
	}

	@When("^I input an invalid date combination$")
	public void i_input_an_invalid_date_combination() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.endDate(ServiceHooks.driver).sendKeys("01021990");
		} catch (Throwable e) {
			System.out.println(("Failed to input date"));
		}
	}
	
	@When("^I enter the number of associates needed\"([^\"]*)\"$")
	public void i_enter_the_number_of_associates_needed(String numAssociates) throws Throwable {

		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys(numAssociates);
		} catch (Throwable e) {
			System.out.println(("Failed to input the number of associates"));

		}
	}

	@When("^I enter a number of associates$")
	public void i_enter_a_number_of_associates() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys("100");
		} catch (Throwable e) {
			System.out.println(("Failed to input the number of associates"));

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
			System.out.println(("Failed to input the number of associates"));

		}
	}

	@When("^I enter e for associates$")
	public void i_enter_e_for_associates() throws Throwable {
		try {
			Thread.sleep(1000);
			Predictions.numberofAssociates(ServiceHooks.driver).sendKeys("e");
		} catch (Throwable e) {
			System.out.println(("Failed to input the number of associates"));

		}
	}

	@When("^I filter by technology$")
	public void i_filter_by_technology(DataTable select) throws Throwable {
		List<String> selected = select.asList(String.class);
		try {
			Thread.sleep(1000);
			Predictions.filterbyTechnologies(ServiceHooks.driver).click();
			Thread.sleep(1000);
			for (String tech : selected) {
				if (Predictions.technologies.containsKey(tech)) {
					Predictions.selectFilter(ServiceHooks.driver, Predictions.technologies.get(tech));
				}
			}
			Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		} catch (Throwable e) {
			System.out.println(("Failed to select a technology"));
		}
	}

	@When("^I enter no techonologies$")
	public void i_enter_no_techonologies() throws Throwable {
	}

	@When("^I click on the Prediction button$")
	public static void i_click_on_the_Prediction_button() {

		try {
			Thread.sleep(1000);
			WaitToLoad.waitForClickable(ServiceHooks.driver, Predictions.buttonPrediction(ServiceHooks.driver), 5);
			Predictions.buttonPrediction(ServiceHooks.driver).click();
			Thread.sleep(1000);

		} catch (Throwable e) {
			System.out.println(e.getMessage());
			System.out.println(("Failed to click on the Get Prediction button"));
		}

	}

	@Then("^I should be on the prediction page$")
	public void i_should_be_on_the_prediction_page() throws Throwable {
		Thread.sleep(1500);
		assertEquals(Predictions.getCurrentURL(ServiceHooks.driver),
				"http://34.227.178.103:8090/NGTrackForce/predictions");
	}

	@Then("^if i chose an additional technology$")
	public void if_i_chose_an_additional_technology() throws Throwable {
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		Predictions.selectFilter(ServiceHooks.driver, ".Net");
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^if i remove a technology$")
	public void if_i_remove_a_technology() throws Throwable {
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		Predictions.selectFilter(ServiceHooks.driver, "JTA");
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^it should be removed$")
	public void it_should_be_removed() throws Throwable {
		assertEquals(Predictions.technology(ServiceHooks.driver).getText(), "PEGA");
	}

	@Then("^if i remove all technology$")
	public void if_i_remove_all_technology() throws Throwable {
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
		Predictions.selectFilter(ServiceHooks.driver, "PEGA");
		Predictions.selectFilter(ServiceHooks.driver, "JTA");
		Predictions.filterbyTechnologies(ServiceHooks.driver).click();
	}

	@Then("^I should see a table displaying the results$")
	public static void i_should_see_a_table_displaying_the_results() {
		try {
			Thread.sleep(2000);
			System.out.println("Technology: " + Predictions.technology(ServiceHooks.driver).getText());
			System.out
					.println("Requested Associates: " + Predictions.requestedAssociates(ServiceHooks.driver).getText());
			System.out
					.println("Available Associates: " + Predictions.availableAssociates(ServiceHooks.driver).getText());
			System.out.println("Difference: " + Predictions.difference(ServiceHooks.driver).getText());

			if (Predictions.technology(ServiceHooks.driver).getText().equals(".Net")
					|| Predictions.requestedAssociates(ServiceHooks.driver).getText().equals("15")
					|| Predictions.availableAssociates(ServiceHooks.driver).getText().equals("0")
					|| Predictions.difference(ServiceHooks.driver).getText().equals("-15")) {
			}
			System.out.println(("Form values do not equal what we intended to input."));

		} catch (Throwable e) {
			System.out.println(("Failed to find the table results"));
		}

	}

	@Then("^it should be displayed$")
	public void it_should_be_displayed() throws Throwable {
		assertEquals(Predictions.technology(ServiceHooks.driver).getText(), ".Net");
	}

	@Then("^nothing should be displayed$")
	public void nothing_should_be_displayed() throws Throwable {
		try {
			assertEquals(Predictions.requestedAssociates(ServiceHooks.driver).getText(), "");
		} catch (Exception e) {

		}
	}

	@Then("^they should all be removed$")
	public void they_should_all_be_removed() throws Throwable {
		try {
			assertEquals(Predictions.requestedAssociates(ServiceHooks.driver).getText(), "");
		} catch (Exception e) {

		}
	}

}
