package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public class ServicioCancelacion {
    private PoliticaCancelacion politica;

    public ServicioCancelacion(PoliticaCancelacion politica) {
        this.politica = politica;
    }

    public void cambiarPolitica(PoliticaCancelacion politica) {
        this.politica = politica;
    }

    public void cancelar(Reserva reserva) {
        if (!politica.puedeCancelar(reserva)) {
            throw new IllegalStateException("La política actual no permite cancelar esta tutoría.");
        }
        reserva.cancelar();
    }
}
