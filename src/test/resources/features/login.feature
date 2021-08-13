Feature:Online Banking Login Feature

  Background: User login
    Given the user is on the login page

  Scenario: Authorized users should be able to login to the application
    And User logins with username "username" and password "password"
    Then the Account Summary page should be displayed


  Scenario: Unauthorized users should NOT be able to login
    When User logins with username "wrong" and password "wrong"
    Then Error message Login and/or password are wrong. should be displayed
    When User logins with username "" and password ""
    Then Error message Login and/or password are wrong. should be displayed