package com.revature.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClientPageSteps {

	@Given("^I am on the client listing page$")
    public void i_am_on_the_client_listing_page() throws Throwable {
        System.out.println("^I am on the client listing page$");
    }

    @When("^I enter the (.+) name into the search bar$")
    public void i_enter_the_name_into_the_search_bar(String client) throws Throwable {
        System.out.println("I enter the (.+) name into the search bar$");
    }

    @When("^I click on the All clients link$")
    public void i_click_on_the_all_clients_link() throws Throwable {
        System.out.println("^I click on the All clients link$");
    }

    @When("^I click on a client link in the listing$")
    public void i_click_on_a_client_link_in_the_listing() throws Throwable {
        System.out.println("^I click on a client link in the listing$");
    }

    @Then("^The table name shown is equal to the client name$")
    public void the_table_name_shown_is_equal_to_the_client_name() throws Throwable {
        System.out.println("^The table name shown is equal to the client name$");
    }

    @Then("^I am able to view the table for all clients$")
    public void i_am_able_to_view_the_table_for_all_clients() throws Throwable {
        System.out.println("^I am able to view the table for all clients$");
    }

    @Then("^I am able to view the table for that client$")
    public void i_am_able_to_view_the_table_for_that_client() throws Throwable {
        System.out.println("^I am able to view the table for that client$");
    }

    @And("^the data shown in the table is accurate$")
    public void the_data_shown_in_the_table_is_accurate() throws Throwable {
        System.out.println("^the data shown in the table is accurate$");
    }
	
}
