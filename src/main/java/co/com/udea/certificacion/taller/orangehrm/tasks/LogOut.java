package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.utils.MaximumWaitTime;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.USERNAME_INPUT;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.UserMenuPage.LOGOUT_OPTION;
import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.UserMenuPage.USER_DROPDOWN;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LogOut implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(USER_DROPDOWN, isClickable()).forNoMoreThan(MaximumWaitTime.inSeconds()).seconds(),
                Click.on(USER_DROPDOWN));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(
                WaitUntil.the(LOGOUT_OPTION, isClickable()).forNoMoreThan(MaximumWaitTime.inSeconds()).seconds(),
                Click.on(LOGOUT_OPTION),
                WaitUntil.the(USERNAME_INPUT, isVisible()).forNoMoreThan(MaximumWaitTime.inSeconds()).seconds());
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static LogOut ofTheSystem() {
        return Tasks.instrumented(LogOut.class);
    }
}
