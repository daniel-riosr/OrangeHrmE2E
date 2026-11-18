package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class FormPage {

    public static final Target FIELD_ERROR_MESSAGE = Target.the("field error message")
            .located(By.cssSelector(".oxd-input-field-error-message"));

    private FormPage() {
    }
}
