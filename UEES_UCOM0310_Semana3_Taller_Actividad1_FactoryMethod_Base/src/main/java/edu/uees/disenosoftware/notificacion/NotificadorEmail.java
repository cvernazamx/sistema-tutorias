package edu.uees.disenosoftware.notificacion;

public class NotificadorEmail implements Notificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.printf("[EMAIL] Destino: %s | Mensaje: %s%n", destino, mensaje);
    }
}
