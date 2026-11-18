package co.com.udea.certificacion.taller.orangehrm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Employee {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String employeeId;
    private final String username;
    private final String password;
}
