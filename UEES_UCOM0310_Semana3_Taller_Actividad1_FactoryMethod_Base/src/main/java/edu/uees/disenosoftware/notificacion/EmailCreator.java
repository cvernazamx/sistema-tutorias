package edu.uees.disenosoftware.notificacion;

public class EmailCreator extends NotificadorCreator {

	@Override
	protected Notificador crear() {
		return new NotificadorEmail();
	}

}
