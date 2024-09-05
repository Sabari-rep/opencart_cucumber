Feature: Account Registration

  @regression
  Scenario: Successfull account Registration
    Given the user navigates to account registration page
    When the user enters the details into beow field
      | First name | Billa     |
      | Last name  | D         |
      | telephone  |   1234567 |
      | password   | billa@123 |
    And the user selects the privacy policy check box
    And the user click the continue button
    Then account registration got successful
