package co.com.udea.certificacion.taller.orangehrm.runners.authentication;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/authentication/login_new_employee.feature",
        glue = "co.com.udea.certificacion.taller.orangehrm.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class LoginNewEmployeeRunner {}
