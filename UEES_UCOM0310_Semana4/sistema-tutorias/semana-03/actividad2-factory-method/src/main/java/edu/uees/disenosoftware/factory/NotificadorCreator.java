package edu.uees.disenosoftware.factory;

public abstract class NotificadorCreator {
    protected abstract Notificador crearNotificador();

    public void notificar(String destino, String mensaje) {
        Notificador notificador = crearNotificador();
        notificador.enviar(destino, mensaje);
    }
}