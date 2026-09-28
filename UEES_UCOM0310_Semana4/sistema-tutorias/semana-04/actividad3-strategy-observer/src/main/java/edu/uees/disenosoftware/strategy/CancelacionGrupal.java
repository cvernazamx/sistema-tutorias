package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionGrupal implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        return reserva.horasRestantes() >= 24;
    }
}
