package com.revature.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchesTest {

	public static WebDriver driver;

	@Given("^I am at the homepage$")
	public void i_am_at_the_homepage() throws Throwable {

		File f1 = new File("D:\\Work\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());

		String expectedUrl = "http://localhost:8080/TrackForce/html/index.html#!/";
		driver = new ChromeDriver();
		driver.get(expectedUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			System.out.println("Navigated to correct webpage");
		} catch (Throwable pageNavigationError) {
			System.out.println("Didn't navigate to correct webpage");
		}
	}

	@When("^I click on batches on the navbar$")
	public void i_click_on_batches_on_the_navbar() throws Throwable {
		WebElement batchListing = driver.findElement(By.xpath("/html/body/nav/div/ul/li/a"));
		batchListing.click();
	}

	@Then("^I validate that it is batchListing\\.html page$")
	public void i_validate_that_it_is_batchListing_html_page() throws Throwable {

		String expectedUrl = "http://localhost:8080/TrackForce/html/index.html#!/batchListing";
		driver.get(expectedUrl);
		try {
			Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			System.out.println("Navigated to correct webpage");
		} catch (Throwable pageNavigationError) {
			System.out.println("Didn't navigate to correct webpage");
		}
	}

	@Given("^I am at the batchListing\\.html page$")
	public void i_am_at_the_batchListing_html_page() throws Throwable {
		String expectedUrl = "http://localhost:8080/TrackForce/html/index.html#!/batchListing";
		driver.get(expectedUrl);
		try {
			Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			System.out.println("Navigated to correct webpage");
		} catch (Throwable pageNavigationError) {
			System.out.println("Didn't navigate to correct webpage");
		}
	}

	@When("^I input a date into the start date$")
	public void i_input_a_date_into_the_start_date() throws Throwable {
		WebElement startdate = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/form/input[1]"));
		startdate.sendKeys("07/27/2017");
		Thread.sleep(500);
	}

	@When("^I input a date into the end date$")
	public void i_input_a_date_into_the_end_date() throws Throwable {
		WebElement enddate = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/form/input[2]"));
		enddate.sendKeys("09/22/2017");
		Thread.sleep(500);
	}

	@Then("^I am able to see batches that are going on between these two dates$")
	public void i_am_able_to_see_batches_that_are_going_on_between_these_two_dates() throws Throwable {
	}

	@When("^I click on a batch name$")
	public void i_click_on_a_batch_name() throws Throwable {
		WebElement batchDetails = driver
				.findElement(By.xpath("/html/body/div/div/div/div[3]/div[1]/div/table/tbody/tr[1]/td[1]/a"));
		batchDetails.click();
	}

	@Then("^I should see the different details about a batch$")
	public void i_should_see_the_different_details_about_a_batch() throws Throwable {
		String expectedUrl = "http://localhost:8080/TrackForce/html/index.html#!/batchDetails";
		driver.get(expectedUrl);
		try {
			Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			System.out.println("Navigated to correct webpage");
		} catch (Throwable pageNavigationError) {
			System.out.println("Didn't navigate to correct webpage");
		}
	}

}
