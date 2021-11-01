Feature: Edit vehicle
  This feature is intended to update an existing vehicle.
  First, it will be necessary to login in the application. Then, the user goes to the vehicle menu and clicks in an existing vehicle.
  Finally the user edits the wanted fields of that chosen vehicle.

  Background:
    Given I have the application successfully opened
    And I am at the "vehicle" page
    And I check the "empty vehicles" edit text has "There are no vehicles"
    And I create a vehicle
    And I "see" a vehicle at the top of the list
    And I click in a vehicle
    And I see that I am in "edit vehicle" page
    And I clear all inputs of "edit vehicle" page

  Scenario: Update vehicle
    When I type "Porsche" on "brand edit text"
    And I type "Panamera" on "model edit text"
    And I type "88XX88" on "license plate edit text"
    And I type "2018" on "year edit text"
    And I click "save" button
    And I see an alert dialog saying "Vehicle updated"
    And I click "ok dialog" button
    Then I see that I am in "vehicle" page
    And I confirm that the vehicle has been "updated"

  Scenario: Empty inputs
    When I click "save" button
    Then I see an error "This field cannot be empty" on all edit text of "edit vehicle" page

  Scenario Outline: Check all invalid inputs combinations
    When I type "Porsche" on "brand edit text"
    And I type "Panamera" on "model edit text"
    And I type "<license plate>" on "license plate edit text"
    And I type "<year>" on "year edit text"
    And I click "save" button
    Then I see that I am in "edit vehicle" page
    And I "<license plate status>" an error "<license plate error message>" on "license plate error" edit text
    And I "<year status>" an error "<year error message>" on "year error" edit text

    Examples:
      | license plate | year | license plate status | license plate error message          | year status | year error message                 |
      | 88XX88        | 1949 | don't see            |                                      | see         | Year must be between 1950 and 2050 |
      | 88XX88        | 2051 | don't see            |                                      | see         | Year must be between 1950 and 2050 |
      | 88XX888       | 2050 | see                  | License plate must have 6 characters | don't see   |                                    |
      | 88XX8         | 2050 | see                  | License plate must have 6 characters | don't see   |                                    |
      | 88XX8         | 1949 | see                  | License plate must have 6 characters | see         | Year must be between 1950 and 2050 |
      | 88XX888       | 2051 | see                  | License plate must have 6 characters | see         | Year must be between 1950 and 2050 |