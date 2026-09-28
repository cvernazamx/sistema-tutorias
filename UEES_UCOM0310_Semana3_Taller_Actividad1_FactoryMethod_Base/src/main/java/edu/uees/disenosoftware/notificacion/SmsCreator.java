package edu.uees.disenosoftware.notificacion;

public class SmsCreator extends NotificadorCreator{

	@Override
	protected Notificador crear() {
		return new NotificadorSMS();
	}

}
