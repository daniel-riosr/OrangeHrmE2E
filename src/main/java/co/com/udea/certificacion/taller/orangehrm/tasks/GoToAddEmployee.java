package co.com.udea.certificacion.taller.orangehrm.tasks;

import co.com.udea.certificacion.taller.orangehrm.interactions.OpenTheAddEmployeeForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class GoToAddEmployee implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(OpenTheAddEmployeeForm.fromThePimMenu());
    }

    public static GoToAddEmployee form() {
        return Tasks.instrumented(GoToAddEmployee.class);
    }
}
