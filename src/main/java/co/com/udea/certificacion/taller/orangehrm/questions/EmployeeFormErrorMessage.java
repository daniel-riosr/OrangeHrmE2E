package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.FIELD_ERROR_MESSAGE;

public class EmployeeFormErrorMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return FIELD_ERROR_MESSAGE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static EmployeeFormErrorMessage text() {
        return new EmployeeFormErrorMessage();
    }
}
