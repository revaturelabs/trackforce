#Author: Clayton Su suclayton937@gmail.com
#Keywords Summary : Don't shoot me
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
@tag
Feature: Client List Tab
  I want to use this template for my feature file

  @tag1
  Scenario: I search for a client
    Given I am on the Client List Tab 
    When I click on the Search by Client Name box
    And Enter some valid client name
    Then I should see only that client name in the list
    
  Scenario: I click on View Data for All Clients
    Given I am on the Client List Tab 
    When I click on the View Data for All Clients button
    Then I should see Total Associates at the top of the bar graph
    
  Scenario: I click on View Data for All Clients
    Given I am on the Client List Tab 
    When I click on a client in the Client List
    Then I should see that client name at the top of the bar graph
