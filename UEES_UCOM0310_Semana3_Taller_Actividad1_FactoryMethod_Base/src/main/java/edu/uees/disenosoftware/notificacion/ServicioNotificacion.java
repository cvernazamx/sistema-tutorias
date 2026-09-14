package edu.uees.disenosoftware.notificacion;

/**
 * Servicio de ejemplo para el caso del Sistema de gestión de tutorías.
 */
public class ServicioNotificacion {

    private final CreadorNotificadorSimple creador;

    public ServicioNotificacion(CreadorNotificadorSimple creador) {
        this.creador = creador;
    }

    public void notificar(
            String tipo,
            String destino,
            String mensaje) {

        Notificador notificador = creador.crear(tipo);
        notificador.enviar(destino, mensaje);
    }
}
