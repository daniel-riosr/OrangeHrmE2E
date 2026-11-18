package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EmployeeDetailsPage {

    public static final Target EMPLOYEE_FULL_NAME = Target.the("employee full name header")
            .located(By.cssSelector(".orangehrm-edit-employee-name h6"));

    private EmployeeDetailsPage() {
    }
}
