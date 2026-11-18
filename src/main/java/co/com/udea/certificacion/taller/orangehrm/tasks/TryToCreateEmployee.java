package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.FillEmployeeForm;
import co.com.udea.certificacion.taller.orangehrm.interactions.FillLoginDetails;
import co.com.udea.certificacion.taller.orangehrm.interactions.SaveTheEmployee;
import co.com.udea.certificacion.taller.orangehrm.models.Employee;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Camino de excepcion: llena el formulario y guarda, pero NO espera la pagina de detalles
 * (a diferencia de CreateEmployee), porque el sistema debe rechazar los datos.
 */
public class TryToCreateEmployee implements Task {

    private final Employee employee;

    public TryToCreateEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                FillEmployeeForm.with(employee),
                FillLoginDetails.with(employee),
                SaveTheEmployee.information());
    }

    public static TryToCreateEmployee withTheInformationOf(Employee employee) {
        return Tasks.instrumented(TryToCreateEmployee.class, employee);
    }
}
