package edu.uees.patrones.builder;

import java.time.LocalDateTime;

public final class Reserva {
    private final String estudiante;
    private final String docente;
    private final LocalDateTime fechaHora;
    private final Modalidad modalidad;
    private final String motivo;
    private final String observacion;
    private final Prioridad prioridad;
    private final boolean recordatorio;
    private final String enlace;
    private final int duracionMinutos;

    Reserva(
            String estudiante,
            String docente,
            LocalDateTime fechaHora,
            Modalidad modalidad,
            String motivo,
            String observacion,
            Prioridad prioridad,
            boolean recordatorio,
            String enlace,
            int duracionMinutos) {
        this.estudiante = estudiante;
        this.docente = docente;
        this.fechaHora = fechaHora;
        this.modalidad = modalidad;
        this.motivo = motivo;
        this.observacion = observacion;
        this.prioridad = prioridad;
        this.recordatorio = recordatorio;
        this.enlace = enlace;
        this.duracionMinutos = duracionMinutos;
    }

    public String getEstudiante() { return estudiante; }
    public String getDocente() { return docente; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Modalidad getModalidad() { return modalidad; }
    public String getMotivo() { return motivo; }
    public String getObservacion() { return observacion; }
    public Prioridad getPrioridad() { return prioridad; }
    public boolean isRecordatorio() { return recordatorio; }
    public String getEnlace() { return enlace; }
    public int getDuracionMinutos() { return duracionMinutos; }

    @Override
    public String toString() {
        return "Reserva{" +
            "estudiante='" + estudiante + '\'' +
            ", docente='" + docente + '\'' +
            ", fechaHora=" + fechaHora +
            ", modalidad=" + modalidad +
            ", motivo='" + motivo + '\'' +
            ", observacion='" + observacion + '\'' +
            ", prioridad=" + prioridad +
            ", recordatorio=" + recordatorio +
            ", enlace='" + enlace + '\'' +
            ", duracionMinutos=" + duracionMinutos +
            '}';
    }
}
