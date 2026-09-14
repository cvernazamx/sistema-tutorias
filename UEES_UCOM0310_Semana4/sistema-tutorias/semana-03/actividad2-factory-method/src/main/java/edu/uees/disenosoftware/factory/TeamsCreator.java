package edu.uees.disenosoftware.factory;

public class TeamsCreator extends NotificadorCreator {
    @Override
    protected Notificador crearNotificador() {
        return new NotificadorTeams();
    }
}