package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.LOGIN_BUTTON;

public class TryToLogIn implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(LOGIN_BUTTON));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static TryToLogIn withoutCredentials() {
        return Tasks.instrumented(TryToLogIn.class);
    }
}
