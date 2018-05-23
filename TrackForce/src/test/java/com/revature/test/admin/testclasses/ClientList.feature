#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario Outline: Business rule through list of steps with arguments.
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

@tag
Feature: Client List Tab
  I want to use this template for my feature file

  @tag1
  Scenario Outline: I search by client name and view their data
    Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    When I type the name of a client into the search bar
    And I see only that client in the list
    And I click the top client in the Clients list
    Then The clients data should show in the graph
    
  Scenario Outline: I use the View Data for All Clients button
    Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    And I click on the View Data for All Clients button
    Then All client's data should show in the graph
    
  Scenario Outline: I click on a client name and view their data
    Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    And I click the top client in the Clients list
    Then The client's data should show in the graph




@tag2
	Scenario Outline: I search by client name but change it to another client and view their data instead
	Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    And I type the name of a client into the search bar
    But I want to enter a different client name into the search bar instead
    And I see only that client in the list
    And I click the top client in the Clients list
    Then The clients data should show in the graph
    
    Scenario Outline: I search by client name but use the View Data for All Clients button instead
    Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    And I type the name of a client into the search bar
    But I click on the View Data for All Clients button
    Then All clients data should show in the graph
    
    Scenario Outline: I search by client name but want to clear the search bar and click on a client name instead
		Given I click on Client List Tab
    And Client List loads
    And Client List panel loads
    When I make sure the search bar is blank
    And I type the name of a client into the search bar
    But I make sure the search bar is blank
    And I click the top client in the Clients list
    Then The clients data should show in the graph