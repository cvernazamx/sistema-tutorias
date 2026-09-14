package edu.uees.disenosoftware.notificacion;

public class PushCreator  extends NotificadorCreator{

	@Override
	protected Notificador crear() {
	return new NotificadorPush();
	}

}
