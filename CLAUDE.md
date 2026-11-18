# CLAUDE.md — Taller de Automatización E2E (OrangeHRM + Serenity Screenplay)

## Contexto
- Proyecto académico del curso **Calidad de Software (UdeA)**. Taller de automatización E2E.
- Sitio bajo prueba: **OrangeHRM demo** → https://opensource-demo.orangehrmlive.com/ (usuario `Admin`, clave `admin123`).
- El profesor **exige Java + patrón Screenplay (Serenity BDD + Cucumber)**. No se aceptan otras herramientas (Cypress, Playwright, etc.).
- La **socialización vale 55%** de la nota: el equipo debe **entender cada línea**. Por eso:
  - **Responde siempre en español.**
  - Trabaja **un paso a la vez**. Antes de escribir código, explica brevemente el plan y espera confirmación.
  - Después de cada cambio, explica **qué hace cada clase nueva y por qué está en esa capa**, con palabras sencillas.
  - No hagas cambios grandes ni "de una vez" en muchos archivos sin explicarlos.

## Stack (NO cambiar versiones sin preguntar)
- Java **17** (Temurin) — Lombok 1.18.22 del scaffold no funciona con Java 21.
- Gradle Wrapper **8.14.x** — no subir a Gradle 9.
- Serenity **4.1.0** (librerías de prueba, en `ext` de `build.gradle`).
- Plugin de reporte `net.serenity-bdd.serenity-gradle-plugin` **4.2.6** (la 4.1.0 no existe en el portal y la 4.1.6 está dañada: `ClearHistoryTask` sin constructor).
- Scaffold del profesor: `io.github.jumosqu12.screenplayarchitecture` 2.0.1.
- Cucumber + JUnit 4 (`@RunWith(CucumberWithSerenity.class)`).

## Comandos
- Ejecutar todo y generar reporte: `.\gradlew clean test` (Windows/PowerShell).
- Un solo runner: `.\gradlew clean test --tests "*LoginRunner"`.
- Reporte: `target/site/serenity/index.html`.
- Generar feature con el scaffold: `.\gradlew gft --name=<nombre> --nameSubFolder=<carpeta>`.
- Generar runner con el scaffold: `.\gradlew grun --name=<NombreRunner> --folderName=<carpeta>` (usar `grun`; el `glue` que genera sí es correcto).
- Después de cada paso que funcione: hacer commit con un mensaje claro en español.

## Paquete base
`co.com.udea.certificacion.taller.orangehrm`

## Arquitectura Screenplay (capas y responsabilidades)
| Capa | Ubicación | Regla |
|---|---|---|
| Features | `src/test/resources/features/<carpeta>/*.feature` | Gherkin **en inglés** (el profe lo pidió: evitar tildes/ñ). Historia de usuario `As a / I need / To`. Usar `Background` para el Given común. Datos en DataTable o `Scenario Outline` + `Examples`. |
| Runners | `src/test/java/.../runners/<carpeta>/` | `features` = ruta exacta del feature; `glue` = `co.com.udea.certificacion.taller.orangehrm.stepdefinitions`. |
| StepDefinitions | `src/test/java/.../stepdefinitions/` | Solo traduce Gherkin → actor. Sin lógica ni localizadores. Actor `Actor.named("Laura")` + `@Managed(driver = "chrome") WebDriver herBrowser` (SIN `uniqueSession = true`, para que cada escenario tenga navegador limpio → FIRST "I"). `@Before` → `administrator.can(BrowseTheWeb.with(herBrowser))`. Given → `wasAbleTo`, When → `attemptsTo`, Then → `should(seeThat(...))`. Usar `@DataTableType` para convertir tablas en Models. |
| Tasks | `src/main/java/.../tasks/` | Acciones de **negocio** (qué se hace). `implements Task`. Método estático de lectura natural que retorna `Tasks.instrumented(Clase.class, args...)`. Llaman a Interactions. |
| Interactions | `src/main/java/.../interactions/` | Acciones sobre la **pantalla** (cómo: escribir, clic, seleccionar). `implements Interaction`. Reciben Models por constructor. |
| Questions | `src/main/java/.../questions/` | Verificaciones. `implements Question<Boolean>` o `Question<String>`. Método estático que retorna `new Clase()` (NO `instrumented`). Usar `TARGET.resolveFor(actor).waitUntilVisible().getText()`. El profe pidió hacerlas de **varias maneras**: Boolean con `seeThat(q)` y String con `seeThat(q, equalTo(...))`. |
| UserInterfaces | `src/main/java/.../userinterfaces/` | Solo mapeo de elementos: `public static final Target NOMBRE = Target.the("descripción legible").located(By....)`. Constantes en MAYÚSCULAS. Constructor privado. Mostrar variedad de localizadores (XPath, name, CSS). |
| Models | `src/main/java/.../models/` | Datos de prueba. Lombok `@Getter @AllArgsConstructor` (aprobado por el profe). |
| Utils | `src/main/java/.../utils/` | `WaitLittleBitTime.waitLittleBitTime()` lee `demo.pause.milliseconds` de `serenity.conf` (2000 para exponer, 0 para trabajar). Llamarla entre interacciones. Clases utilitarias `final` con constructor privado. |

