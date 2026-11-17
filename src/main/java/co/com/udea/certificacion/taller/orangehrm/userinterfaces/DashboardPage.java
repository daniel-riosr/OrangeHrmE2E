package co.com.udea.certificacion.taller.orangehrm.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {

    public static final Target DASHBOARD_TITLE = Target.the("dashboard title")
            .located(By.cssSelector(".oxd-topbar-header-breadcrumb h6"));

    private DashboardPage() {
    }
}