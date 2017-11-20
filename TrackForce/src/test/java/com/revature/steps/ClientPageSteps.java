package com.revature.steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.factory.ClientPage;
import com.revature.utils.DriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClientPageSteps {
	
	public static WebDriver driver = null;
	private WebDriverWait wait;
	
	static ClientPage clientPage = null;
	
	@Before
	public void beforeScenario() {
		System.out.println("beforeScenario");
		String browser = "firefox";
		driver = DriverUtil.getDriver(browser);
		clientPage = new ClientPage(driver);
		driver.get("http://localhost:8080/TrackForce/html/index.html#!/clientDetails");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Given("^I am on the client listing page$")
    public void i_am_on_the_client_listing_page() throws Throwable {
        System.out.println("^I am on the client listing page$");
        assertTrue(driver.getCurrentUrl().contains("clientDetails"));
    }

    @When("^I enter the (.+) name into the search bar$")
    public void i_enter_the_name_into_the_search_bar(String client) throws Throwable {
        System.out.println("I enter the (.+) name into the search bar$");
        clientPage.searchBar.sendKeys(client);
    }

    @When("^I click on the All clients link$")
    public void i_click_on_the_all_clients_link() throws Throwable {
        System.out.println("^I click on the All clients link$");
        clientPage.allClientsBtn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^I click on a client link in the listing$")
    public void i_click_on_a_client_link_in_the_listing() throws Throwable {
        System.out.println("^I click on a client link in the listing$");
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(clientPage.clientListItem).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(clientPage.clientListItem)).click();
        Thread.sleep(2000);
    }

    /*@Then("^The table name shown is equal to the client name$")
    public void the_table_name_shown_is_equal_to_the_client_name(String client) throws Throwable {
        System.out.println("^The table name shown is equal to the client name$");
        assertEquals(clientPage.clientListItem.getAttribute("innerHTML"), "Infosys");
    }*/
    
    @Then("^The table name shown is equal to the (.+) name$")
    public void the_table_name_shown_is_equal_to_the_name(String client) throws Throwable {
    	System.out.println("^The table name shown is equal to the (.+) name$");
        assertEquals(clientPage.chartTitle.getAttribute("innerHTML"), client);
    }

    @Then("^I am able to view the table for all clients$")
    public void i_am_able_to_view_the_table_for_all_clients() throws Throwable {
        System.out.println("^I am able to view the table for all clients$");
        assertTrue(clientPage.chartTitle.getAttribute("innerHTML").contains("All Clients"));
    }

    @Then("^I am able to view the table for that client$")
    public void i_am_able_to_view_the_table_for_that_client() throws Throwable {
        System.out.println("^I am able to view the table for that client$");
    }

    @And("^the data shown in the table is accurate$")
    public void the_data_shown_in_the_table_is_accurate() throws Throwable {
        System.out.println("^the data shown in the table is accurate$");
    }
    
    @After
    public void afterScenario() {
		System.out.println("afterScenario");
		driver.close();
	}
	
}
