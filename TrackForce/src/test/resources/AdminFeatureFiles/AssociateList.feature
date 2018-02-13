#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


Feature: Title of your feature
  I want to use this template for my feature file
  
  Background Logged in as VP and on the Associate List Page.
    
   Scenario: Search by text
    Given I want to search by text
    When I input text in the search by text input field
    Then The item is retrieved
    
    Scenario: Marketing Status
    Given I want to filter by marketing status
    When I select a marketing status value from the marketing status drop drown
    Then the table is filtered by that marketing status
    
  Scenario: Search by curriculum
   Given I want to filter by curriculum
   When I select a curriculum value from the curriculum drop down
   Then the table is filtered by that curriculum

 
  Scenario: Search by client
    Given I want to filter by client
    When I select a client value from the client drop down
    Then the table is filtered by that client

    
    Scenario: Edit associate status
    Given I want to edit an associates status
    When I click an associate checkbox
    And I select a update by marketing status value from the update by marketing status drop down
    And I select a client value from the client drop down
    And I click the update button
    Then the information is updated
   
    Scenario: Sort the associate table by associate id in ascending order
 		Given I want to sort the associate's table by associate id in ascending order
 		When I click the associate id heading on the associate table
 		Then The associate table is sorted by the associate's id in ascending order

 		Scenario: Sort the associate table by associate id in descending order
 		Given I want to sort the associate table by associate id in descending order
 		When I click the associate id heading on the associate table
 		Then The associate table is sorted by associate id in descending order
 		
 	  Scenario: Sort the associate table by first name in ascending order
 		Given I want to sort the associate table by first name in ascending order
 		When I click the first name heading on the associate table
 		Then The associate table is sorted by first name in ascending order
 		
 		Scenario: Sort the associate table by first name in descending order
 		Given I want to sort the associate table by first name in descending order
 		When I click the first name heading on the associate table
 		Then The associate table is sorted by first name in descending order
 		
 		Scenario: Sort the associate table by last name in ascending order
 		Given I want to sort the associate table by last name in ascending order
 		When I click the last name heading on the associate table
 		Then The associate table is sorted by last name in ascending order
 		
 		Scenario: Sort the associate table by last name in descending order
 		Given I want to sort the associate table by last name in descending order
 		When I click the last name heading on the associate table
 		Then The associate table is sorted by last name in descending order
 		
 		Scenario: Sort the associate table by marketing status in ascending order
 		Given I want to sort the associate table by marketing status in ascending order
 		When I click the marketing status heading on the associate table
 		Then The associate table is sorted by marketing status in ascending order
 		
 		Scenario: Sort the associate table by marketing status in descending order
 		Given I want to sort the associate table by marketing status in descending order
 		When I click the marketing status heading on the associate table
 		Then The associate table is sorted by marketing status in descending order
 		
 		Scenario: Sort the associate table by client name in ascending order
 		Given I want to sort the associate table by client name in ascending order
 		When I click the client name heading on the associate table
 		Then The associate table is sorted by client name in ascending order
 		
 		Scenario: Sort the associate table by client name in descending order
 		Given I want to sort the associate table by client name in descending order
 		When I click the client name heading on the associate table
 		Then The associate table is sorted by client name in descending order
 		
 		Scenario: Sort the associate table by batch name in ascending order
 		Given I want to sort the associate table by batch name in ascending order
 		When I click the batch name heading on the associate table
 		Then The associate table is sorted by batch name in ascending order
 		
 		Scenario: Sort the associate table by batch name in descending order
 		Given I want to sort the associate table by batch name in descending order
 		When I click the batch name heading on the associate table
 		Then The associate table is sorted by batch name in descending order
	