package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Usuario;

public interface INotificador {
    void enviar(Usuario destinatario, String mensaje);
}