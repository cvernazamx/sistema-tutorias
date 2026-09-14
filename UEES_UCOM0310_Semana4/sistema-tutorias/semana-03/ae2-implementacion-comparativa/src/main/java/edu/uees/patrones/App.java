package edu.uees.patrones;

import edu.uees.patrones.factory.*;
import edu.uees.patrones.builder.*;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACION FACTORY METHOD ===");
        NotificadorCreator email = new EmailCreator();
        email.notificar("estudiante@uees.edu.ec", "Su tutoria de Diseno de Software ha sido asignada");

        NotificadorCreator push = new PushCreator();
        push.notificar("usuario-001", "Recordatorio: Tutoria en 30 minutos");

        NotificadorCreator sms = new SmsCreator();
        sms.notificar("+593999999999", "Codigo de confirmacion de tutoria: 482910");

        NotificadorCreator teams = new TeamsCreator();
        teams.notificar("cvernaza@uees.edu.ec", "Sesion de tutoria iniciada en Microsoft Teams");
        System.out.println();

        System.out.println("=== DEMOSTRACION BUILDER ===");
        Reserva res1 = new ReservaBuilder()
            .estudiante("Ana Torres")
            .docente("Carlos Perez")
            .fechaHora(LocalDateTime.of(2026, 8, 29, 18, 0))
            .modalidad(Modalidad.VIRTUAL)
            .motivo("Revision de proyecto")
            .observacion("Analizar diagrama UML")
            .prioridad(Prioridad.ALTA)
            .recordatorio(true)
            .enlace("https://meet.example/tutoria")
            .duracionMinutos(45)
            .build();
        System.out.println("Reserva Completa (Virtual):");
        System.out.println(res1);
        System.out.println();

        Reserva res2 = new ReservaBuilder()
            .estudiante("Maria Lopez")
            .docente("Juan Garcia")
            .fechaHora(LocalDateTime.of(2026, 8, 30, 10, 0))
            .modalidad(Modalidad.PRESENCIAL)
            .build();
        System.out.println("Reserva con Valores por Defecto (Presencial):");
        System.out.println(res2);
        System.out.println();

        System.out.println("Prueba de Validacion:");
        try {
            Reserva inv = new ReservaBuilder()
                .docente("Carlos Perez")
                .fechaHora(LocalDateTime.now())
                .modalidad(Modalidad.VIRTUAL)
                .build();
            System.out.println(inv);
        } catch (IllegalStateException e) {
            System.out.println("Validacion correcta: " + e.getMessage());
        }
    }
}
