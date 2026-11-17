package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.DashboardPage.DASHBOARD_TITLE;

public class DashboardValidation implements Question<Boolean> {

    private static final String EXPECTED_TITLE = "Dashboard";

    @Override
    public Boolean answeredBy(Actor actor) {
        String titleFromPage = DASHBOARD_TITLE.resolveFor(actor).waitUntilVisible().getText();
        return titleFromPage.contains(EXPECTED_TITLE);
    }

    public static DashboardValidation isDisplayed() {
        return new DashboardValidation();
    }
}