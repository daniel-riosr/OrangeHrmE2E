package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.*;

public class FillLoginDetails implements Interaction {

    private final Employee employee;

    public FillLoginDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CREATE_LOGIN_TOGGLE));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(employee.getUsername()).into(USERNAME_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(employee.getPassword()).into(PASSWORD_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(employee.getPassword()).into(CONFIRM_PASSWORD_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static FillLoginDetails with(Employee employee) {
        return Tasks.instrumented(FillLoginDetails.class, employee);
    }
}
