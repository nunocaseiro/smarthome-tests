Feature: Account Settings
  This feature is intended to allow user edit the information of their user information.
  To do that the user see a block on settings page where he can edit user settings

  Background:
    Given I have the application successfully opened
    And I am at the "settings" page
    And I click "pencil btn" button
    And I see that I am in "edit user" page

  Scenario: Empty inputs
    When I clear all inputs of "edit user" page
    And I click "save edit user" button
    Then I see an error "This field cannot be empty" on "username edit error" edit text
    And I see an error "This field cannot be empty" on "email edit error" edit text
    And I see an error "This field cannot be empty" on "first name error" edit text
    And I see an error "This field cannot be empty" on "last name error" edit text

  Scenario: Valid inputs
    When I clear all inputs of "edit user" page
    And I type "smarthomeqs" on "username edit text"
    And I type "smarthomesq@gmail.com" on "email edit text"
    And I type "Software" on "first name edit text"
    And I type "test" on "last name edit text"
    And I click "save edit user" button
    And I click "pencil btn" button
    And I see that I am in "edit account" page
    Then I check the "username edit text" edit text has "smarthomeqs"
    And I check the "email edit text" edit text has "smarthomesq@gmail.com"
    And I check the "first name edit text" edit text has "Software"
    And I check the "last name edit text" edit text has "test"


  Scenario Outline: Invalid inputs
    When I clear all inputs of "edit user" page
    And I type "<username edit>" on "username edit text"
    And I type "<email edit>" on "email edit text"
    And I type "<name edit>" on "first name edit text"
    And I type "<last name edit>" on "last name edit text"
    And I click "save edit user" button
    Then I see an error "<error message>" on "<label error>" edit text

    Examples:
      | username edit | email edit            | name edit | last name edit | label error         | error message              |
      |               | smarthomesq@gmail.com | Software  | quality        | username edit error | This field cannot be empty |
      | smarthomeqs   |                       | Software  | quality        | email edit error    | This field cannot be empty |
      | smarthomeqs   | smarthomesq@gmail.com |           | quality        | first name error    | This field cannot be empty |
      | smarthomeqs   | smarthomesq@gmail.com | Software  |                | last name error     | This field cannot be empty |
      | smarthomeqs   | smarthomesq           | Software  | quality        | email edit error    | Enter a valid email        |


  Scenario: Valid inputs password
    When I click "check box" button
    And I type "meicm123" on "old password"
    And I type "1Software85" on "new password"
    And I type "1Software85" on "confirm new password"
    And I click "save edit user" button
    Then I see that I am in "settings" page
    And I do logout
    And I do login with new credentials, "smarthomeqs", "1Software85"


  Scenario Outline: invalid password
    When I click "check box" button
    And I type "<old>" on "old password"
    And I type "<new>" on "new password"
    And I type "<confirm>" on "confirm new password"
    And I click "save edit user" button
    Then I see an error "<error>" on "<label>" edit text

    Examples:
      | old      | new       | confirm   | label                      | error                              |
      |          | oteste123 | oteste123 | old password error         | This field cannot be empty         |
      | meicm123 |           | oteste123 | new password error         | This field cannot be empty         |
      | meicm123 | oteste123 |           | confirm new password error | The passwords don't match         |
      | meicm123 | oteste123 | oteste851 | confirm new password error | The passwords don't match          |
      | meicm123 | ola       | ola       | new password error         | Password must have >= 8 characters |
      | meicm123 | ola       | ol        | new password error         | Password must have >= 8 characters |

