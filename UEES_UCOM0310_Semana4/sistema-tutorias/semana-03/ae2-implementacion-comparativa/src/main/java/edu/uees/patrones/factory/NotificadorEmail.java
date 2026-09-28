package edu.uees.patrones.factory;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[EMAIL] Destino: " + destino + " | Mensaje: " + mensaje);
    }
}
