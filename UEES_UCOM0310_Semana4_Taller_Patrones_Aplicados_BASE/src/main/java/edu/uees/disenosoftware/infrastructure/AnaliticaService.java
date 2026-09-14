package edu.uees.disenosoftware.infrastructure;

import edu.uees.disenosoftware.domain.Reserva;

public class AnaliticaService {
    public void registrarEvento(Reserva reserva) {
        System.out.println("[ANALÍTICA] evento enviado: " + reserva.getId());
    }
}
