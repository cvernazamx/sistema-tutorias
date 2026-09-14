package edu.uees.disenosoftware.facade;

import edu.uees.disenosoftware.adapter.Videoconferencia;
import edu.uees.disenosoftware.domain.Reserva;

public class TutoriasFacade {
    private final ServicioReservas reservas;
    private final Videoconferencia video;
    private final ServicioCalendario calendario;
    private final Notificador notificador;

    public TutoriasFacade(ServicioReservas reservas,
                          Videoconferencia video,
                          ServicioCalendario calendario,
                          Notificador notificador) {
        this.reservas = reservas;
        this.video = video;
        this.calendario = calendario;
        this.notificador = notificador;
    }

    public Reserva crearTutoriaVirtual(Reserva reserva) {
        Reserva guardada = reservas.guardar(reserva);

        String enlace = video.crearSala(
            guardada.getTema(),
            guardada.getDocente().getCorreo()
        );

        calendario.registrar(guardada, enlace);

        notificador.enviar(
            guardada.getEstudiante().getCorreo(),
            "Tutoría confirmada: " + enlace
        );

        return guardada;
    }
}
