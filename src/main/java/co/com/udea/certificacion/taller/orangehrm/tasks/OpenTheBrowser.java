package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.LoadTheLoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class OpenTheBrowser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(LoadTheLoginPage.ofOrangeHrm());
    }

    public static OpenTheBrowser onTheLoginPage() {
        return Tasks.instrumented(OpenTheBrowser.class);
    }
}
