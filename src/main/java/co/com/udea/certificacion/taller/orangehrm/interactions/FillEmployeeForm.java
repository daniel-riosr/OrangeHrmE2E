package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SendKeys;
import org.openqa.selenium.Keys;

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
        replaceTheEmployeeId(actor);
        WaitLittleBitTime.waitLittleBitTime();
    }

    private <T extends Actor> void replaceTheEmployeeId(T actor) {
        WebElementFacade employeeIdField = EMPLOYEE_ID_INPUT.resolveFor(actor);
        employeeIdField.waitForCondition().until(browser -> !employeeIdField.getValue().isBlank());
        actor.attemptsTo(
                SendKeys.of(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE).into(EMPLOYEE_ID_INPUT),
                SendKeys.of(employee.getEmployeeId()).into(EMPLOYEE_ID_INPUT));
        employeeIdField.waitForCondition()
                .until(browser -> employeeIdField.getValue().equals(employee.getEmployeeId()));
    }

    public static FillEmployeeForm with(Employee employee) {
        return Tasks.instrumented(FillEmployeeForm.class, employee);
    }
}
