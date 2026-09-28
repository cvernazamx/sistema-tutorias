package edu.uees.disenosoftware.factory;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[EMAIL]");
        System.out.println("Destino: " + destino);
        System.out.println("Mensaje: " + mensaje);
    }
}