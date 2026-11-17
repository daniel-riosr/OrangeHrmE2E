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