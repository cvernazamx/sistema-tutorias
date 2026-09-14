package edu.uees.disenosoftware.notificacion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreadorNotificadorSimpleTest {

    private final CreadorNotificadorSimple creador =
        new CreadorNotificadorSimple();

    @Test
    void debeCrearNotificadorEmail() {
        Notificador resultado = creador.crear("EMAIL");
        assertInstanceOf(NotificadorEmail.class, resultado);
    }

    @Test
    void debeCrearNotificadorPush() {
        Notificador resultado = creador.crear("PUSH");
        assertInstanceOf(NotificadorPush.class, resultado);
    }

    @Test
    void debeCrearNotificadorSms() {
        Notificador resultado = creador.crear("SMS");
        assertInstanceOf(NotificadorSMS.class, resultado);
    }

    @Test
    void debeRechazarTipoNoSoportado() {
        assertThrows(
            IllegalArgumentException.class,
            () -> creador.crear("TEAMS")
        );
    }
}
