package co.com.udea.certificacion.taller.orangehrm.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public final class  WaitLittleBitTime {

    private static final String PAUSE_PROPERTY = "demo.pause.milliseconds";

    private WaitLittleBitTime() {
    }

    public static void waitLittleBitTime() {
        int milliseconds = EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalInteger(PAUSE_PROPERTY)
                .orElse(0);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
