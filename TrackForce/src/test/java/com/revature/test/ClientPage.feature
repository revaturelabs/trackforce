#Keywords Summary : client listing page: clientDetails.html
#	This feature file describes the full functionality of the client listing page									
#The scenarios describe view a chart for all clients, and specific clients
#as well as clicking on a specific clients link
@tag
Feature: View client page tables
Background:
Given I am on the client listing page
@tag1
Scenario Outline: I want to search for a specific client
When I enter the <client> name into the search bar
Then The table name shown is equal to the client name
Examples:
    | client  			 |
    | Accenture 		 |  
    | Infosys   		 |
    | AFS		 |  
		| Revature|

@tag2
Scenario: I want to view the table for all clients
When I click on the All clients link
Then I am able to view the table for all clients
  And the data shown in the table is accurate
@tag3
Scenario: I click on a client link
When I click on a client link in the listing
Then I am able to view the table for that client
  And the data shown in the table is accurate