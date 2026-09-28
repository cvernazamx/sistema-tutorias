package edu.uees.tutorias.domain;

public class Docente extends Usuario {
	private final String departamento;
	private final String cubiculo;

	public Docente(Long id, String nombre, String email, String departamento, String cubiculo) {
		super(id, nombre, email);
		this.departamento = departamento;
		this.cubiculo = cubiculo;
	}

	public String getDepartamento() { return departamento; }
	public String getCubiculo() { return cubiculo; }
}
