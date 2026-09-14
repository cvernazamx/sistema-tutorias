package edu.uees.disenosoftware.observer;

import edu.uees.disenosoftware.domain.Reserva;

public class PanelEstudianteObserver implements ReservaObserver {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("[PanelEstudiante] Interfaz de estudiante actualizada a: " + reserva.getEstado());
    }
}
