Feature: Login to the OrangeHRM system
  As an HR administrator
  I need to log in to OrangeHRM
  To manage the employees information

  Background:
    Given that the administrator is on the OrangeHRM login page

  Scenario: Successful login with valid credentials
    When she logs in with her credentials
      | username | password |
      | Admin    | admin123 |
    Then she should see the dashboard

  Scenario Outline: Login fails with invalid credentials
    When she logs in with her credentials
      | username   | password   |
      | <username> | <password> |
    Then she should see the alert message "<message>"

    Examples:
      | username        | password | message             |
      | Admin           | wrong123 | Invalid credentials |
      | nonexistentUser | admin123 | Invalid credentials |

  Scenario: Login fails when the fields are empty
    When she tries to log in without entering her credentials
    Then she should see the field error message "Required"
