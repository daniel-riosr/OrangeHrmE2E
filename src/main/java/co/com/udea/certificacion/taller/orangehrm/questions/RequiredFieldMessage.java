package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.LoginPage.REQUIRED_FIELD_MESSAGE;

public class RequiredFieldMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return REQUIRED_FIELD_MESSAGE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static RequiredFieldMessage text() {
        return new RequiredFieldMessage();
    }
}
