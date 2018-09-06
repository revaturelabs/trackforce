Feature: Testing the registration for new associates

  Background: 
    Given I connect to caliber
    And I click on the register button

  Scenario: Creating a valid associate
    When I enter a valid username
    And I enter a vaild password
    And I confirm my password
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select an associate role
    And I click register user
    Then a new user should be registered

  Scenario Outline: Creating an invalid associate with invalid username
    When I enter a invalid username "<username>"
    And I enter a vaild password
    And I confirm my password
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select an associate role
    And I click register user
    Then an error message should appear

    Examples: 
      | username |
      |  0000000 |
      | user     |
      | New User |
      | NewUser! |
      |          |

  Scenario Outline: Creating an invalid associate with invalid password
    When I enter a valid username
    And I enter a invalid password"<password>"
    And I confirm my invalid password"<password>"
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select an associate role
    And I click register user
    Then an error message should appear

    Examples: 
      | password | confPassword |
      | NewA!    | NewA!        |
      | NewUsers | NewUsers     |
      | newuser! | newuser!     |
      | NEWUSER! | NEWUSER!     |
      |          |              |
      | NewUser! | Not the same |
      | NewUser! |              |
