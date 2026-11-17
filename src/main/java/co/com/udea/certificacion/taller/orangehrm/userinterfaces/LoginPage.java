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

    private LoginPage() {
    }
}