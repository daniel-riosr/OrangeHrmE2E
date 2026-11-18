package co.com.udea.certificacion.taller.orangehrm.stepdefinitions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.models.User;
import co.com.udea.certificacion.taller.orangehrm.questions.DashboardValidation;
import co.com.udea.certificacion.taller.orangehrm.questions.EmployeeCreation;
import co.com.udea.certificacion.taller.orangehrm.questions.EmployeeFormErrorMessage;
import co.com.udea.certificacion.taller.orangehrm.questions.LoginAlertMessage;
import co.com.udea.certificacion.taller.orangehrm.questions.RequiredFieldMessage;
import co.com.udea.certificacion.taller.orangehrm.tasks.LogIn;
import co.com.udea.certificacion.taller.orangehrm.tasks.LogOut;
import co.com.udea.certificacion.taller.orangehrm.tasks.OpenTheBrowser;
import co.com.udea.certificacion.taller.orangehrm.tasks.RegisterEmployee;
import co.com.udea.certificacion.taller.orangehrm.tasks.TryToLogIn;
import co.com.udea.certificacion.taller.orangehrm.tasks.TryToRegisterEmployee;
import co.com.udea.certificacion.taller.orangehrm.utils.EmployeeCredentials;
import co.com.udea.certificacion.taller.orangehrm.utils.UniqueData;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class OrangeHrmStepDefinition {

    @Managed(driver = "chrome")
    WebDriver herBrowser;

    private final Actor administrator = Actor.named("Laura");

    private Actor newEmployeeActor;

    private Employee newEmployee;

    @Before
    public void setUp() {
        administrator.can(BrowseTheWeb.with(herBrowser));
    }

    @DataTableType
    public User userEntry(Map<String, String> entry) {
        return new User(entry.get("username"), entry.get("password"));
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public Employee employeeEntry(Map<String, String> entry) {
        return new Employee(entry.get("firstName"), entry.get("middleName"), entry.get("lastName"),
                UniqueData.employeeId(), entry.get("username"), entry.get("password"));
    }

    @Given("that the administrator is on the OrangeHRM login page")
    public void thatTheAdministratorIsOnTheOrangeHrmLoginPage() {
        administrator.wasAbleTo(OpenTheBrowser.onTheLoginPage());
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

    @When("she registers a new employee with the following information")
    public void sheRegistersANewEmployeeWithTheFollowingInformation(List<Employee> employees) {
        newEmployee = UniqueData.makeUnique(employees.get(0));
        administrator.attemptsTo(RegisterEmployee.withTheInformationOf(newEmployee));
    }

    @Then("she should see the personal details of the new employee")
    public void sheShouldSeeThePersonalDetailsOfTheNewEmployee() {
        administrator.should(seeThat(EmployeeCreation.fullNameDisplayed(),
                allOf(containsString(newEmployee.getFirstName()),
                        containsString(newEmployee.getLastName()))));
    }

    @When("she tries to register a new employee with the following information")
    public void sheTriesToRegisterANewEmployeeWithTheFollowingInformation(List<Employee> employees) {
        administrator.attemptsTo(TryToRegisterEmployee.withTheInformationOf(employees.get(0)));
    }

    @Then("she should see the employee form error message {string}")
    public void sheShouldSeeTheEmployeeFormErrorMessage(String expectedMessage) {
        administrator.should(seeThat(EmployeeFormErrorMessage.text(), equalTo(expectedMessage)));
    }

    @Given("she has registered a new employee with system access")
    public void sheHasRegisteredANewEmployeeWithSystemAccess(List<Employee> employees) {
        newEmployee = UniqueData.makeUnique(employees.get(0));
        administrator.wasAbleTo(RegisterEmployee.withTheInformationOf(newEmployee), LogOut.ofTheSystem());
        newEmployeeActor = Actor.named(newEmployee.getFirstName()).whoCan(BrowseTheWeb.with(herBrowser));
    }

    @When("the new employee logs in with his credentials")
    public void theNewEmployeeLogsInWithHisCredentials() {
        newEmployeeActor.attemptsTo(LogIn.withTheCredentialsOf(EmployeeCredentials.of(newEmployee)));
    }

    @When("the new employee logs in with the password {string}")
    public void theNewEmployeeLogsInWithThePassword(String password) {
        newEmployeeActor.attemptsTo(LogIn.withTheCredentialsOf(EmployeeCredentials.withPassword(newEmployee, password)));
    }

    @Then("he should see the dashboard")
    public void heShouldSeeTheDashboard() {
        newEmployeeActor.should(seeThat(DashboardValidation.isDisplayed()));
    }

    @Then("he should see the alert message {string}")
    public void heShouldSeeTheAlertMessage(String expectedMessage) {
        newEmployeeActor.should(seeThat(LoginAlertMessage.text(), equalTo(expectedMessage)));
    }
}
