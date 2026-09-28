package edu.uees.disenosoftware.app;

import edu.uees.disenosoftware.domain.Reserva;
import edu.uees.disenosoftware.service.ReservaService;

public class Main {

    public static void main(String[] args) {

        Reserva vip = new Reserva(
            "R-001",
            100.0,
            "VIP"
        );

        Reserva normal = new Reserva(
            "R-002",
            80.0,
            "NORMAL"
        );

        ReservaService servicio = new ReservaService();

        System.out.println("=== RESERVA VIP ===");
        servicio.confirmar(vip);
        System.out.println("Estado: " + vip.getEstado());
        System.out.println("Descuento: " + vip.getDescuento());

        System.out.println();

        System.out.println("=== RESERVA NORMAL ===");
        servicio.confirmar(normal);
        System.out.println("Estado: " + normal.getEstado());
        System.out.println("Descuento: " + normal.getDescuento());
    }
}
