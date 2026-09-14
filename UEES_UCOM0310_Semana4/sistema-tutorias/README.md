# Sistema de Gestion de Tutorias - UEES
- Asignatura: Diseno de Software (UCOM0310) | PEL 4 - 2026
- Actividad: Actividad 5 | Ae3 - Incremento 1 del Proyecto
- Estudiante: Christian Fernando Vernaza Vernaza

---

## 1. Proposito del Proyecto
Proveer una solucion desacoplada, extensible y mantenible para la gestion de tutorias universitarias virtuales, facilitando la reserva, integracion con salas de videoconferencia, sincronizacion de calendarios y alertas a estudiantes y docentes.

## 2. Alcance del Incremento 1 (Ae3)
Evolucion de la arquitectura base orientada a objetos mediante la integracion de dos patrones estructurales justificados por necesidades reales:
1. Patron Adapter: Aislamiento e integracion de proveedores externos de videoconferencia (Zoom y Microsoft Teams) con contratos incompatibles.
2. Patron Facade: Simplificacion del flujo de creacion de tutorias, abstrayendo la orquestacion de cuatro subsistemas independientes en una sola llamada de alto nivel.

## 3. Patrones de Diseno Utilizados

### Patron Adapter
- Problema real: Las APIs externas (Zoom / Teams) exponen nombres de metodos y parametros incompatibles con el dominio interno (generarMeeting vs crearSala).
- Justificacion: Permite conectar bibliotecas de terceros sin acoplar las entidades del sistema a proveedores especificos.
- SOLID: DIP (el cliente depende de la abstraccion Videoconferencia) y OCP (se agregan nuevos proveedores creando nuevos adaptadores).

### Patron Facade
- Problema real: El cliente debia conocer y coordinar secuencialmente reservas, videoconferencia, calendario y notificaciones.
- Justificacion: Reduce el acoplamiento y la fragilidad del cliente, centralizando la secuencia en TutoriasFacade.
- SOLID: SRP (cada servicio mantiene su responsabilidad unica) y Ley de Demeter (el cliente solo interactua con la fachada).

## 4. Estructura de Paquetes
- edu.uees.disenosoftware.domain: Entidades de negocio (Estudiante, Docente, Reserva).
- edu.uees.disenosoftware.adapter: Interfaz Target (Videoconferencia), Adaptees y Adaptadores (Zoom, Teams).
- edu.uees.disenosoftware.facade: Subsistemas y orquestador (TutoriasFacade, Notificador, Calendario, Reservas).
- edu.uees.disenosoftware.app: Punto de entrada y prueba de integracion (Main).

## 5. Compilacion y Ejecucion
Para compilar y verificar el proyecto con Maven:
mvn clean compile

## 6. Diagrama UML
El modelado completo del incremento se encuentra en docs/uml-incremento1.puml.

## 7. Declaracion de Uso de IA
Se utilizo asistencia de IA como herramienta de apoyo para la estructuracion del documento tecnico y validacion de sintaxis PlantUML, bajo revision y verificacion conceptual del estudiante.
