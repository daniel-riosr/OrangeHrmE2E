package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.FillEmployeeForm;
import co.com.udea.certificacion.taller.orangehrm.interactions.FillLoginDetails;
import co.com.udea.certificacion.taller.orangehrm.interactions.OpenTheAddEmployeeForm;
import co.com.udea.certificacion.taller.orangehrm.interactions.SaveTheEmployee;
import co.com.udea.certificacion.taller.orangehrm.interactions.WaitForTheProfileOf;
import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RegisterEmployee implements Task {

    private final Employee employee;

    public RegisterEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenTheAddEmployeeForm.fromThePimMenu(),
                FillEmployeeForm.with(employee),
                FillLoginDetails.with(employee),
                SaveTheEmployee.information(),
                WaitForTheProfileOf.employee(employee));
    }

    public static RegisterEmployee withTheInformationOf(Employee employee) {
        return Tasks.instrumented(RegisterEmployee.class, employee);
    }
}
