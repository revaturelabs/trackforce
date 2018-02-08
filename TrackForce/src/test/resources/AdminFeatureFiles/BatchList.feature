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
Feature: Batch List Scenarios

  @tag1
  Scenario Outline: Navigate to Batch List Tab <WebDriver>
    Given The Batch List Tab is clicked <WebDriver>
    Then All Batches text is visible
    
  Examples:
  	#| WebDriver |
  	| WebDriver |

	@tag2
	Scenario: Click Batch Name List
		Given the first batch is clicked
		When the list of associates is grabbed
		Then associates should match the associate list
