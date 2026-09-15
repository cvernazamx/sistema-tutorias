# Sistema de Gestión de Tutorías Académicas

## 1. Propósito y Descripción del Problema
Este proyecto implementa el modelo inicial orientado a objetos para el **Sistema de Gestión de Tutorías** de una institución educativa. Resuelve problemáticas comunes como reservas duplicadas, solapamiento de horarios, falta de visibilidad en la disponibilidad docente y ausencia de notificaciones estructuradas.

El objetivo central es desacoplar las reglas del dominio educativo respecto a las herramientas de persistencia y servicios de comunicación técnica.

---

## 2. Clases Principales y Responsabilidades

### Dominio (`edu.uees.tutorias.domain`)
* **`Usuario` (Abstracta):** Abstracción base que encapsula la identidad y contacto compartido (`id`, `nombre`, `email`).
* **`Estudiante`:** Modela al alumno solicitante de tutorías y su información académica.
* **`Docente`:** Modela al tutor académico encargado de definir y publicar franjas de disponibilidad.
* **`HorarioTutoria`:** Controla la ventana de tiempo (fecha, hora inicio, hora fin) y su estado de disponibilidad (`reservar()`, `liberar()`).
* **`Reserva`:** Entidad central que administra el ciclo de vida y transiciones de estado de una tutoría (`confirmar()`, `cancelar()`).
* **`EstadoReserva` (Enum):** Define los estados válidos (`PENDIENTE`, `CONFIRMADA`, `CANCELADA`, `REALIZADA`).
* **`Asignatura`:** Representa la materia académica vinculada a la tutoría.

### Servicios e Infraestructura (`service`, `repository`, `notification`)
* **`ServicioReservas`:** Orquesta el flujo del caso de uso validando disponibilidad, solicitando el guardado y emitiendo avisos.
* **`IReservaRepository`:** Contrato abstracto para aislar las operaciones CRUD y evitar el acoplamiento con una base de datos específica.
* **`INotificador`:** Interfaz que define el contrato de envío de notificaciones.
* **`NotificadorEmail`:** Implementación concreta para despacho de mensajes vía correo electrónico.

---

## 3. Decisiones de Diseño y Principios SOLID

1. **Principio de Responsabilidad Única (SRP):**
   * Cada clase posee una única razón de cambio. `Reserva` únicamente gobierna su ciclo de vida y reglas internas, delegando la persistencia a `IReservaRepository` y la mensajería a `INotificador`.
2. **Principio de Inversión de Dependencias (DIP):**
   * `ServicioReservas` interactúa exclusivamente con interfaces abstractas inyectadas mediante su constructor, impidiendo dependencias directas con motores SQL o clientes de red concretos.
3. **Principio Abierto/Cerrado (OCP):**
   * Es posible incorporar nuevos canales de aviso (ej. WhatsApp, notificaciones push) creando nuevas clases que implementen `INotificador`, sin necesidad de alterar la lógica del servicio.

---

## 4. Diagrama UML de Clases

El diagrama estructural se encuentra ubicado en la carpeta `docs/`:
* **Fuente PlantUML:** [`docs/modelo-clases.puml`](docs/modelo-clases.puml)
* **Imagen renderizada:** [`docs/modelo-clases.png`](docs/modelo-clases.png)

---

## 5. Requisitos y Compilación

### Requisitos
* **Java SDK:** Versión 17 o superior
* **Apache Maven:** Versión 3.8+

### Comando de Compilación
Para compilar el proyecto y verificar que no existan errores de tipos o dependencias:
```bash
mvn clean compile