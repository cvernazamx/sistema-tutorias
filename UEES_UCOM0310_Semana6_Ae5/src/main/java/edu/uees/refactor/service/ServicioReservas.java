package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.PeriodoReserva;
import edu.uees.refactor.domain.Reserva;

public class ServicioReservas {

    private final NotificadorReserva notificador;

    public ServicioReservas() {
        this.notificador = new NotificadorReserva();
    }

    public double procesar(Reserva r, int horasAnticipacion) {
        // Guard Clause 1: Validación de entrada
        if (r == null) {
            return 0;
        }

        // Guard Clause 2: Validación de correo
        if (r.getCorreo() == null || !r.getCorreo().contains("@")) {
            return 0;
        }

        // Guard Clause 3: Delegación de regla temporal al Value Object
        PeriodoReserva periodo = new PeriodoReserva(r.getInicio(), r.getFin());
        if (!periodo.esValido()) {
            return 0;
        }

        // Guard Clause 4: Anticipación mínima
        if (horasAnticipacion < 2) {
            return 0;
        }

        double total = calcularTotal(r);

        guardar(r);
        notificador.notificarConfirmacion(r);

        r.setEstado(EstadoReserva.CONFIRMADA);

        return total;
    }

    private double calcularTotal(Reserva r) {
        double total = 40;
        if ("VIP".equals(r.getTipo())) {
            return total * 0.85;
        }
        return total;
    }

    private void guardar(Reserva r) {
        System.out.println("Guardando reserva " + r.getCodigo());
    }
}