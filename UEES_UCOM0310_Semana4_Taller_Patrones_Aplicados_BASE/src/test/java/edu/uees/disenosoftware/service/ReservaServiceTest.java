package edu.uees.disenosoftware.service;

import edu.uees.disenosoftware.domain.EstadoReserva;
import edu.uees.disenosoftware.domain.Reserva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaServiceTest {

    @Test
    void reservaVipSeConfirmaConQuincePorCiento() {
        Reserva r = new Reserva("T-001", 100.0, "VIP");

        new ReservaService().confirmar(r);

        assertEquals(EstadoReserva.CONFIRMADA, r.getEstado());
        assertEquals(15.0, r.getDescuento(), 0.001);
    }

    @Test
    void reservaNormalSeConfirmaSinDescuento() {
        Reserva r = new Reserva("T-002", 100.0, "NORMAL");

        new ReservaService().confirmar(r);

        assertEquals(EstadoReserva.CONFIRMADA, r.getEstado());
        assertEquals(0.0, r.getDescuento(), 0.001);
    }
}
