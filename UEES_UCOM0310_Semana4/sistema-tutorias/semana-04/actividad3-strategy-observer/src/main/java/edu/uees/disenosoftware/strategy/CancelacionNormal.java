package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionNormal implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        return reserva.horasRestantes() >= 2;
    }
}
