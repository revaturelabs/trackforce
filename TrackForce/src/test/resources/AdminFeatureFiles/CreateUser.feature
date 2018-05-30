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
@CreateUserTests
Feature: Create User tab for Admin
  TaskForce Create User tab Scenarios

  @CreateNewAdmin
  Scenario: Create new admin
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a username
    And I type in a password
    And I confirm the password
    And I check the Administrator role
    Then I press submit

  @CreateNewManager
  Scenario: Create new manager
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a username
    And I type in a password
    And I confirm the password
    And I check the Manager role
    Then I press submit

  @CreateNewVP
  Scenario: Create new VP
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a username
    And I type in a password
    And I confirm the password
    And I check the VP role
    Then I press submit
