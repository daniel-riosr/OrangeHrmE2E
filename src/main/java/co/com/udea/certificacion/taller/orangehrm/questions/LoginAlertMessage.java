package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.INVALID_CREDENTIALS_ALERT;

public class LoginAlertMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return INVALID_CREDENTIALS_ALERT.resolveFor(actor).waitUntilVisible().getText();
    }

    public static LoginAlertMessage text() {
        return new LoginAlertMessage();
    }
}
