package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

public class NotificadorReserva {

    public void notificarConfirmacion(Reserva reserva) {
        if (reserva != null && reserva.getCorreo() != null) {
            System.out.println("Correo enviado a " + reserva.getCorreo());
        }
    }
}
