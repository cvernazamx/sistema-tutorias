package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioReservasTest {

    private ServicioReservas servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioReservas();
    }

    private Reserva reservaNormalValida() {
        LocalDateTime inicio = LocalDateTime.of(2026, 9, 20, 10, 0);
        return new Reserva("R-NORMAL", "ana@uees.edu.ec", inicio, inicio.plusHours(1), "NORMAL");
    }

    private Reserva reservaVipValida() {
        LocalDateTime inicio = LocalDateTime.of(2026, 9, 20, 10, 0);
        return new Reserva("R-VIP", "vip@uees.edu.ec", inicio, inicio.plusHours(1), "VIP");
    }

    @Test
    void normalActualmenteRetornaCuarenta() {
        Reserva reserva = reservaNormalValida();
        double total = servicio.procesar(reserva, 5);
        assertAll(
            () -> assertEquals(40, total, 0.001),
            () -> assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado())
        );
    }

    @Test
    void vipActualmenteRetornaTreintaYCuatro() {
        Reserva reserva = reservaVipValida();
        double total = servicio.procesar(reserva, 5);
        assertEquals(34, total, 0.001);
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void correoInvalidoNoProcesaReserva() {
        LocalDateTime inicio = LocalDateTime.of(2026, 9, 20, 10, 0);
        Reserva reserva = new Reserva("R-EMAIL", "correo-invalido", inicio, inicio.plusHours(1), "NORMAL");
        double total = servicio.procesar(reserva, 5);
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void periodoConFinAnteriorNoProcesa() {
        LocalDateTime inicio = LocalDateTime.of(2026, 9, 20, 10, 0);
        Reserva reserva = new Reserva("R-PERIODO", "ana@uees.edu.ec", inicio, inicio.minusHours(1), "NORMAL");
        double total = servicio.procesar(reserva, 5);
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void dosHorasExactasPermitenProcesar() {
        Reserva reserva = reservaNormalValida();
        double total = servicio.procesar(reserva, 2);
        assertEquals(40, total, 0.001);
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void unaHoraNoPermiteProcesar() {
        Reserva reserva = reservaNormalValida();
        double total = servicio.procesar(reserva, 1);
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void reservaNulaRetornaCero() {
        double total = servicio.procesar(null, 5);
        assertEquals(0, total, 0.001);
    }
}
