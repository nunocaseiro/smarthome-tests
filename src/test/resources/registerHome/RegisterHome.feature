Feature: Register Home
  This feature is intended to create a home. After inserted the valid key on the feature "InsertHomeKey" this new activity is shown to user.
  Here it's possible to insert house's name, latitude and longitude for their house and create a new one with inserted data.
  The latitude and longitude data are for the weather feature on dashboard. It calls an external api with inserted lat and long. The response is used to show the weather for user.
  The inputs by default for latitude and longitude only allow numbers, - and points.

  Background:
    Given I have the application successfully opened
    And I see that I am in "login" page
    And I click "Want to create a new home" button
    And I insert a valid key
    And I click "submit" button
    And I see that I am in "register Home" page

  Scenario: Empty fields - all
    When I clear all inputs of "register home" page
    And I click "create house" button
    Then I see an error "This field cannot be empty" on all edit text of "register home" page

  Scenario Outline: Invalid latitude and longitude
    When I type "<latitude>" on "latitude edit text"
    And I type "<longitude>" on "longitude edit text"
    And I click "create house" button
    Then I see an error "<messageLatitude>" on "latitude error" edit text
    And I see an error "<messageLongitude>" on "longitude error" edit text

    Examples:
      | latitude | longitude | messageLatitude                    | messageLongitude                      |
      | -100     | -200      | This latitude is invalid. [-90,90] | This longitude is invalid. [-180,180] |
      | 100      | 200       | This latitude is invalid. [-90,90] | This longitude is invalid. [-180,180] |
      | -56..1   | 100..2    | This latitude is invalid. [-90,90] | This longitude is invalid. [-180,180] |
      | 77...6   | -170...9  | This latitude is invalid. [-90,90] | This longitude is invalid. [-180,180] |

  Scenario: Insert valid fields and create home
    When I type "Casa QS" on "house name edit text"
    And I create a house
    And I ask to server if a "home" with inserted name was created
    Then I see that I am in "create user" page