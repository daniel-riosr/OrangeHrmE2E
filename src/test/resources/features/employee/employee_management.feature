Feature: Employee management in OrangeHRM
  As an HR administrator
  I need to register new employees
  To keep the employees information up to date

  Background:
    Given that the administrator is on the OrangeHRM login page
    And she logs in with her credentials
      | username | password |
      | Admin    | admin123 |

  Scenario: Successful registration of a new employee
    When she registers a new employee with the following information
      | firstName | middleName | lastName | username | password |
      | Sofia     | Isabel     | Ramirez  | sofiar   | Sofia123 |
    Then she should see the personal details of the new employee

  Scenario Outline: Employee registration fails with invalid data
    When she tries to register a new employee with the following information
      | firstName   | middleName   | lastName   | username   | password   |
      | <firstName> | <middleName> | <lastName> | <username> | <password> |
    Then she should see the field error message "<message>"

    Examples:
      | firstName | middleName | lastName | username       | password | message                           |
      | [blank]   | Isabel     | Ramirez  | sofiaramirezqa | Sofia123 | Required                          |
      | Sofia     | Isabel     | [blank]  | sofiaramirezqa | Sofia123 | Required                          |
      | Sofia     | Isabel     | Ramirez  | Admin          | Sofia123 | Username already exists           |
      | Sofia     | Isabel     | Ramirez  | sof            | Sofia123 | Should be at least 5 characters   |
      | Sofia     | Isabel     | Ramirez  | sofiaramirezqa | Sof12    | Should have at least 7 characters |
