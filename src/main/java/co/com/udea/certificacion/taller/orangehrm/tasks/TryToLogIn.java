package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.SubmitTheLoginForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class TryToLogIn implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SubmitTheLoginForm.withEmptyFields());
    }

    public static TryToLogIn withoutCredentials() {
        return Tasks.instrumented(TryToLogIn.class);
    }
}
