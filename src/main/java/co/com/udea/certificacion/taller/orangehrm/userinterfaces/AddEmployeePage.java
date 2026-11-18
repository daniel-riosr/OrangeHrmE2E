package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddEmployeePage {

    public static final Target FIRST_NAME_INPUT = Target.the("first name input")
            .located(By.name("firstName"));
    public static final Target MIDDLE_NAME_INPUT = Target.the("middle name input")
            .located(By.name("middleName"));
    public static final Target LAST_NAME_INPUT = Target.the("last name input")
            .located(By.name("lastName"));
    public static final Target CREATE_LOGIN_TOGGLE = Target.the("create login details toggle")
            .located(By.xpath("//span[contains(@class,'oxd-switch-input')]"));
    public static final Target USERNAME_INPUT = Target.the("username input")
            .located(By.xpath("//label[normalize-space()='Username']"
                    + "/ancestor::div[contains(@class,'oxd-input-group')]//input"));
    public static final Target PASSWORD_INPUT = Target.the("password input")
            .located(By.xpath("//label[normalize-space()='Password']"
                    + "/ancestor::div[contains(@class,'oxd-input-group')]//input"));
    public static final Target CONFIRM_PASSWORD_INPUT = Target.the("confirm password input")
            .located(By.xpath("//label[normalize-space()='Confirm Password']"
                    + "/ancestor::div[contains(@class,'oxd-input-group')]//input"));
    public static final Target SAVE_BUTTON = Target.the("save button")
            .located(By.xpath("//button[@type='submit']"));

    private AddEmployeePage() {
    }
}
