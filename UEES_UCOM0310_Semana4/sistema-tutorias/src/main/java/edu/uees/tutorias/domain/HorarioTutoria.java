package edu.uees.tutorias.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class HorarioTutoria {
	private final Long id;
	private final LocalDate fecha;
	private final LocalTime horaInicio;
	private final LocalTime horaFin;
	private final Docente docente;
	private boolean disponible = true;

	public HorarioTutoria(Long id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Docente docente) {
		this.id = id;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.docente = docente;
	}

	public void reservar() {
		if (!disponible) {
			throw new IllegalStateException("El horario ya está reservado.");
		}
		disponible = false;
	}

	public void liberar() { disponible = true; }
	public boolean isDisponible() { return disponible; }
	public Docente getDocente() { return docente; }
	public Long getId() { return id; }
	public LocalDate getFecha() { return fecha; }
	public LocalTime getHoraInicio() { return horaInicio; }
	public LocalTime getHoraFin() { return horaFin; }
}
