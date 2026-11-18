package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class UserMenuPage {

    public static final Target USER_DROPDOWN = Target.the("user dropdown")
            .located(By.cssSelector(".oxd-userdropdown-tab"));
    public static final Target LOGOUT_OPTION = Target.the("logout option")
            .located(By.xpath("//a[normalize-space()='Logout']"));

    private UserMenuPage() {
    }
}
