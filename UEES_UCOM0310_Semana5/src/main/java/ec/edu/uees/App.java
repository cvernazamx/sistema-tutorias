package ec.edu.uees;

public class App {
    public static void main(String[] args) {
        ServicioTutorias servicio = new ServicioTutorias();

        System.out.println("=== EJECUCIÓN LÍNEA BASE / CASOS DE PRUEBA ===");

        System.out.println("\nCaso A (Valida, 5h):");
        Reserva resA = new Reserva("RES-101", "Estudiante 1", false);
        servicio.confirmarReserva(resA, 5);
        System.out.println("Estado confirmada: " + resA.isConfirmada());

        System.out.println("\nCaso B (Cancelada, 5h):");
        Reserva resB = new Reserva("RES-102", "Estudiante 2", true);
        servicio.confirmarReserva(resB, 5);
        System.out.println("Estado confirmada: " + resB.isConfirmada());

        System.out.println("\nCaso C (Reserva null):");
        servicio.confirmarReserva(null, 5);

        System.out.println("\nCaso D (Valida, 1h):");
        Reserva resD = new Reserva("RES-104", "Estudiante 4", false);
        servicio.confirmarReserva(resD, 1);
        System.out.println("Estado confirmada: " + resD.isConfirmada());
    }
}
