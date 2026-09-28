package edu.uees.disenosoftware.builder;

import java.time.LocalDateTime;

public class ReservaBuilder {
    private String estudiante;
    private String docente;
    private LocalDateTime fechaHora;
    private Modalidad modalidad;
    private String motivo = "Sin motivo especificado";
    private String observacion = "";
    private Prioridad prioridad = Prioridad.MEDIA;
    private boolean recordatorio = false;
    private String enlace = "";
    private int duracionMinutos = 30;
    private String idiomaNotificacion = "ES";
    private boolean requiereGrabacion = false;

    public ReservaBuilder estudiante(String estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public ReservaBuilder docente(String docente) {
        this.docente = docente;
        return this;
    }

    public ReservaBuilder fechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        return this;
    }

    public ReservaBuilder modalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public ReservaBuilder motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public ReservaBuilder observacion(String observacion) {
        this.observacion = observacion;
        return this;
    }

    public ReservaBuilder prioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public ReservaBuilder recordatorio(boolean recordatorio) {
        this.recordatorio = recordatorio;
        return this;
    }

    public ReservaBuilder enlace(String enlace) {
        this.enlace = enlace;
        return this;
    }

    public ReservaBuilder duracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
        return this;
    }

    public ReservaBuilder idiomaNotificacion(String idiomaNotificacion) {
        this.idiomaNotificacion = idiomaNotificacion;
        return this;
    }

    public ReservaBuilder requiereGrabacion(boolean requiereGrabacion) {
        this.requiereGrabacion = requiereGrabacion;
        return this;
    }

    public Reserva build() {
        validar();
        return new Reserva(
            estudiante,
            docente,
            fechaHora,
            modalidad,
            motivo,
            observacion,
            prioridad,
            recordatorio,
            enlace,
            duracionMinutos,
            idiomaNotificacion,
            requiereGrabacion
        );
    }

    private void validar() {
        if (estudiante == null || estudiante.isBlank()) {
            throw new IllegalStateException("El estudiante es obligatorio");
        }
        if (docente == null || docente.isBlank()) {
            throw new IllegalStateException("El docente es obligatorio");
        }
        if (fechaHora == null) {
            throw new IllegalStateException("La fecha y hora son obligatorias");
        }
        if (modalidad == null) {
            throw new IllegalStateException("La modalidad es obligatoria");
        }
        if (duracionMinutos <= 0) {
            throw new IllegalStateException("La duracion debe ser mayor que cero");
        }
    }
}
