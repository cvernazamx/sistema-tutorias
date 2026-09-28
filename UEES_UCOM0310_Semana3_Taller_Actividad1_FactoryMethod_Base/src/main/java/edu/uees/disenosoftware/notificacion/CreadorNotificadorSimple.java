package edu.uees.disenosoftware.notificacion;

/**
 * VERSIÓN INICIAL DEL TALLER.
 *
 * Esta clase funciona, pero conoce directamente todas las clases concretas.
 * El objetivo del taller es analizar si esta variabilidad justifica
 * refactorizar la creación mediante Factory Method.
 */
public class CreadorNotificadorSimple {

    public Notificador crear(String tipo) {

        if ("EMAIL".equalsIgnoreCase(tipo)) {
            return new NotificadorEmail();
        }

        if ("PUSH".equalsIgnoreCase(tipo)) {
            return new NotificadorPush();
        }

        if ("SMS".equalsIgnoreCase(tipo)) {
            return new NotificadorSMS();
        }
        
        throw new IllegalArgumentException(
            "Tipo de notificación no soportado: " + tipo
        );
    }
}
