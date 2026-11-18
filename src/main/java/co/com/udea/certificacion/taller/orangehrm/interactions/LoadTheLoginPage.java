package co.com.udea.certificacion.taller.orangehrm.interactions;

import co.com.udea.certificacion.taller.orangehrm.utils.MaximumWaitTime;
import co.com.udea.certificacion.taller.orangehrm.utils.WaitLittleBitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.USERNAME_INPUT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoadTheLoginPage implements Interaction {

    private static final String ORANGE_HRM_URL_PROPERTY = "pages.orangeHrmUrl";

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().thePageNamed(ORANGE_HRM_URL_PROPERTY),
                WaitUntil.the(USERNAME_INPUT, isVisible()).forNoMoreThan(MaximumWaitTime.inSeconds()).seconds());
        WaitLittleBitTime.waitLittleBitTime();
    }

    public static LoadTheLoginPage ofOrangeHrm() {
        return Tasks.instrumented(LoadTheLoginPage.class);
    }
}
