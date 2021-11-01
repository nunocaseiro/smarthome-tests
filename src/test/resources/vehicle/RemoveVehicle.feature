Feature: Remove vehicle
  This feature is intended to delete an existing vehicle.
  First, it will be necessary to login in the application. Then, the user goes to the vehicle menu and clicks in an existing vehicle.
  Finally the user deletes that chosen vehicle.

  Background:
    Given I have the application successfully opened
    And I am at the "vehicle" page
    And I check the "empty vehicles" edit text has "There are no vehicles"
    And I create a vehicle
    And I "see" a vehicle at the top of the list
    And I click in a vehicle
    And I see that I am in "edit vehicle" page

  Scenario: Delete vehicle
    When I click "delete" button
    And I see an alert dialog saying "Do you really want to delete this vehicle?"
    And I click "yes" button
    And I see an alert dialog saying "Vehicle deleted"
    And I click "ok dialog" button
    Then I see that I am in "vehicle" page
    And I confirm that the vehicle has been "deleted"

  Scenario: Delete vehicle cancellation
    When I click "delete" button
    And I see an alert dialog saying "Do you really want to delete this vehicle?"
    And I click "no" button
    Then I see that I am in "edit vehicle" page