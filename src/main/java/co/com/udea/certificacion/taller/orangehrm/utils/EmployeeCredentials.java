package co.com.udea.certificacion.taller.orangehrm.utils;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import co.com.udea.certificacion.taller.orangehrm.models.User;

public final class EmployeeCredentials {

    private EmployeeCredentials() {
    }

    public static User of(Employee employee) {
        return withPassword(employee, employee.getPassword());
    }

    public static User withPassword(Employee employee, String password) {
        return new User(employee.getUsername(), password);
    }
}
