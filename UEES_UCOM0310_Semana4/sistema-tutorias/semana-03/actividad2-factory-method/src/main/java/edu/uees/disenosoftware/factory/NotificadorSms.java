package edu.uees.disenosoftware.factory;

public class NotificadorSms implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[SMS]");
        System.out.println("Telefono: " + destino);
        System.out.println("Mensaje: " + mensaje);
    }
}