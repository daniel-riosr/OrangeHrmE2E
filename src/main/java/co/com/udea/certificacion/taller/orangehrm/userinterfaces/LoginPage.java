package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target USERNAME_INPUT = Target.the("username input")
            .located(By.xpath("//input[@name='username']"));
    public static final Target PASSWORD_INPUT = Target.the("password input")
            .located(By.name("password"));
    public static final Target LOGIN_BUTTON = Target.the("login button")
            .located(By.xpath("//button[@type='submit']"));
    public static final Target INVALID_CREDENTIALS_ALERT = Target.the("invalid credentials alert")
            .located(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
    public static final Target REQUIRED_FIELD_MESSAGE = Target.the("required field message")
            .located(By.xpath("//span[contains(@class,'oxd-input-field-error-message')]"));

    private LoginPage() {
    }
}
