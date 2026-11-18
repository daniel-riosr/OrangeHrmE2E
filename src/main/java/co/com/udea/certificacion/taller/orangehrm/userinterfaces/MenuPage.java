package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MenuPage {

    public static final Target PIM_MENU = Target.the("PIM menu option")
            .located(By.xpath("//span[normalize-space()='PIM']"));
    public static final Target ADD_EMPLOYEE_TAB = Target.the("Add Employee tab")
            .located(By.xpath("//a[normalize-space()='Add Employee']"));

    private MenuPage() {
    }
}
