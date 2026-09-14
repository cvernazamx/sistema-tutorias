package edu.uees.patrones.factory;

public class NotificadorSms implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[SMS] Telefono: " + destino + " | Mensaje: " + mensaje);
    }
}
