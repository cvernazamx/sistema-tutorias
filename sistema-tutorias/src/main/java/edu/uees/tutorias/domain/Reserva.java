package edu.uees.tutorias.domain;

import java.time.LocalDateTime;

public class Reserva {
	private final Long id;
	private final LocalDateTime fechaCreacion;
	private EstadoReserva estado;
	private final Estudiante estudiante;
	private final HorarioTutoria horario;
	private final String motivo;

	public Reserva(Long id, Estudiante estudiante, HorarioTutoria horario, String motivo) {
		this.id = id;
		this.estudiante = estudiante;
		this.horario = horario;
		this.motivo = motivo;
		this.fechaCreacion = LocalDateTime.now();
		this.estado = EstadoReserva.PENDIENTE;
		this.horario.reservar();
	}

	public void confirmar() {
		if (estado == EstadoReserva.CANCELADA) {
			throw new IllegalStateException("No se puede confirmar una reserva cancelada.");
		}
		estado = EstadoReserva.CONFIRMADA;
	}

	public void cancelar(String motivoCancelacion) {
		estado = EstadoReserva.CANCELADA;
		horario.liberar();
	}

	public Long getId() { return id; }
	public EstadoReserva getEstado() { return estado; }
	public Estudiante getEstudiante() { return estudiante; }
	public HorarioTutoria getHorario() { return horario; }
	public String getMotivo() { return motivo; }
	public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
