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
      | 00u      |
      | user@    |
      | -12      |
      | user_new |
      | 13 User  |
      | loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong |
      | ـثـثـثـثـثـثـثـث |
      | select * from tf_users |
      | *aaaaaaa* |
      | ششششششش |
      
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
      |  ششششششش| ششششششش |
      | *ششششششش* | ششششششش |
      | Aa*ششششششش | Aa*ششششششش |
      | Aaششششششش | Aaششششششش |
      | pW@      | pa@         |
      | 12 aaa 13 | 12 aaa 13  |
      | -1234556ab |  -1234556ab |
      | !@!@!@!@!@!@!a | !@!@!@!@!@!@!a |
      | select * from tf_users | select * from tf_users |
      | a b c d e f 10 | a b c d e f 10 |
      | A3a*ششششششش | A3a*ششششششش |
      
      
      Scenario: Creating a valid trainer
    When I enter a valid username
    And I enter a vaild password
    And I confirm my password
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select a trainer role
    And I click register user
    Then a new user should be registered

  Scenario Outline: Creating an invalid trainer with invalid username
    When I enter a invalid username "<username>"
    And I enter a vaild password
    And I confirm my password
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select a trainer role
    And I click register user
    Then an error message should appear

    Examples: 
      | username |
      |  0000000 |
      | user     |
      | New User |
      | NewUser! |
      | 00u      |
      | user@    |
      | -12      |
      | user_new |
      | 13 User  |
      | loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong |
      | ـثـثـثـثـثـثـثـث |
      | select * from tf_users |
      | *aaaaaaa* |
      | ششششششش |

  Scenario Outline: Creating an invalid trainer with invalid password
    When I enter a valid username
    And I enter a invalid password"<password>"
    And I confirm my invalid password"<password>"
    And I enter a invalid firstname
    And I enter a invalid lastname
    And I select a trainer role
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
      |  ششششششش| ششششششش |
      | *ششششششش* | ششششششش |
      | Aa*ششششششش | Aa*ششششششش |
      | Aaششششششش | Aaششششششش |
      | pW@      | pa@         |
      | 12 aaa 13 | 12 aaa 13  |
      | -1234556ab |  -1234556ab |
      | !@!@!@!@!@!@!a | !@!@!@!@!@!@!a |
      | select * from tf_users | select * from tf_users |
      | Abc*3fd | Abc*3fd |
      | a b c d e f 10 | a b c d e f 10 |
      | A3a*ششششششش | A3a*ششششششش |
      
      