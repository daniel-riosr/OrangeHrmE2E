package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.*;

public class FillEmployeeForm implements Interaction {

    private final Employee employee;

    public FillEmployeeForm(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(employee.getFirstName()).into(FIRST_NAME_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(employee.getMiddleName()).into(MIDDLE_NAME_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(employee.getLastName()).into(LAST_NAME_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static FillEmployeeForm with(Employee employee) {
        return Tasks.instrumented(FillEmployeeForm.class, employee);
    }
}
