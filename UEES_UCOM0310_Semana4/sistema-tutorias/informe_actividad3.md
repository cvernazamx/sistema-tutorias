# UNIVERSIDAD ESPÍRITU SANTO
**Facultad de Ingenierías y Tecnologías de la Información**  
**Carrera:** Ingeniería de Software | **Modalidad:** En Línea  
**Asignatura:** Diseño de Software (UCOM0310) | **Período:** PEL 4 - 2026  
**Actividad:** Actividad 3 | Laboratorio Formativo: Strategy + Observer  
**Estudiante:** Christian Fernando Vernaza Vernaza | **Fecha:** 7 de septiembre de 2026  

## 1. Resumen Ejecutivo del Laboratorio
El presente laboratorio implementa de manera práctica y desacoplada los patrones de comportamiento Strategy y Observer en el subsistema de cancelaciones y notificaciones de tutorías académicas con Java 21 y Maven. Mediante Strategy se aislaron las reglas de negocio variables para la autorización de cancelaciones horarias (Normal, Prioritaria, Grupal y Emergencia), cumpliendo con OCP y DIP. Mediante Observer se configuró Reserva como Sujeto emisor para notificar a Correo, Calendario, Panel de Estudiante y Panel de Docente sin acoplar el dominio.

## 2. Respuestas a las Preguntas de Análisis

| Pregunta | Respuesta / Justificación Técnica |
| :--- | :--- |
| **1. ¿Qué parte es estable y qué parte es variable en Strategy?** | La parte estable es el flujo transaccional en ServicioCancelacion y Reserva. La parte variable es la regla algorítmica específica que valida el tiempo mínimo de anticipación. |
| **2. ¿Qué tendría que cambiar para agregar CancelacionGrupal?** | Únicamente crear la clase CancelacionGrupal implementando PoliticaCancelacion, sin tocar ServicioCancelacion ni Reserva (cumpliendo OCP). |
| **3. ¿Quién es Subject y quiénes son Observers?** | El Subject es la clase Reserva. Los Observers son EmailObserver, CalendarioObserver, PanelEstudianteObserver y PanelDocenteObserver. |
| **4. ¿Qué problema habría si un Observer lanza una excepción?** | Si no se controla con try-catch, aborta el bucle de notificación dejando a los observadores restantes sin enterarse y deteniendo la transacción. |
| **5. ¿Por qué Strategy y Observer son de comportamiento pero no resuelven lo mismo?** | Strategy desacopla algoritmos intercambiables (1 a 1). Observer propaga eventos de cambio de estado a múltiples oyentes (1 a muchos). |

