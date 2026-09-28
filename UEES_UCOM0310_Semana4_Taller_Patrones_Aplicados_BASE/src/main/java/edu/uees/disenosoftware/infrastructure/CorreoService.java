package edu.uees.disenosoftware.infrastructure;

import edu.uees.disenosoftware.domain.Reserva;

public class CorreoService {
    public void enviar(Reserva reserva) {
        System.out.println("[CORREO] confirmación enviada: " + reserva.getId());
    }
}
