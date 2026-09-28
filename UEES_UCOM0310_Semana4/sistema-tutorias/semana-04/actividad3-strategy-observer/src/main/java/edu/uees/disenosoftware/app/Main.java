package edu.uees.disenosoftware.app;

import edu.uees.disenosoftware.domain.*;
import edu.uees.disenosoftware.observer.*;
import edu.uees.disenosoftware.strategy.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TUTORÍAS (STRATEGY & OBSERVER) ===");

        // 1. Probar Observer: Registrar suscriptores y confirmar reserva
        System.out.println("\n--- Prueba 1: Notificación de Eventos (Observer) ---");
        Reserva r1 = new Reserva("RES-101", LocalDateTime.now().plusHours(48));
        r1.agregarObserver(new EmailObserver());
        r1.agregarObserver(new CalendarioObserver());
        r1.agregarObserver(new PanelEstudianteObserver());
        r1.agregarObserver(new PanelDocenteObserver());
        
        System.out.println("Disparando evento de confirmación:");
        r1.confirmar();

        // 2. Probar Strategy: Cancelación con política grupal (24 horas)
        System.out.println("\n--- Prueba 2: Política de Cancelación Grupal (Strategy) ---");
        ServicioCancelacion servicio = new ServicioCancelacion(new CancelacionGrupal());
        servicio.cancelar(r1);

        // 3. Probar Strategy: Política de Emergencia
        System.out.println("\n--- Prueba 3: Política de Emergencia ---");
        Reserva r2 = new Reserva("RES-102", LocalDateTime.now().plusMinutes(30));
        r2.agregarObserver(new EmailObserver());
        r2.agregarObserver(new PanelDocenteObserver());

        servicio.cambiarPolitica(new CancelacionEmergencia());
        servicio.cancelar(r2);

        System.out.println("\n=== EJECUCIÓN COMPLETADA SATISFACTORIAMENTE ===");
    }
}
