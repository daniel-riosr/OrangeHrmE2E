package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.FIRST_NAME_INPUT;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.LAST_NAME_INPUT;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.MIDDLE_NAME_INPUT;

public class FillEmployeeForm implements Interaction {

    private final Employee employee;

    public FillEmployeeForm(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        enterIfNotBlank(actor, employee.getFirstName(), FIRST_NAME_INPUT);
        enterIfNotBlank(actor, employee.getMiddleName(), MIDDLE_NAME_INPUT);
        enterIfNotBlank(actor, employee.getLastName(), LAST_NAME_INPUT);
    }

    // Un campo vacio en el feature significa "dejar el campo sin llenar" (caso de excepcion)
    private <T extends Actor> void enterIfNotBlank(T actor, String value, Target field) {
        if (value != null && !value.isBlank()) {
            actor.attemptsTo(Enter.theValue(value).into(field));
        }
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static FillEmployeeForm with(Employee employee) {
        return Tasks.instrumented(FillEmployeeForm.class, employee);
    }
}
