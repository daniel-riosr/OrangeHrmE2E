package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.AddEmployeePage.SAVE_BUTTON;

public class SaveTheEmployee implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(SAVE_BUTTON));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static SaveTheEmployee information() {
        return Tasks.instrumented(SaveTheEmployee.class);
    }
}
