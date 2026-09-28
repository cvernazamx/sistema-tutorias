package edu.uees.patrones.factory;

public class NotificadorPush implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[PUSH] Usuario: " + destino + " | Mensaje: " + mensaje);
    }
}
