package edu.uees.disenosoftware.notificacion;

public class NotificadorPush implements Notificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.printf("[PUSH] Destino: %s | Mensaje: %s%n", destino, mensaje);
    }
}
