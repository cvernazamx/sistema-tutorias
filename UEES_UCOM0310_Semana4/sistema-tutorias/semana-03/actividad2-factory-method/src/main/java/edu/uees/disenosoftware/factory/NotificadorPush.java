package edu.uees.disenosoftware.factory;

public class NotificadorPush implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[PUSH]");
        System.out.println("Usuario: " + destino);
        System.out.println("Mensaje: " + mensaje);
    }
}