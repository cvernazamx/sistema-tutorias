package edu.uees.disenosoftware.infrastructure;

import edu.uees.disenosoftware.domain.Reserva;

public class InventarioService {
    public void verificar(Reserva reserva) {
        System.out.println("[INVENTARIO] disponibilidad verificada para " + reserva.getId());
    }
}
