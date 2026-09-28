package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Usuario;

public class NotificadorEmail implements INotificador {
    @Override
    public void enviar(Usuario destinatario, String mensaje) {
        System.out.println("Enviando correo a " + destinatario.getEmail() + ": " + mensaje);
    }
}