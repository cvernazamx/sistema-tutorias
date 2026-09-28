package edu.uees.disenosoftware.facade;

public class NotificadorConsola implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("Notificación a " + destino + ": " + mensaje);
    }
}
