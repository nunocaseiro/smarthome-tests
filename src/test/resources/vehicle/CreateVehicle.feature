Feature: Create vehicle
  This feature is intended to create a new vehicle.
  First, it will be necessary to login in the application. Then, the user goes to the vehicle menu and clicks the '+' button to add a new vehicle.
  Finally the user adds valid information about the vehicle and creates it.

  Background:
    Given I have the application successfully opened
    And I am at the "vehicle" page
    And I check the "empty vehicles" edit text has "There are no vehicles"
    And I click "add vehicle" button
    And I see that I am in "create vehicle" page

  Scenario: Empty inputs
    When I clear all inputs of "create vehicle" page
    And I click "save" button
    Then I see an error "This field cannot be empty" on all edit text of "create vehicle" page

  Scenario: Valid inputs
    When I type "Tesla" on "brand edit text"
    And I type "S" on "model edit text"
    And I type "99ZZ99" on "license plate edit text"
    And I type "2019" on "year edit text"
    And I click "save" button
    And I see an alert dialog saying "Vehicle added"
    And I click "ok dialog" button
    Then I see that I am in "vehicle" page
    And I confirm that the vehicle has been "created"

  Scenario Outline: Check all invalid inputs combinations
    When I type "Tesla" on "brand edit text"
    And I type "S" on "model edit text"
    And I type "<license plate>" on "license plate edit text"
    And I type "<year>" on "year edit text"
    And I click "save" button
    Then I see that I am in "create vehicle" page
    And I "<license plate status>" an error "<license plate error message>" on "license plate error" edit text
    And I "<year status>" an error "<year error message>" on "year error" edit text

    Examples:
      | license plate | year | license plate status | license plate error message          | year status | year error message                 |
      | 99ZZ99        | 1949 | don't see            |                                      | see         | Year must be between 1950 and 2050 |
      | 99ZZ99        | 2051 | don't see            |                                      | see         | Year must be between 1950 and 2050 |
      | 99ZZ999       | 2050 | see                  | License plate must have 6 characters | don't see   |                                    |
      | 99ZZ9         | 2050 | see                  | License plate must have 6 characters | don't see   |                                    |
      | 99ZZ9         | 1949 | see                  | License plate must have 6 characters | see         | Year must be between 1950 and 2050 |
      | 99ZZ999       | 2051 | see                  | License plate must have 6 characters | see         | Year must be between 1950 and 2050 |