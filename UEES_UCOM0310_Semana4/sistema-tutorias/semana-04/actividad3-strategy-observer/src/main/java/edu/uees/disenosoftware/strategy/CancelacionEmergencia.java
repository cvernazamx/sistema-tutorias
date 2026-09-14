package edu.uees.disenosoftware.strategy;

import edu.uees.disenosoftware.domain.Reserva;

public class CancelacionEmergencia implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        // Precondición: al menos 15 minutos de anticipación por causa de fuerza mayor
        return reserva.minutosRestantes() >= 15;
    }
}
