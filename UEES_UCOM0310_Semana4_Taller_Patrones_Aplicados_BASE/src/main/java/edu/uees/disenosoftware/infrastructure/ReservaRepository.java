package edu.uees.disenosoftware.infrastructure;

import edu.uees.disenosoftware.domain.Reserva;

public class ReservaRepository {
    public void guardar(Reserva reserva) {
        System.out.println("[REPOSITORIO] reserva guardada: " + reserva.getId());
    }
}
