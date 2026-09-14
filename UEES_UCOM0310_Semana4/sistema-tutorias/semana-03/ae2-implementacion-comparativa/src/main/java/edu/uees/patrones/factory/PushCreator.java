package edu.uees.patrones.factory;

public class PushCreator extends NotificadorCreator {
    @Override
    protected Notificador crearNotificador() {
        return new NotificadorPush();
    }
}
