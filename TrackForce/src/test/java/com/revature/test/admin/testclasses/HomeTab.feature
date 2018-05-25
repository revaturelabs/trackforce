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
Feature: Home Page Tab
  I want to use this template for my feature file

  @tag1
  Scenario Outline: I click on the Revature telephone number
    Given I am on the Home Page
    When I click on the telephone link
    Then I should see the telephone number link open on a browser 
  
  Scenario Outline: I click on the Revature email
    Given I am on the Home Page
    When I click on the email link
    Then I should see the email link open on a browser 
  
  Scenario Outline: I click on the Revature website
    Given I am on the Home Page
    When I click on the website link
    Then I should see the Revature website link open on a browser 
   
  Scenario Outline: I click on the Populate Database button
    Given I am on the Home Page
    When I click on the populate database button
    Then I should see a temporary blue border for the populate database button
  
   Scenario Outline: I click on the Populate Static Salesforce button
    Given I am on the Home Page
    When I click on the populate static salesforce button
    Then I should see a temporary blue border for the populate static salesforce button 
    
    Scenario Outline: I click on the Empty Database button
    Given I am on the Home Page
    When I click on the empty database button
    Then I should see a temporary blue border for the empty database button   
    