#Author: Ryan Timms/Ashley Dyas
#Keywords Summary : client listing page: clientlisting.html
#										

@tag
Feature: View client page tables

Background:
Given I am on the client listing page

@tag1
Scenario: I want to view the table for all clients
When I click on the All clients link
Then I am able to view the table for all clients
	And the data shown in the table is accurate
	
@tag2
Scenario: I click on a client link
When I click on a client link in the listing
Then I am able to view the table for that client
	And the data shown in the table is accurate
	
@tag3
Scenario Outline: I want to search for a specific client
When I enter the <client> name into the search bar
Then The table name shown is equal to the client name

Examples:
    | client  			 |
    | Accenture 		 |  
    | Infosys   		 |
    | Microsoft 		 |  
		| Bank of America|