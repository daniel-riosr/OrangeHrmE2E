package co.com.udea.certificacion.taller.orangehrm.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;

public class OpenTheBrowser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().thePageNamed("pages.orangeHrmUrl"));
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static OpenTheBrowser onTheLoginPage() {
        return Tasks.instrumented(OpenTheBrowser.class);
    }
}