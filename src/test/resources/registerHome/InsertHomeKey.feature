Feature: Insert HomeKey
  This feature is intended to insert the key to initialize the home creation process.
  At login page the user have one button to navigate to activity where it's possible to insert one home key with an edittext field to that effect.
  If its valid a new activity is opened and the user can create a home.
  The key's input only allows numbers by default.

  Background:
    Given I have the application successfully opened
    And I see that I am in "login" page
    And I click "Want to create a new home" button
    And I see a "key edit text"

  Scenario Outline: Insert letters, characters and numbers
    When I type "<text>" on "key edit text"
    And I check the "key edit text" edit text has "<text>"
    And I click "submit" button
    Then I see an error "<message>" on "key error" edit text

    Examples:
      | text         | message                   |
      | !#.-abcd     | Please insert a valid key |
      | !#.-1234abcd | Please insert a valid key |

  Scenario: Empty key
    When I clear "key edit text"
    And I check "key edit text" is clear
    And I click "submit" button
    Then I see an error "Please insert a key" on "key error" edit text

  Scenario: Insert valid key and house isn't created
    When I insert a valid key
    And I click "submit" button
    Then I see that I am in "register home" page

  Scenario: Insert valid key and house is already created and with admin
    When I insert a valid key
    And I click "submit" button
    And I create a house
    And I create an admin user
    And I do logout
    And I click "Want to create a new home" button
    And I insert a valid key
    And I click "submit" button
    Then I see an alert dialog saying "Home and admin already exists. You will return to login page."

  Scenario: Insert valid key and house is created but dont have admin user and i press no
    When I insert a valid key
    And I click "submit" button
    And I create a house
    And I press back
    And I see that I am in "login" page
    And I click "Want to create a new home" button
    And I insert a valid key
    And I click "submit" button
    And I see an alert dialog saying "Home already exists with this code. However there are no admin. Do you want create one?"
    And I click "no" button
    Then I see that I am in "login" page

  Scenario: Insert valid key and house is created but dont have admin user and i press yes
    When I insert a valid key
    And I click "submit" button
    And I create a house
    And I press back
    And I see that I am in "login" page
    And I click "Want to create a new home" button
    And I insert a valid key
    And I click "submit" button
    And I see an alert dialog saying "Home already exists with this code. However there are no admin. Do you want create one?"
    And I click "yes" button
    And I create an admin user
    Then I check the "account name" edit text has "Qualidade"