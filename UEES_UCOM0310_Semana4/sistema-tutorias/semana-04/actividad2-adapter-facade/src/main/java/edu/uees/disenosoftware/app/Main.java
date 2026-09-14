package edu.uees.disenosoftware.app;

import edu.uees.disenosoftware.adapter.*;
import edu.uees.disenosoftware.domain.*;
import edu.uees.disenosoftware.facade.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TUTORÍAS (UEES) ===");

        Estudiante estudiante = new Estudiante("Christian Vernaza", "cvernaza@uees.edu.ec");
        Docente docente = new Docente("Docente Tutor", "tutor@uees.edu.ec");

        // 1. Demostración Facade con Proveedor Zoom vía Adapter
        System.out.println("\n--- Caso 1: Creación de Tutoría usando ZoomAdapter ---");
        Reserva reservaZoom = new Reserva("RES-001", estudiante, docente, "Patrones de Diseño: Adapter y Facade");
        TutoriasFacade facadeZoom = new TutoriasFacade(
            new ServicioReservas(),
            new ZoomAdapter(new ProveedorZoom()),
            new ServicioCalendario(),
            new NotificadorConsola()
        );
        facadeZoom.crearTutoriaVirtual(reservaZoom);

        // 2. Demostración Reto de Extensión: TeamsAdapter sin tocar la Facade
        System.out.println("\n--- Caso 2: Creación de Tutoría usando TeamsAdapter (Reto Extensión) ---");
        Reserva reservaTeams = new Reserva("RES-002", estudiante, docente, "Arquitectura de Software y OCP");
        TutoriasFacade facadeTeams = new TutoriasFacade(
            new ServicioReservas(),
            new TeamsAdapter(new MicrosoftTeamsAPI()),
            new ServicioCalendario(),
            new NotificadorConsola()
        );
        facadeTeams.crearTutoriaVirtual(reservaTeams);

        System.out.println("\n=== EJECUCIÓN FINALIZADA CON ÉXITO ===");
    }
}
