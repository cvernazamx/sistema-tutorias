package edu.uees.disenosoftware.observer;

import edu.uees.disenosoftware.domain.Reserva;

public class PanelDocenteObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[PanelDocente] Interfaz docente actualizada para reserva: " + reserva.getId());
    }
}
