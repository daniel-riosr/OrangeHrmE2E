package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.models.User;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.*;

public class Authenticate implements Interaction {

    private final User user;

    public Authenticate(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(user.getUsername()).into(USERNAME_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Enter.theValue(user.getPassword()).into(PASSWORD_INPUT));
        WaitLittleBitTime.waitLittleBitTime();
        actor.attemptsTo(Click.on(LOGIN_BUTTON));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static Authenticate withCredentials(User user) {
        return Tasks.instrumented(Authenticate.class, user);
    }
}