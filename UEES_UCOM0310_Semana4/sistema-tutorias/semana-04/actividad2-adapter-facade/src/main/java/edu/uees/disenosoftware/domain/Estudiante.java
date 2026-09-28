package edu.uees.disenosoftware.domain;

public class Estudiante {
    private final String nombre;
    private final String correo;

    public Estudiante(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
}
