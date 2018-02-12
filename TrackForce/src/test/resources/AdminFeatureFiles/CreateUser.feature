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

@tag
Feature: Create User tab for Admin
  TaskForce Create User tab scenario outlines

  @tag1
  Scenario Outline: Check if in Locations Tab for trainer
    Given The Locations Tab is selected <webdriver>
    Then I should see the Revature HQ drop down <webdriver>
    And New York City drop down <webdriver>
