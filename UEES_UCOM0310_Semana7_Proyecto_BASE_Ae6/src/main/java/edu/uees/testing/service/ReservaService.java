package edu.uees.testing.service;

import edu.uees.testing.availability.DisponibilidadClient;
import edu.uees.testing.domain.EstadoReserva;
import edu.uees.testing.domain.Reserva;
import edu.uees.testing.notification.Notificador;
import edu.uees.testing.repository.ReservaRepository;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class ReservaService {

    private final DisponibilidadClient disponibilidad;
    private final ReservaRepository repository;
    private final Notificador notificador;

    public ReservaService(
            DisponibilidadClient disponibilidad,
            ReservaRepository repository,
            Notificador notificador) {
        this.disponibilidad = disponibilidad;
        this.repository = repository;
        this.notificador = notificador;
    }

    public boolean puedeCancelar(int horasAnticipacion) {
        return horasAnticipacion >= 2;
    }

    public double calcularTotal(String tipo, double totalBase) {
        if (totalBase < 0) {
            throw new IllegalArgumentException("Total base inválido");
        }

        if ("VIP".equalsIgnoreCase(tipo)) {
            return totalBase * 0.85;
        }

        if ("ESTUDIANTE".equalsIgnoreCase(tipo)) {
            return totalBase * 0.90;
        }

        return totalBase;
    }

    public void confirmar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva obligatoria");
        }

        if (!disponibilidad.estaDisponible(reserva)) {
            throw new IllegalStateException("Horario no disponible");
        }

        reserva.confirmar();
        repository.guardar(reserva);
        notificador.enviarConfirmacion(reserva);
    }
    // ==========================================
    // Laboratorio 2: Pruebas con Stubs y Mocks
    // ==========================================

    @Test
    void reservaDisponibleSeConfirmaGuardaYNotifica() {
        // Arrange: creamos los dobles de prueba con Mockito
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        // Stub: forzamos que el cliente diga que sí hay cupo
        when(disponibilidad.estaDisponible(any())).thenReturn(true);

        ReservaService servicio = new ReservaService(disponibilidad, repository, notificador);
        Reserva reserva = new Reserva("R-001", "NORMAL");

        // Act: ejecutamos el método productivo
        servicio.confirmar(reserva);

        // Assert y Mocks: verificamos estado y llamadas externas
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        verify(repository).guardar(reserva);
        verify(notificador).enviarConfirmacion(reserva);
    }

    @Test
    void reservaNoDisponibleNoSeGuardaNiNotifica() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        // Stub: forzamos que responda false (sin cupo)
        when(disponibilidad.estaDisponible(any())).thenReturn(false);

        ReservaService servicio = new ReservaService(disponibilidad, repository, notificador);
        Reserva reserva = new Reserva("R-002", "NORMAL");

        // Act & Assert: debe lanzar IllegalStateException
        assertThrows(IllegalStateException.class, () -> servicio.confirmar(reserva));

        // Mock: verificamos que NUNCA se guardó ni se envió correo
        verify(repository, never()).guardar(any());
        verify(notificador, never()).enviarConfirmacion(any());
    }

    @Test
    void reservaNulaNoConsultaDependencias() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        ReservaService servicio = new ReservaService(disponibilidad, repository, notificador);

        // Act & Assert: pasar null debe rechazar inmediatamente
        assertThrows(IllegalArgumentException.class, () -> servicio.confirmar(null));

        // Verificamos que ninguna dependencia externa fue molestada
        verify(disponibilidad, never()).estaDisponible(any());
        verify(repository, never()).guardar(any());
        verify(notificador, never()).enviarConfirmacion(any());
    }
```[cite: 5]
4. Guarda los cambios del archivo[cite: 5].

---

### Paso 4: Ejecutar las pruebas y abrir el reporte JaCoCo
1. En tu terminal vuelve a compilar y ejecutar:
   ```bash
   mvn clean test
   ```[cite: 5]
   *(Asegúrate de que pasen las 12 pruebas sin fallas: 9 de la anterior + 3 de esta)*[cite: 1, 5, 6].
2. El comando Maven genera automáticamente una carpeta llamada `target/site/jacoco`[cite: 5].
3. Abre el archivo `target/site/jacoco/index.html` en tu navegador web[cite: 5]:
   * En Mac: `open target/site/jacoco/index.html`
   * En Windows: `start target/site/jacoco/index.html`
4. Haz clic en el paquete `edu.uees.testing.service` y entra a la clase `ReservaService`[cite: 5]. Verás las líneas en verde (cubiertas) y podrás comprobar que los métodos tienen cobertura completa de líneas e instrucciones[cite: 5, 6].

---

### Paso 5: Llenar el archivo de análisis
1. Abre con tu editor el archivo que viene en el proyecto:
   `docs/02_ANALISIS_COBERTURA_PLANTILLA.md`[cite: 5]
2. Completa las preguntas con este contenido técnico[cite: 5]:
   * **¿Qué método tiene menor cobertura?** Inicialmente `calcularTotal()` si faltaba la prueba de excepción negativa[cite: 5, 6]. Con los casos agregados, tanto `puedeCancelar()`, `calcularTotal()` y `confirmar()` alcanzan 100% de cobertura de líneas y ramas[cite: 5, 6].
   * **¿Qué comportamiento falta por proteger?** Falta proteger el comportamiento ante una falla externa incontrolada (por ejemplo, si el cliente de disponibilidad arroja un error de red o timeout)[cite: 5, 6].
   * **¿Qué prueba nueva aportaría valor?** Una prueba donde `disponibilidad.estaDisponible(...)` lance una excepción en tiempo de ejecución (`RuntimeException`), para validar que el sistema no guarde la reserva en estado inconsistente[cite: 5, 6].
   * **¿Existe código cubierto pero mal probado?** Sí[cite: 5, 6]. Se puede invocar un método y tener la línea verde en JaCoCo al 100%, pero si no se coloca un `assertEquals` o un `verify`, no se comprueba que el comportamiento interno haya sido el correcto (cobertura no equivale a calidad de aserción)[cite: 5, 6].
3. Guarda el archivo[cite: 5].

---

### Paso 6: Guardar tus avances en Git con commits pequeños
La rúbrica exige que el historial muestre cómo fuiste avanzando[cite: 5]. Ejecuta estos comandos en tu terminal uno a uno:

1. **Guardar las pruebas creadas:**
   ```bash
   git add src/test/java/edu/uees/testing/service/ReservaServiceTest.java
   git commit -m "test: agregar escenarios con stub y mock para confirmar reserva"
   ```[cite: 5]

2. **Guardar el análisis de JaCoCo:**
   ```bash
   git add docs/02_ANALISIS_COBERTURA_PLANTILLA.md
   git commit -m "docs: analizar cobertura jacoco y registrar huecos de prueba"
   ```[cite: 5]

3. **Verificar tu historial ordenado:**
   ```bash
   git log --oneline --decorate -n 5
   ```[cite: 5, 6]
   *(Te mostrará los commits realizados de forma clara)*[cite: 5, 6].

---

Con estos 6 pasos tienes el código funcionando, las pruebas en verde, JaCoCo generado y el historial de Git listo para entregar[cite: 5].
}
