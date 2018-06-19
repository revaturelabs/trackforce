
Feature: Home Page Tab
  I want to use this template for my feature file

Background:
	Given I connect to caliber
	And I login

  Scenario: I click on the Revature telephone number
    Given I am on the Home Page
    When I click on the telephone link
    Then I should see the telephone number link open on a browser

  Scenario: I click on the Revature email
    Given I am on the Home Page
    When I click on the email link
    Then I should see the email link open on a browser

  Scenario: I click on the Revature website
    Given I am on the Home Page
    When I click on the website link
    Then I should see the Revature website link open on a browser

	Scenario: I view the graphs on the home page
	Given I am on the Home Page
	Then the graphs should be visible

  Scenario: I click on each element in Mapped pie chart
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    Then Should open with Mapped graphs

  
  Scenario: I click on each element in UnMapped pie chart
    Given I am on the Home Page
    When I click on the elements in the UnMapped pie chart
    Then Should open with UnMapped graphs

  Scenario: I click on each element in Mapped vs Unmapped Not Deployed chart
    Given I am on the Home Page
    When I click on the elements in the Mapped vs Unmapped Not Deployed chart
    Then Should open with Mapped vs Unmapped Not Deployed graphs

  Scenario: I click on each element in Mapped vs Unmapped Deployed chart
    Given I am on the Home Page
    When I click on the elements in the Mapped vs Unmapped Deployed chart
    Then Should open with Mapped vs Unmapped Deployed graphs

  Scenario: I click Pie Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on pie chart button
    Then A pie chart will be shown

  Scenario: I click Bar Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on bar chart button
    Then A bar chart will be shown

  Scenario: I click Polar Chart button
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on polar chart button
    Then A polar chart will be shown

  Scenario: I click Chart elements to get Associate list refered to by that chart element
    Given I am on the Home Page
    When I click on the elements in the Mapped pie chart
    And I click on pie chart button
    And I click a section of the pie chart
    Then I am on the Associate Page
    And The associate list associated with that section of the chart will be shown
