package edu.uees.patrones.factory;

public class SmsCreator extends NotificadorCreator {
    @Override
    protected Notificador crearNotificador() {
        return new NotificadorSms();
    }
}
