Feature: Edit Home
  This feature is intended to allow user edit the information of their house like house's name, latitude and longitude.
  To do that the user see a block on settings page where he can edit directly the house information and press save
  First, it will be need to open the application and do login successfully and move application to Setting page

  Background:
    Given I have the application successfully opened
    And I am at the "settings" page

  Scenario: Empty inputs
    When I clear all inputs of "settings" page
    And I click "SAVE1" button
    Then I see an error "This field cannot be empty" on all edit text of "settings" page

  Scenario: Valid inputs
    When I clear all inputs of "settings" page
    And I type "SQ Home" on "Home Edit"
    And I type "39.74" on "Latitude Edit"
    And I type "8.84" on "Longitude Edit"
    And I click "SAVE1" button
    Then I see an alert dialog saying "Home was updated."
    And I click "ok dialog" button
    And I check the "Latitude Edit" edit text has "39.74"
    And I check the "Longitude Edit" edit text has "8.84"

  Scenario Outline: Check invalid inputs
    When I clear all inputs of "settings" page
    And I type "<home name>" on "Home Edit"
    And I type "<latitude>" on "Latitude Edit"
    And I type "<longitude>" on "Longitude Edit"
    And I click "SAVE1" button
    Then I see an error "<error message>" on "<label error>" edit text

    Examples:
      | home name    | latitude  | longitude            | label error                          | error message                          |
      | Sq casa      | 9999      | -8.84                | Latitude Edit Error                  | Latitude must be between -90 and 90    |
      | Sq casa      | 39.74     | 99999                | Longitude Edit Error                 | Longitude must be between -180 and 80  |
      | Sq casa      |           | -8.84                | Latitude Edit Error                  | Latitude cannot be empty               |
      | Sq casa      | 39.74     |                      | Longitude Edit Error                 | Longitude cannot be empty              |
      |              | 39.74     | -8.84                | Home Edit Error                      | Name cannot be empty                   |
      | Sq casa      | 39.74     | 99999                | Longitude Edit Error                 | Longitude must be between -180 and 80  |