## 3. Registro de Compilación Maven
```text
[INFO] Scanning for projects...
[INFO] Building actividad3-strategy-observer 1.0-SNAPSHOT
[INFO] BUILD SUCCESS
[INFO] Total time: 0.408 s
cd ~/Desktop/UEES_UCOM0310_Semana4/sistema-tutorias/semana-04/actividad3-strategy-observer
cat << 'EOF' > entregable_actividad3.html
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<style>
  body { font-family: Arial, sans-serif; line-height: 1.4; color: #000; font-size: 11pt; }
  h1 { font-size: 16pt; margin-bottom: 4px; }
  h2 { font-size: 13pt; margin-top: 18px; margin-bottom: 6px; border-bottom: 1px solid #000; padding-bottom: 3px; }
  h3 { font-size: 11pt; margin-top: 12px; margin-bottom: 4px; }
  p, li { font-size: 10.5pt; }
  table { width: 100%; border-collapse: collapse; margin-top: 10px; margin-bottom: 15px; font-size: 10pt; }
  th, td { border: 1px solid #000; padding: 6px 8px; text-align: left; }
  th { background-color: #f0f0f0; }
  pre { border: 1px solid #000; padding: 8px; font-family: Courier, monospace; font-size: 9.5pt; background-color: #fafafa; }
</style>
</head>
<body>

<h1>UNIVERSIDAD ESPÍRITU SANTO</h1>
<p><strong>Facultad de Ingenierías y Tecnologías de la Información</strong><br>
<strong>Carrera:</strong> Ingeniería de Software | <strong>Modalidad:</strong> En Línea<br>
<strong>Asignatura:</strong> Diseño de Software (UCOM0310) | <strong>Período:</strong> PEL 4 - 2026<br>
<strong>Actividad:</strong> Actividad 3 | Laboratorio Formativo: Strategy + Observer<br>
<strong>Estudiante:</strong> Christian Fernando Vernaza Vernaza | <strong>Fecha:</strong> 7 de septiembre de 2026</p>

<h2>1. Resumen Ejecutivo del Laboratorio</h2>
<p>El presente laboratorio implementa de manera práctica y desacoplada los patrones de comportamiento <strong>Strategy</strong> y <strong>Observer</strong> en el contexto del subsistema de cancelaciones y notificaciones de tutorías académicas empleando Java 21 y Maven. Mediante <em>Strategy</em> se aislaron las reglas de negocio variables para la validación horaria de cancelaciones (Normal, Prioritaria, Grupal y Emergencia), cumpliendo los principios OCP y DIP. Mediante <em>Observer</em> se configuró la clase <code>Reserva</code> como Sujeto emisor, permitiendo que múltiples oyentes (Email, Calendario, Panel de Estudiante y Panel de Docente) reaccionen dinámicamente a cambios de estado sin incrustar dependencias directas en la entidad de negocio.</p>

<h2>2. Estructura del Proyecto Maven</h2>
<pre>
actividad3-strategy-observer/
├── pom.xml
├── strategy.puml
├── observer.puml
└── src/
    └── main/
        └── java/
            └── edu/
                └── uees/
                    └── disenosoftware/
                        ├── domain/
                        │   ├── EstadoReserva.java
                        │   └── Reserva.java
                        ├── strategy/
                        │   ├── PoliticaCancelacion.java
                        │   ├── CancelacionNormal.java
                        │   ├── CancelacionPrioritaria.java
                        │   ├── CancelacionGrupal.java          (Reto)
                        │   ├── CancelacionEmergencia.java      (Reto)
                        │   └── ServicioCancelacion.java
                        ├── observer/
                        │   ├── ReservaObserver.java
                        │   ├── EmailObserver.java
                        │   ├── CalendarioObserver.java
                        │   ├── PanelEstudianteObserver.java   (Reto)
                        │   └── PanelDocenteObserver.java      (Reto)
                        └── app/
                            └── Main.java
</pre>

<h2>3. Implementación del Código Fuente</h2>

<h3>3.1 Capa de Dominio (domain)</h3>
<pre>
// EstadoReserva.java
package edu.uees.disenosoftware.domain;

public enum EstadoReserva {
    PENDIENTE, CONFIRMADA, CANCELADA
}

// Reserva.java (Subject)
package edu.uees.disenosoftware.domain;

import edu.uees.disenosoftware.observer.ReservaObserver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final String id;
    private final LocalDateTime fechaHora;
    private EstadoReserva estado = EstadoReserva.PENDIENTE;
    private final List&lt;ReservaObserver&gt; observers = new ArrayList&lt;&gt;();

    public Reserva(String id, LocalDateTime fechaHora) {
        this.id = id;
        this.fechaHora = fechaHora;
    }

    public void agregarObserver(ReservaObserver observer) { observers.add(observer); }
    public void eliminarObserver(ReservaObserver observer) { observers.remove(observer); }

    private void notificar() {
        for (ReservaObserver o : observers) { o.actualizar(this); }
    }

    public void confirmar() { estado = EstadoReserva.CONFIRMADA; notificar(); }
    public void cancelar() { estado = EstadoReserva.CANCELADA; notificar(); }

    public long horasRestantes() { return Duration.between(LocalDateTime.now(), fechaHora).toHours(); }
    public long minutosRestantes() { return Duration.between(LocalDateTime.now(), fechaHora).toMinutes(); }

    public String getId() { return id; }
    public EstadoReserva getEstado() { return estado; }
}
</pre>

<h3>3.2 Capa Strategy (strategy)</h3>
<pre>
// PoliticaCancelacion.java
package edu.uees.disenosoftware.strategy;
import edu.uees.disenosoftware.domain.Reserva;

public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva);
}

// CancelacionNormal.java
package edu.uees.disenosoftware.strategy;
import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionNormal implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) { return reserva.horasRestantes() &gt;= 2; }
}

// CancelacionGrupal.java (Reto)
package edu.uees.disenosoftware.strategy;
import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionGrupal implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) { return reserva.horasRestantes() &gt;= 24; }
}

// CancelacionEmergencia.java (Reto)
package edu.uees.disenosoftware.strategy;
import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionEmergencia implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) { return reserva.minutosRestantes() &gt;= 15; }
}

// ServicioCancelacion.java (Context)
package edu.uees.disenosoftware.strategy;
import edu.uees.disenosoftware.domain.Reserva;

public class ServicioCancelacion {
    private PoliticaCancelacion politica;

    public ServicioCancelacion(PoliticaCancelacion politica) { this.politica = politica; }
    public void cambiarPolitica(PoliticaCancelacion politica) { this.politica = politica; }

    public void cancelar(Reserva reserva) {
        if (!politica.puedeCancelar(reserva)) {
            throw new IllegalStateException("La política actual no autoriza la cancelación.");
        }
        reserva.cancelar();
    }
}
</pre>

<h3>3.3 Capa Observer (observer)</h3>
<pre>
// ReservaObserver.java
package edu.uees.disenosoftware.observer;
import edu.uees.disenosoftware.domain.Reserva;

public interface ReservaObserver {
    void actualizar(Reserva reserva);
}

// EmailObserver.java
package edu.uees.disenosoftware.observer;
import edu.uees.disenosoftware.domain.Reserva;

public class EmailObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[EmailObserver] Estado cambiado a: " + reserva.getEstado());
    }
}

// PanelEstudianteObserver.java (Reto)
package edu.uees.disenosoftware.observer;
import edu.uees.disenosoftware.domain.Reserva;

public class PanelEstudianteObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[PanelEstudiante] Panel de estudiante actualizado: " + reserva.getEstado());
    }
}
</pre>

<h2>4. Respuestas a las Preguntas de Análisis</h2>
<table>
  <tr>
    <th style="width: 35%;">Pregunta</th>
    <th>Respuesta Técnica</th>
  </tr>
  <tr>
    <td><strong>1. ¿Qué parte es estable y qué parte es variable en Strategy?</strong></td>
    <td>La parte estable es la ejecución transaccional coordinada por <code>ServicioCancelacion</code> y el estado de <code>Reserva</code>. La parte variable es el criterio algorítmico específico que evalúa el umbral horario de anticipación.</td>
  </tr>
  <tr>
    <td><strong>2. ¿Qué tendría que cambiar para agregar CancelacionGrupal?</strong></td>
    <td>Únicamente se crea la nueva clase concreta que implementa <code>PoliticaCancelacion</code>. No se modifica ninguna clase existente, cumpliendo estrictamente con el principio OCP.</td>
  </tr>
  <tr>
    <td><strong>3. ¿Quién es Subject y quiénes son Observers?</strong></td>
    <td>El Subject es <code>Reserva</code> (quien emite la notificación). Los Observers son <code>EmailObserver</code>, <code>CalendarioObserver</code>, <code>PanelEstudianteObserver</code> y <code>PanelDocenteObserver</code>.</td>
  </tr>
  <tr>
    <td><strong>4. ¿Qué problema habría si un Observer lanza una excepción?</strong></td>
    <td>Sin bloques de captura <code>try-catch</code>, la excepción interrumpirá el bucle de notificación, impidiendo que los observadores posteriores reciban el evento y dejando la transacción en un estado inconsistente.</td>
  </tr>
  <tr>
    <td><strong>5. ¿Por qué Strategy y Observer son de comportamiento pero no resuelven lo mismo?</strong></td>
    <td>Strategy resuelve la <em>variabilidad de algoritmos</em> reemplazables en un contexto (relación 1 a 1). Observer resuelve la <em>difusión de eventos desacoplada</em> a múltiples componentes interesados (relación 1 a muchos).</td>
  </tr>
</table>

<h2>5. Registro de Compilación Maven</h2>
<pre>
[INFO] Scanning for projects...
[INFO] Building actividad3-strategy-observer 1.0-SNAPSHOT
[INFO] Compiling 14 source files with javac [debug target 21] to target/classes
[INFO] BUILD SUCCESS
[INFO] Total time:  0.408 s
[INFO] Finished at: 2026-09-07T21:48:42-05:00
</pre>

</body>
</html>
