package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.UserMenuPage.LOGOUT_OPTION;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.UserMenuPage.USER_DROPDOWN;

public class LogOut implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Doble espera: la página termina de guardar y redirigir antes de cerrar sesión
        WaitLittleBitTime.waitLittleBitTime();
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Click.on(USER_DROPDOWN));
        WaitLittleBitTime.waitLittleBitTime();
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Click.on(LOGOUT_OPTION));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static LogOut ofTheSystem() {
        return Tasks.instrumented(LogOut.class);
    }
}
