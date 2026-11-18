package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.MenuPage.ADD_EMPLOYEE_TAB;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.MenuPage.PIM_MENU;

public class OpenTheAddEmployeeForm implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(PIM_MENU));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Click.on(ADD_EMPLOYEE_TAB));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static OpenTheAddEmployeeForm fromThePimMenu() {
        return Tasks.instrumented(OpenTheAddEmployeeForm.class);
    }
}
