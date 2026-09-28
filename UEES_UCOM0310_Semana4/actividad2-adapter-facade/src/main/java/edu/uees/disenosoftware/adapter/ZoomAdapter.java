package edu.uees.disenosoftware.adapter;

public class ZoomAdapter implements Videoconferencia {
    private final ProveedorZoom zoom;

    public ZoomAdapter(ProveedorZoom zoom) {
        this.zoom = zoom;
    }

    @Override
    public String crearSala(String titulo, String correoDocente) {
        return zoom.generarMeeting(titulo, correoDocente);
    }
}
