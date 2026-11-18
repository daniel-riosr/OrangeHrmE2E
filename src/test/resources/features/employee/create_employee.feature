Feature: Employee creation in OrangeHRM
  As an HR administrator
  I need to create new employees
  To keep the employees information up to date

  Background:
    Given that the administrator is on the OrangeHRM login page
    And she logs in with her credentials
      | username | password |
      | Admin    | admin123 |

  Scenario: Successful employee creation with valid data
    Given she is on the Add Employee form
    When she creates a new employee with the following information
      | firstName | middleName | lastName | username | password |
      | Laura     | Maria      | Gomez    | laurag   | Laura123 |
    Then she should see the personal details of the new employee
