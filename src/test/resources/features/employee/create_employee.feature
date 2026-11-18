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

  Scenario Outline: Employee creation fails with invalid data
    Given she is on the Add Employee form
    When she tries to create a new employee with the following information
      | firstName   | middleName   | lastName   | username   | password   |
      | <firstName> | <middleName> | <lastName> | <username> | <password> |
    Then she should see the employee form error message "<message>"

    Examples:
      | firstName | middleName | lastName | username | password | message                           |
      |           | Maria      | Gomez    | laurag   | Laura123 | Required                          |
      | Laura     | Maria      |          | laurag   | Laura123 | Required                          |
      | Laura     | Maria      | Gomez    | Admin    | Laura123 | Username already exists           |
      | Laura     | Maria      | Gomez    | lau      | Laura123 | Should be at least 5 characters   |
      | Laura     | Maria      | Gomez    | laurag   | Lau12    | Should have at least 7 characters |
