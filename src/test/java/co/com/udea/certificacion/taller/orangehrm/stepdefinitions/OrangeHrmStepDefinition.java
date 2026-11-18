package co.com.udea.certificacion.taller.orangehrm.stepdefinitions;

import co.com.udea.certificacion.taller.orangehrm.tasks.OpenTheBrowser;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.models.User;
import co.com.udea.certificacion.taller.orangehrm.questions.DashboardValidation;
import co.com.udea.certificacion.taller.orangehrm.questions.EmployeeCreation;
import co.com.udea.certificacion.taller.orangehrm.tasks.CreateEmployee;
import co.com.udea.certificacion.taller.orangehrm.tasks.GoToAddEmployee;
import co.com.udea.certificacion.taller.orangehrm.tasks.LogIn;
import co.com.udea.certificacion.taller.orangehrm.questions.LoginAlertMessage;
import co.com.udea.certificacion.taller.orangehrm.questions.RequiredFieldMessage;
import co.com.udea.certificacion.taller.orangehrm.tasks.LogOut;
import co.com.udea.certificacion.taller.orangehrm.tasks.TryToLogIn;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class OrangeHrmStepDefinition {

    @Managed(driver = "chrome")
    WebDriver herBrowser;

    private final Actor administrator = Actor.named("Laura");

    private Employee newEmployee;

    @Before
    public void setUp() {
        administrator.can(BrowseTheWeb.with(herBrowser));
    }

    @Given("that the administrator is on the OrangeHRM login page")
    public void thatTheAdministratorIsOnTheOrangeHrmLoginPage() {
        administrator.wasAbleTo(OpenTheBrowser.onTheLoginPage());
    }

    @DataTableType
    public User userEntry(Map<String, String> entry) {
        return new User(entry.get("username"), entry.get("password"));
    }

    @When("she logs in with her credentials")
    public void sheLogsInWithHerCredentials(List<User> users) {
        administrator.attemptsTo(LogIn.withTheCredentialsOf(users.get(0)));
    }

    @Then("she should see the dashboard")
    public void sheShouldSeeTheDashboard() {
        administrator.should(seeThat(DashboardValidation.isDisplayed()));
    }

    @When("she tries to log in without entering her credentials")
    public void sheTriesToLogInWithoutEnteringHerCredentials() {
        administrator.attemptsTo(TryToLogIn.withoutCredentials());
    }

    @Then("she should see the alert message {string}")
    public void sheShouldSeeTheAlertMessage(String expectedMessage) {
        administrator.should(seeThat(LoginAlertMessage.text(), equalTo(expectedMessage)));
    }

    @Then("she should see the required field message {string}")
    public void sheShouldSeeTheRequiredFieldMessage(String expectedMessage) {
        administrator.should(seeThat(RequiredFieldMessage.text(), equalTo(expectedMessage)));
    }

    @DataTableType
    public Employee employeeEntry(Map<String, String> entry) {
        String uniqueUsername = entry.get("username") + ThreadLocalRandom.current().nextInt(1000, 9999);
        return new Employee(entry.get("firstName"), entry.get("middleName"), entry.get("lastName"),
                uniqueUsername, entry.get("password"));
    }

    @Given("she is on the Add Employee form")
    public void sheIsOnTheAddEmployeeForm() {
        administrator.wasAbleTo(GoToAddEmployee.form());
    }

    @When("she creates a new employee with the following information")
    public void sheCreatesANewEmployeeWithTheFollowingInformation(List<Employee> employees) {
        newEmployee = employees.get(0);
        administrator.attemptsTo(CreateEmployee.withTheInformationOf(newEmployee));
    }

    @When("she logs out")
    public void sheLogsOut() {
        administrator.attemptsTo(LogOut.ofTheSystem());
    }

    @When("she logs in with the credentials of the new employee")
    public void sheLogsInWithTheCredentialsOfTheNewEmployee() {
        administrator.attemptsTo(LogIn.withTheCredentialsOf(
                new User(newEmployee.getUsername(), newEmployee.getPassword())));
    }

    @Then("she should see the personal details of the new employee")
    public void sheShouldSeeThePersonalDetailsOfTheNewEmployee() {
        administrator.should(seeThat(EmployeeCreation.fullNameDisplayed(),
                allOf(containsString(newEmployee.getFirstName()),
                        containsString(newEmployee.getLastName()))));
    }
}
