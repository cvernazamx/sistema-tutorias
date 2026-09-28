package edu.uees.patrones.factory;

public class NotificadorTeams implements Notificador {
    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[TEAMS] Usuario: " + destino + " | Mensaje: " + mensaje);
    }
}
