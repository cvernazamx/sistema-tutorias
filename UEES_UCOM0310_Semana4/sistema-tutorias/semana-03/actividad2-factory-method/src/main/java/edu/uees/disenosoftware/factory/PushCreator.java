package edu.uees.disenosoftware.factory;

public class PushCreator extends NotificadorCreator {
    @Override
    protected Notificador crearNotificador() {
        return new NotificadorPush();
    }
}