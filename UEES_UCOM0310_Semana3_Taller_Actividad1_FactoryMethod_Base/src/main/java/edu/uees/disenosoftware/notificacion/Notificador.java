package edu.uees.disenosoftware.notificacion;

/**
 * Contrato común para los mecanismos de notificación.
 *
 * En el taller este contrato representa la parte estable del diseño.
 */
public interface Notificador {

    void enviar(String destino, String mensaje);
}
