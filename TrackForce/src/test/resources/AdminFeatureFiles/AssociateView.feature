Feature: Testing the various user stories connected to the Associate use case

  Background: 
    Given I connect to caliber
    Given I login as an Associate

#The changes should be reflected is failing - relies on broken database/frontend schema
  Scenario: Associate is attempting to update
    Given I am on the Associate Home Page
    When I enter new name information
    And I click the save button
    Then the changes should be reflected

#Select a client is failing - relies on broken frontend/database code
#Need to implement "it should be in the interview table"
  Scenario: Creating a valid Interview
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter an Interview date
    And I enter an Assigned date
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then it should be in the interview table

  Scenario: Creating an Interview with assigned after interview date
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter an Interview date that occurs after my Assigned date
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview and selecting twenty-four hour notice while assinged and interview are less than twenty-four hours apart
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter dates less than twenty-four hours apart
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview and not selecting twenty-four hour notice while assinged and interview are more than twenty-four hours apart
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter dates more than twenty-four hours apart
    And I select an interview type
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview without selecting a client
    Given I am on the Associate Home Page
    When I click the interview tab
    And I enter an Interview date
    And I enter an Assigned date
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview without selecting an interview date
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter an Assigned date
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview without selecting an assigned date
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter an Interview date
    And I enter an Assigned date
    And I select an interview type
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview without selecting a type
    Given I am on the Associate Home Page
    When I click the interview tab
    And I select a client
    And I enter an Interview date
    And I enter an Assigned date
    And I select twenty-four hour notice
    And press the add interview button
    Then an error popup should display
