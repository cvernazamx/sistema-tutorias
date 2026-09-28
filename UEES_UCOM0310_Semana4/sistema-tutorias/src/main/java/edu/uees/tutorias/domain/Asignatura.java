package edu.uees.tutorias.domain;

public class Asignatura {
	private final Long id;
	private final String nombre;

	public Asignatura(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() { return id; }
	public String getNombre() { return nombre; }
}
