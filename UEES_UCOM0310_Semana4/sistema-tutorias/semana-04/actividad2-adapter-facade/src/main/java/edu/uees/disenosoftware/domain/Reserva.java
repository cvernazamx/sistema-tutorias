package edu.uees.disenosoftware.domain;

public class Reserva {
    private final String id;
    private final Estudiante estudiante;
    private final Docente docente;
    private final String tema;

    public Reserva(String id, Estudiante estudiante,
                   Docente docente, String tema) {
        this.id = id;
        this.estudiante = estudiante;
        this.docente = docente;
        this.tema = tema;
    }

    public String getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public Docente getDocente() { return docente; }
    public String getTema() { return tema; }
}
