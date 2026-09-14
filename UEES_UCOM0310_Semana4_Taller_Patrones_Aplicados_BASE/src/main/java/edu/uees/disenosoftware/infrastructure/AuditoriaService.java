package edu.uees.disenosoftware.infrastructure;

import edu.uees.disenosoftware.domain.Reserva;

public class AuditoriaService {
    public void registrar(Reserva reserva) {
        System.out.println("[AUDITORÍA] evento registrado: " + reserva.getId());
    }
}
