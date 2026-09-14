package edu.uees.disenosoftware.facade;

import edu.uees.disenosoftware.domain.Reserva;

public class ServicioCalendario {
    public void registrar(Reserva reserva, String enlace) {
        System.out.println("Calendario actualizado para " + reserva.getId() + " -> " + enlace);
    }
}
