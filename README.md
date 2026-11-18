# OrangeHRM E2E: taller de automatización

Proyecto del curso **Calidad de Software (UdeA)**. Automatiza pruebas de extremo a extremo (E2E) sobre el demo público de [OrangeHRM](https://opensource-demo.orangehrmlive.com/) con **Java + Serenity BDD + Cucumber**, usando el patrón **Screenplay**.

Las pruebas cubren el login, el registro de empleados y un flujo completo: la administradora registra un empleado con acceso al sistema y ese empleado inicia sesión.

## Requisitos

- **JDK 17** (Temurin). Con Java 21 no compila: la versión de Lombok del proyecto no lo soporta.
- **Google Chrome** instalado. Serenity descarga el driver solo.
- Internet, para entrar al demo de OrangeHRM.
- No hace falta instalar Gradle: el proyecto trae el *wrapper* (`gradlew`).

## Cómo ejecutarlo

En PowerShell, desde la carpeta del proyecto:

```powershell
# Todas las pruebas y el reporte
.\gradlew clean test

# Un solo runner
.\gradlew clean test --tests "*LoginRunner"
.\gradlew clean test --tests "*EmployeeManagementRunner"
.\gradlew clean test --tests "*EmployeeOnboardingRunner"
```

En Mac o Linux se usa `./gradlew` en lugar de `.\gradlew`.

El **reporte** queda en `target/site/serenity/index.html`. Muestra cada escenario, cada paso y una captura de pantalla por acción.

### Pausa para la demostración

En `src/test/resources/serenity.conf`:

```hocon
demo {
    pause.milliseconds = 2000
}
```

- **2000**: el navegador hace una pausa de 2 s entre acciones, para que el público alcance a ver qué pasa. Es el valor para exponer.
- **0**: sin pausas; la suite completa tarda unos 3 minutos. Es el valor para trabajar.

## Estructura (capas de Screenplay)

| Capa | Carpeta | En una frase |
|---|---|---|
| Features | `src/test/resources/features/` | Los escenarios escritos en Gherkin (inglés), como historias de usuario. |
| Runners | `src/test/java/.../runners/` | Le dicen a Cucumber qué feature ejecutar; hay uno por feature. |
| StepDefinitions | `src/test/java/.../stepdefinitions/` | Traducen cada paso del Gherkin a una acción o pregunta del actor, sin lógica. |
| Tasks | `src/main/java/.../tasks/` | Lo que el actor hace en lenguaje de negocio ("registrar un empleado"); solo llaman Interactions. |
| Interactions | `src/main/java/.../interactions/` | Cómo se hace en la pantalla: clics, escritura, esperas y pausas. |
| Questions | `src/main/java/.../questions/` | Lo que el actor lee de la pantalla para verificar el resultado. |
| UserInterfaces | `src/main/java/.../userinterfaces/` | Dónde está cada elemento de la página (localizadores XPath, name y CSS). |
| Models | `src/main/java/.../models/` | Los datos de prueba (`User`, `Employee`). |
| Utils | `src/main/java/.../utils/` | Herramientas de apoyo: pausas, tiempo de espera, datos únicos y credenciales. |

Hay dos actores: **Laura**, la administradora, y en el flujo E2E **Carlos**, el empleado nuevo, que usa el mismo navegador.

## Escenarios cubiertos (12)

| Feature | Escenario | Tipo |
|---|---|---|
| `authentication/login.feature` | Login exitoso con credenciales válidas | Feliz |
| `authentication/login.feature` | Clave incorrecta → "Invalid credentials" | Error |
| `authentication/login.feature` | Usuario inexistente → "Invalid credentials" | Error |
| `authentication/login.feature` | Campos vacíos → "Required" | Error |
| `employee/employee_management.feature` | Registro exitoso de un empleado (Sofia Ramirez) | Feliz |
| `employee/employee_management.feature` | Sin First Name → "Required" | Error |
| `employee/employee_management.feature` | Sin Last Name → "Required" | Error |
| `employee/employee_management.feature` | Usuario que ya existe → "Username already exists" | Error |
| `employee/employee_management.feature` | Usuario muy corto → "Should be at least 5 characters" | Error |
| `employee/employee_management.feature` | Clave muy corta → "Should have at least 7 characters" | Error |
| `e2e/employee_onboarding.feature` | Laura registra a Carlos con acceso y Carlos entra al Dashboard | Feliz |
| `e2e/employee_onboarding.feature` | Carlos intenta entrar con una clave incorrecta → "Invalid credentials" | Error |

## Decisiones técnicas importantes

- **Datos únicos.** El demo es público y no se limpia entre ejecuciones. Si siempre creáramos al mismo usuario, la segunda ejecución fallaría con "Username already exists". `UniqueData` le agrega la fecha y hora al apellido y al usuario (por ejemplo `sofiar1118143052`) solo cuando el empleado se registra de verdad. En los escenarios de error los datos se usan tal cual, porque el error depende de ellos (por ejemplo, el usuario `Admin` debe existir).
- **Esperas de Serenity en vez de pausas fijas.** OrangeHRM tarda distinto cada vez. En lugar de "esperar 2 segundos y ojalá alcance", las pruebas esperan **a que pase algo concreto**, por ejemplo que aparezca el campo Username o que el perfil muestre el nombre. Así son estables con pausa 0. Detalle importante de Serenity 4.1.0: `WaitUntil` se escribe `.forNoMoreThan(MaximumWaitTime.inSeconds()).seconds()`, porque la forma con `Duration` revisa la condición una sola vez. El tiempo máximo (15 s) se lee de `serenity.conf`.
- **El Employee Id duplicado del demo compartido.** Al abrir el formulario, OrangeHRM propone un Employee Id automático. Mientras nuestra prueba llena el formulario, otra persona en el mundo puede guardar un empleado con ese mismo número, y el nuestro falla con "Employee Id already exists". Por eso `ReplaceTheEmployeeId` borra el número automático, escribe uno único con fecha y hora y verifica que quedó exacto.
- **El plugin del reporte.** Las librerías de prueba son Serenity **4.1.0**, pero el plugin de Gradle que genera el reporte es la **4.2.6**: la 4.1.0 no existe en el portal de plugins y la 4.1.6 está dañada. Además, `build.gradle` obliga a regenerar el reporte en cada ejecución (`aggregate` nunca se considera "al día").
- **Pausa configurable para la demostración.** `WaitLittleBitTime` es el único lugar del proyecto con `Thread.sleep`, y lee la duración de `serenity.conf`. Con 0 no hace nada; con 2000 hace visible cada paso para el público.

## FIRST en este proyecto

| Letra | Principio | Cómo lo cumple el proyecto |
|---|---|---|
| **F** | Fast (rápidas) | Con pausa 0 cada escenario tarda entre 5 y 30 s y la suite completa unos 3 minutos. Las esperas terminan apenas aparece el elemento, en vez de esperar un tiempo fijo. |
| **I** | Independent (independientes) | Cada escenario abre su propio navegador limpio (`@Managed` sin `uniqueSession`) y empieza desde el login con el `Background`. Ninguno depende de otro: el E2E registra a su propio empleado en lugar de usar uno de otro escenario. |
| **R** | Repeatable (repetibles) | Se pueden correr muchas veces sobre el demo compartido: usuario, apellido y Employee Id son únicos (`UniqueData`), y los errores usan un usuario poco común (`sofiaramirezqa`). *Pendiente:* eliminar al final los empleados creados. |
| **S** | Self-validating (autovalidables) | Nadie tiene que mirar la pantalla para saber si pasó: cada `Then` usa una Question y un comparador (`equalTo`, `containsString`, o una Question Boolean como `DashboardValidation`), y el resultado es verde o rojo. |
| **T** | Timely (oportunas) | Las pruebas se escribieron junto con cada funcionalidad y se corrieron antes de cada commit. Además, **cada escenario termina con un `Then` que deja evidencia en el reporte**: el resultado de la verificación y la captura de pantalla de cada acción (`take.screenshots = FOR_EACH_ACTION`). |

## Integrantes

| Nombre | Usuario de GitHub |
|---|---|
|  |  |
|  |  |
|  |  |
