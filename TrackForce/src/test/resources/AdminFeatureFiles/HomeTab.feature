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
@HomePageTests
Feature: Home Page Tab
  I want to use this template for my feature file

  @HomeTagTest1
  Scenario: I click on the Revature telephone number
    Given I am on the Home Page
    When I click on the telephone link
    Then I should see the telephone number link open on a browser

  @HomeTagTest2
  Scenario: I click on the Revature email
    Given I am on the Home Page
    When I click on the email link
    Then I should see the email link open on a browser

  @HomeTagTest3
  Scenario: I click on the Revature website
    Given I am on the Home Page
    When I click on the website link
    Then I should see the Revature website link open on a browser

  @HomeTagTest4
  Scenario: I click on the Populate Database button
    Given I am on the Home Page
    When I click on the populate database button
    Then I should see a temporary blue border for the populate database button

  @HomeTagTest5
  Scenario: I click on the Populate Static Salesforce button
    Given I am on the Home Page
    When I click on the populate static salesforce button
    Then I should see a temporary blue border for the populate static salesforce button

  @ClickOnElementsInMappedChart
  Scenario: I click on each element in Mapped pie chart
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    Then Should open with Mapped graphs

  @ClickOnElementsInUnMappedChart
  Scenario: I click on each element in UnMapped pie chart
    Given I am on the Home Page
    When I click on the elements in the UnMapped pie chart
    Then Should open with UnMapped graphs

  @ClickOnElementsInMappedVsUnmappedNotDeployed
  Scenario: I click on each element in Mapped vs Unmapped Not Deployed chart
    Given I am on the Home Page
    When I click on the elements in the Mapped vs Unmapped Not Deployed chart
    Then Should open with Mapped vs Unmapped Not Deployed graphs

  @ClickOnElementsInMappedVsUnmappedDeployed
  Scenario: I click on each element in Mapped vs Unmapped Deployed chart
    Given I am on the Home Page
    When I click on the elements in the Mapped vs Unmapped Deployed chart
    Then Should open with Mapped vs Unmapped Deployed graphs

  @TestPieChartButton
  Scenario: I click Pie Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on pie chart button
    Then A pie chart will be shown

  @TestBarChartButton
  Scenario: I click Bar Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on bar chart button
    Then A bar chart will be shown

  @TestPolarChartButton
  Scenario: I click Polar Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on polar chart button
    Then A polar chart will be shown
@TestPieChartButton
  Scenario: I click Chart elements to get Associate list refered to by that chart element
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on pie chart button
    And I click a section of the pie chart
    Then I am on the Associate Page
    And The associate list associated with that section of the chart will be shown
  @HomeTagTest6
  Scenario: I click on the Empty Database button
    Given I am on the Home Page
    When I click on the empty database button
    Then I should see a temporary blue border for the empty database button
