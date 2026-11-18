package co.com.udea.certificacion.taller.orangehrm.runners.e2e;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/e2e/employee_onboarding.feature",
        glue = "co.com.udea.certificacion.taller.orangehrm.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class EmployeeOnboardingRunner {}
