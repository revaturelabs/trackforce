Feature: Predictions Tab Tests

	Background:
	Given I connect to caliber
	And I login

  Scenario: Navigating to the prediction tab
	When I click on the prediction tab
	Then I should be on the prediction page

	Scenario Outline: Inputing a valid prediction
	When I click on the prediction tab
	And I input a start date"<startDate>"
	And I input an end date"<endDate>"
	And I enter the number of associates needed"<numAssociates>"
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then I should see a table displaying the results
	
	Examples:
	|startDate|endDate|numAssociates|
	|01021990|01022018|100|
	
	Scenario: Inputing invalid date
	When I click on the prediction tab
	And I input an invalid date combination
	And I enter a number of associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then nothing should be displayed
	
	Scenario: Inputing no associates
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter no associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then nothing should be displayed
	
	Scenario: Inputing negative associates
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter a negative number of associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then nothing should be displayed
	
	Scenario: Inputing the letter e for associates
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter e for associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then nothing should be displayed
	
	Scenario: Inputing no technologies
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter a number of associates
	And I enter no techonologies
	And I click on the Prediction button
	Then nothing should be displayed
	
Scenario: Selecting more technologies
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter a number of associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then I should see a table displaying the results
	But if i chose an additional technology
	And I click on the Prediction button
	Then it should be displayed
	
Scenario: Deselecting technologies
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter a number of associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then I should see a table displaying the results
	But if i remove a technology
	And I click on the Prediction button
	Then it should be removed
	
Scenario: Deselecting all technologies
	When I click on the prediction tab
	And I enter a valid date combination
	And I enter a number of associates
	And I filter by technology
	| JTA |
	| PEGA |
	And I click on the Prediction button
	Then I should see a table displaying the results
	But if i remove all technology
	And I click on the Prediction button
	Then they should all be removed		