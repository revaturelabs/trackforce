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
  Scenario Outline: Create new admin
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Administrator role
    Then I press submit

    Examples: 
      | username    | password |
      | "testAdmin" | "1234"   |
      | "Admintest" | "monkey" |

  @FailedtoCreate
  Scenario Outline: Bad Entry
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Administrator role
    Then I press submit
    And Pop Up Error should occur

    Examples: 
      | username    | password |
      | ""          | "1234"   |
      | "Admintest" | ""       |

  @NoUserTypeSelected
  Scenario Outline: No User Type Selected
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    Then I press submit
    And Pop Up Error should occur

    Examples: 
      | username        | password        |
      | "ValidUsername" | "ValidPassword" |

  @CreateNewManager
  Scenario Outline: Create new manager
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Manager role
    Then I press submit

    Examples: 
      | username      | password   |
      | "testManager" | "12345"    |
      | "Managertest" | "managing" |

  @CreateNewTrainer
  Scenario Outline: Create new Trainer
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Trainer role
    Then I press submit

    Examples: 
      | username      | password  |
      | "testTrainer" | "123"     |
      | "Trainertest" | "Trainer" |

  @CreateNewDelivaryUser
  Scenario Outline: Create new Delivary
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Delivary role
    Then I press submit

    Examples: 
      | username        | password |
      | "testDeveloper" | "123343" |
      | "Developertest" | "Dev"    |

  @CreateNewAssociate
  Scenario Outline: Create new Associate
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a <username> username
    And I type in a <password> password
    And I confirm the <password>
    And I check the Associate role
    Then I press submit

    Examples: 
      | username        | password    |
      | "Associatetest" | "Associate" |
