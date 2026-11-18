package co.com.udea.certificacion.taller.orangehrm.utils;

import co.com.udea.certificacion.taller.orangehrm.models.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class UniqueData {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("MMddHHmmss");
    private static final int EMPLOYEE_ID_MAX_LENGTH = 10;

    private UniqueData() {
    }

    public static String withTimestamp(String baseValue) {
        return baseValue + timestamp();
    }

    public static String employeeId() {
        String timestamp = timestamp();
        return timestamp.substring(Math.max(0, timestamp.length() - EMPLOYEE_ID_MAX_LENGTH));
    }

    public static Employee makeUnique(Employee employee) {
        return new Employee(employee.getFirstName(), employee.getMiddleName(),
                withTimestamp(employee.getLastName()), employee.getEmployeeId(),
                withTimestamp(employee.getUsername()), employee.getPassword());
    }

    private static String timestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}
