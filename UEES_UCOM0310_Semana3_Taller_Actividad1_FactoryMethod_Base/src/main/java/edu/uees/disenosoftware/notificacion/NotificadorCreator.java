package edu.uees.disenosoftware.notificacion;

public abstract class NotificadorCreator {
	
	protected abstract Notificador crear(); //que clase concreta se construir
	
	public void notificar(
			String destino,
			String mensaje
				) {
		Notificador notificador = crear();
		
		notificador.enviar(destino, mensaje);
	}
	

}
