Feature: Create user
  This feature is intended to create a new user. To do that the information about username, first name, last name, email
  ,and password must be inserted on the fields for that.
  If all information are correctly inserted then the user is created.

  Background:
    Given I have the application successfully opened
    And I see that I am in "login" page
    And I click "Want to create a new home" button
    And I insert a valid key
    And I click "submit" button
    And I see that I am in "register Home" page
    And I type "Casa QS" on "house name edit text"
    And I type "39.73" on "latitude edit text"
    And I type "-8.82" on "longitude edit text"
    And I click "create house" button
    And I ask to server if a "home" with inserted name was created
    And I see that I am in "create user" page

  Scenario: Empty inputs
    When I clear all inputs of "create user" page
    And I click "create account" button
    Then I see an error "This field cannot be empty" on all edit text of "create user" page

  Scenario Outline: Check all inputs combinations
    When I type "<username>" on "username edit text"
    And I type "<email>" on "email edit text"
    And I type "NewQs" on "first name edit text"
    And I type "Admin" on "last name edit text"
    And I type "<password>" on "password edit text"
    And I type "<password confirmation>" on "password confirmation edit text"
    And I click "create account" button
    Then I "<seeUserError>" an error "<usernameError>" on "username error" edit text
    And I "<seeEmailError>" an error "<emailError>" on "email edit error" edit text
    And I "<seePwError>" an error "<password error>" on "password error" edit text
    And I "<seePwCError>" an error "<password confirmation error>" on "password confirmation error" edit text

    Examples:
      | username   | email               | password    | password confirmation | seeUserError | usernameError                 | seeEmailError | emailError                | seePwError | password error                     | seePwCError | password confirmation error |
      | newsqadmin | myqsemail@gmail.com | softqual21  | softqual              | don't see    |                               | don't see     |                           | don't see  |                                    | see         | The passwords don't match   |
      | newsqadmin | myqsemail@gmail.com | soft        | soft                  | don't see    |                               | don't see     |                           | see        | Password must have >= 8 characters | don't see   |                             |
      | arciteste  | arci@teste.pt       | qwerty12    | qwerty12              | see          | That user name already exists | see           | That email already exists | see        | This password is too common        | don't see   |                             |
      | arcinew    | arcinew@            | n3wpassw0rd | n3wpassw0rd           | don't see    |                               | see           | Enter a valid email       | don't see  |                                    | don't see   |                             |

  Scenario: Valid inputs
    When I create an admin user
    Then I see that I am in "dashboard" page
    And I ask to server if a "user" with inserted name was created
    And I check the "account name" edit text has "Qualidade"