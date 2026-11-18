Feature: Onboarding of a new employee in OrangeHRM
  As an HR administrator
  I need the employees I register to be able to log in
  To make sure their access works from the start

  Background:
    Given that the administrator is on the OrangeHRM login page
    And she logs in with her credentials
      | username | password |
      | Admin    | admin123 |

  Scenario: The new employee logs in with his credentials
    Given she has registered a new employee with system access
      | firstName | lastName | username | password  |
      | Carlos    | Perez    | carlosp  | Carlos123 |
    When the new employee logs in with his credentials
    Then he should see the dashboard

  Scenario: The new employee cannot log in with a wrong password
    Given she has registered a new employee with system access
      | firstName | lastName | username | password  |
      | Carlos    | Perez    | carlosp  | Carlos123 |
    When the new employee logs in with the password "Wrong123"
    Then he should see the alert message "Invalid credentials"
