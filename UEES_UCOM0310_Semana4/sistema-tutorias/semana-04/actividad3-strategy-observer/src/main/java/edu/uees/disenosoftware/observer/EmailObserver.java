package edu.uees.disenosoftware.observer;

import edu.uees.disenosoftware.domain.Reserva;

public class EmailObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[EmailObserver] Notificación enviada. Nuevo estado: " + reserva.getEstado());
    }
}