## Reglas de calidad (el profe las evalúa)
- **Nada "quemado"**: textos esperados y nombres de propiedades en constantes; datos de prueba en el feature; URL en `serenity.conf` (`pages.orangeHrmUrl`).
- **Responsabilidad única** y nombres en inglés que se lean como frase (`LogIn.withTheCredentialsOf(user)`).
- Las Tasks hablan de negocio; las Interactions de la pantalla. No mezclar.
- Nada de `Thread.sleep` fuera de `WaitLittleBitTime`. Para estabilidad usar esperas de Serenity (`waitUntilVisible`, `WaitUntil`) — `webdriver.wait.for.timeout = 15000` ya está en `serenity.conf`.
- **FIRST**: escenarios independientes (cada uno inicia desde el login por `Background`), repetibles (datos únicos con timestamp; limpiar lo que se crea), autovalidables (todo escenario termina en un `Then` con Question).
- Borrar las clases "semilla" del scaffold cuando se reemplazan.
- Si un localizador falla, NO adivinar: pedir al usuario que lo verifique con el inspector de Chrome (`Ctrl+F` en Elements debe dar "1 of 1") y que pegue el HTML del elemento. OrangeHRM es una SPA (Vue): el HTML no se puede obtener con una petición simple.

## Ya implementado
- `serenity.conf` (Chrome, `wait.for.timeout`, `take.screenshots = FOR_EACH_ACTION`, `pages.orangeHrmUrl`, `demo.pause.milliseconds`).
- Smoke: `features/smoke/open_orangehrm.feature`, `OrangeHrmRunner`, Task `OpenTheBrowser`.
- Login exitoso: `features/authentication/login.feature`, `LoginRunner`, `OrangeHrmStepDefinition`, Model `User`, Task `LogIn`, Interaction `Authenticate`, UserInterfaces `LoginPage` y `DashboardPage`, Question `DashboardValidation`, Util `WaitLittleBitTime`.
- Reporte Serenity funcionando. Verificar que `build.gradle` tenga al final:
  ```groovy
  tasks.named('aggregate') { outputs.upToDateWhen { false } }
  ```

## Pendiente (en este orden)
8. **Errores del login** con `Scenario Outline` + `Examples`: clave incorrecta y usuario inexistente → "Invalid credentials"; campos vacíos → "Required". Question que retorna `String` validada con `equalTo` (la "otra manera").
9. **E2E parte 1**: menú PIM → Add Employee → Model `Employee` (nombre único con timestamp) → Save → verificar el perfil del empleado.
10. **E2E parte 2**: Employee List → buscar el empleado creado → verificar que aparece en resultados.
11. **Error del E2E**: guardar empleado sin First Name → "Required".
12. **Limpieza**: eliminar el empleado creado y hacer logout (FIRST "R").
13. **Runner general** de todos los features + reporte final con `.\gradlew clean test`.
14. **Pulir**: semillas sobrantes, nombres, constantes, README del repositorio.
15. **Tabla FIRST** del proyecto para la exposición.
16. Apoyo para preparar la socialización (guion, preguntas probables).
