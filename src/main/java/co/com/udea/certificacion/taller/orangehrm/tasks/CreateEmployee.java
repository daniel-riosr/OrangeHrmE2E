package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.FillEmployeeForm;
import co.com.udea.certificacion.taller.orangehrm.interactions.FillLoginDetails;
import co.com.udea.certificacion.taller.orangehrm.interactions.SaveTheEmployee;
import co.com.udea.certificacion.taller.orangehrm.interactions.WaitForTheProfileOf;
import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class CreateEmployee implements Task {

    private final Employee employee;

    public CreateEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                FillEmployeeForm.with(employee),
                FillLoginDetails.with(employee),
                SaveTheEmployee.information(),
                WaitForTheProfileOf.employee(employee));
    }

    public static CreateEmployee withTheInformationOf(Employee employee) {
        return Tasks.instrumented(CreateEmployee.class, employee);
    }
}
