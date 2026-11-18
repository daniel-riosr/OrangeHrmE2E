package co.com.udea.certificacion.taller.orangehrm.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.udea.certificacion.taller.orangehrm.userinterfaces.EmployeeDetailsPage.EMPLOYEE_FULL_NAME;

public class EmployeeCreation implements Question<String> {

    private static final int MAX_ATTEMPTS = 20;
    private static final long RETRY_MILLISECONDS = 500;

    @Override
    public String answeredBy(Actor actor) {
        String fullName = "";
        for (int attempt = 0; attempt < MAX_ATTEMPTS && fullName.isBlank(); attempt++) {
            fullName = EMPLOYEE_FULL_NAME.resolveFor(actor).waitUntilVisible().getText().trim();
            if (fullName.isBlank()) {
                pause();
            }
        }
        return fullName;
    }

    private void pause() {
        try {
            Thread.sleep(RETRY_MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static EmployeeCreation fullNameDisplayed() {
        return new EmployeeCreation();
    }
}
