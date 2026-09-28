package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva);
}
