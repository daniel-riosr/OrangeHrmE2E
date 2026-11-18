Feature: Login with a newly created employee
  As an HR administrator
  I need the employees I create to be able to log in
  To make sure their access works from the start

  Background:
    Given that the administrator is on the OrangeHRM login page
    And she logs in with her credentials
      | username | password |
      | Admin    | admin123 |

  Scenario: Successful login with the credentials of a newly created employee
    Given she is on the Add Employee form
    And she creates a new employee with the following information
      | firstName | middleName | lastName | username | password  |
      | Carlos    | Andres     | Perez    | carlosp  | Carlos123 |
    When she logs out
    And she logs in with the credentials of the new employee
    Then she should see the dashboard
