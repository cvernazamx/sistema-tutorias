package edu.uees.disenosoftware.observer;

import edu.uees.disenosoftware.domain.Reserva;

public class CalendarioObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[CalendarioObserver] Calendario sincronizado para " + reserva.getId());
    }
}
