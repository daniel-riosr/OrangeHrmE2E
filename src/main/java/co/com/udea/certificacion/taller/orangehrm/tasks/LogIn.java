package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.Authenticate;
import co.com.udea.certificacion.taller.orangehrm.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LogIn implements Task {

    private final User user;

    public LogIn(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Authenticate.withCredentials(user));
    }

    public static LogIn withTheCredentialsOf(User user) {
        return Tasks.instrumented(LogIn.class, user);
    }
}