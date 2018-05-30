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
Feature: Batch List Scenarios

  @batchTag1
  Scenario: Navigate to Batch List Tab
  	Given I am logged in
    When The Batch List Tab is clicked
    Then All Batches text is visible

  @batchTag2
  Scenario: Click Batch Name List
    Given The Batch List Tab is clicked
    And Batch List Tab loads
    When the list of associates is grabbed
    Then associates should match the associate list

  @batchTag3
  Scenario: The From date is entered
    Given The Batch List Tab is clicked
    And Batch List Tab loads
    When the From date is entered
    Then the From field should not contain default values

  @batchTag4
  Scenario: The To date is entered
    Given The Batch List Tab is clicked
    And Batch List Tab loads
    When the To date is entered
    Then the To field should not contain default values

  @batchTag5
  Scenario: Submit Batches Dates
    Given The Batch List Tab is clicked
    And Batch List Tab loads
    When the submit button is clicked
    Then the batch list should update to show only the batches which fit the entered criteria

  @batchTag6
  Scenario: Reset Batches Dates
    Given The Batch List Tab is clicked
    And Batch List Tab loads
    When the reset button is clicked
    Then associates should match the associate list

  @batchTag7
  Scenario: View Individual Batch
    Given batches are showing
    When I click on a specific batch name
    Then I should be taken to the appropriate details page

  @batchTag8
  Scenario: Navigate to Associate
    Given I am looking at batch details
    When I click on an associate ID
    Then I should be taken to the appropriate information page
