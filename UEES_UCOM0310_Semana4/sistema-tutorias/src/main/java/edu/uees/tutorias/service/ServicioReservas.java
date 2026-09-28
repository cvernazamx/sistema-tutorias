package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.*;
import edu.uees.tutorias.notification.INotificador;
import edu.uees.tutorias.repository.IReservaRepository;

public class ServicioReservas {
    private final IReservaRepository repositorio;
    private final INotificador notificador;

    public ServicioReservas(IReservaRepository repositorio, INotificador notificador) {
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public Reserva crearReserva(Long id, Estudiante estudiante, HorarioTutoria horario, String motivo) {
        if (!horario.isDisponible()) {
            throw new IllegalStateException("El horario seleccionado ya no está disponible.");
        }
        Reserva reserva = new Reserva(id, estudiante, horario, motivo);
        repositorio.guardar(reserva);
        notificador.enviar(horario.getDocente(), "Nueva solicitud de tutoría de " + estudiante.getNombre());
        return reserva;
    }
}