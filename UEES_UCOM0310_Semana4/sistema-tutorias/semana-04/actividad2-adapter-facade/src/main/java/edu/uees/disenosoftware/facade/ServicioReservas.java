package edu.uees.disenosoftware.facade;

import edu.uees.disenosoftware.domain.Reserva;

public class ServicioReservas {
    public Reserva guardar(Reserva reserva) {
        System.out.println("Reserva guardada: " + reserva.getId());
        return reserva;
    }
}
