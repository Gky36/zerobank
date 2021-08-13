Feature: Add new payee under pay bills
  Background:
    Given the user is on the login page
    And User logins with username "username" and password "password"
    And the user navigates to "Pay Bills" menu

  Scenario: Add a new payee
    Given Add New Payee tab
    And creates new payee using following information
      | Payee_Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee_Address | 100 Same st, Anytown, USA, 10001         |
      | Payee_Account | Checking                                 |
      | Payee_Details | XYZ account                              |
    Then message "The new payee The Law Offices of Hyde, Price & Scharks was successfully created" should be displayed


  Scenario: Available currencies
    Given the user accesses the Purchase foreign currency cash tab
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |


  Scenario: Error message for not selecting currency
    Given the user accesses the Purchase foreign currency cash tab
    When user tries to calculate cost without selecting a currency
    Then error message should be displayed