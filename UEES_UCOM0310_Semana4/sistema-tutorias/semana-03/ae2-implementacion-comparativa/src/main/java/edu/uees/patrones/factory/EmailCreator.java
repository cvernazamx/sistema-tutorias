package edu.uees.patrones.factory;

public class EmailCreator extends NotificadorCreator {
    @Override
    protected Notificador crearNotificador() {
        return new NotificadorEmail();
    }
}
