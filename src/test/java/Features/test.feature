Feature: To check the simple form demo

  Background:
    Given User is in the home page

  Scenario: Validate Simple Form Demo
    Given User is in the simple form demo page
    When User enters text on the checkbox
    And User clicks on checked value button
    Then Message should get displayed
