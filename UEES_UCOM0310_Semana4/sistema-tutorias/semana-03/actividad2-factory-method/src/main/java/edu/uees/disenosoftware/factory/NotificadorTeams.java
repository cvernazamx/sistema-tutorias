package edu.uees.disenosoftware.factory;

public class NotificadorTeams implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[TEAMS]");
        System.out.println("Usuario: " + destino);
        System.out.println("Mensaje: " + mensaje);
    }
}