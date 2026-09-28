package ec.edu.uees;

public class Reserva {
    private String id;
    private String estudiante;
    private boolean cancelada;
    private boolean confirmada;

    public Reserva(String id, String estudiante, boolean cancelada) {
        this.id = id;
        this.estudiante = estudiante;
        this.cancelada = cancelada;
        this.confirmada = false;
    }

    public String getId() {
        return id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void confirmar() {
        this.confirmada = true;
    }
}
