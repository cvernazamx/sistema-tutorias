package edu.uees.disenosoftware.adapter;

public class TeamsAdapter implements Videoconferencia {
    private final MicrosoftTeamsAPI teamsApi;

    public TeamsAdapter(MicrosoftTeamsAPI teamsApi) {
        this.teamsApi = teamsApi;
    }

    @Override
    public String crearSala(String titulo, String correoDocente) {
        return teamsApi.scheduleOnlineMeeting(titulo, correoDocente);
    }
}
