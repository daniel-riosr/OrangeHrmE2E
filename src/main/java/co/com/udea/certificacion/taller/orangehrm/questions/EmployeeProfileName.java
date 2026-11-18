package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.EmployeeDetailsPage.EMPLOYEE_FULL_NAME;

public class EmployeeProfileName implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return EMPLOYEE_FULL_NAME.resolveFor(actor).getText().trim();
    }

    public static EmployeeProfileName displayed() {
        return new EmployeeProfileName();
    }
}
