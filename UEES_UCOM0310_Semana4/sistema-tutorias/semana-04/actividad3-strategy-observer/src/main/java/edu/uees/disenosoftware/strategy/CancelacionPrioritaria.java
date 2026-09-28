package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionPrioritaria implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        return reserva.horasRestantes() >= 1;
    }
}
