package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.EndTheSession;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LogOut implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(EndTheSession.fromTheUserMenu());
    }

    public static LogOut ofTheSystem() {
        return Tasks.instrumented(LogOut.class);
    }
}
