package co.com.udea.certificacion.taller.orangehrm.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public final class MaximumWaitTime {

    private static final String WAIT_PROPERTY = "webdriver.wait.for.timeout";
    private static final int DEFAULT_MILLISECONDS = 15000;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    private MaximumWaitTime() {
    }

    public static long inSeconds() {
        int milliseconds = EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalInteger(WAIT_PROPERTY)
                .orElse(DEFAULT_MILLISECONDS);
        return milliseconds / MILLISECONDS_PER_SECOND;
    }
}
