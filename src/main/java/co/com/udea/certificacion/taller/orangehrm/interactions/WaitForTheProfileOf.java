package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.utils.MaximumWaitTime;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.EmployeeDetailsPage.EMPLOYEE_FULL_NAME;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;

public class WaitForTheProfileOf implements Interaction {

    private final Employee employee;

    public WaitForTheProfileOf(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(EMPLOYEE_FULL_NAME, containsText(employee.getFirstName()))
                        .forNoMoreThan(MaximumWaitTime.inSeconds()).seconds());
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static WaitForTheProfileOf employee(Employee employee) {
        return Tasks.instrumented(WaitForTheProfileOf.class, employee);
    }
}
