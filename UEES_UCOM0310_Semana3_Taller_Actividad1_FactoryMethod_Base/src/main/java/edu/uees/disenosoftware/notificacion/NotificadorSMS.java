package edu.uees.disenosoftware.notificacion;

public class NotificadorSMS implements Notificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.printf("[SMS] Destino: %s | Mensaje: %s%n", destino, mensaje);
    }
}
