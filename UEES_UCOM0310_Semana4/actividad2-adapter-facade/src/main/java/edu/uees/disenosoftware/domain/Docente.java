package edu.uees.disenosoftware.domain;

public class Docente {
    private final String nombre;
    private final String correo;

    public Docente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
}
