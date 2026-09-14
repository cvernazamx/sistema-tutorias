package edu.uees.disenosoftware.factory;

public class App {
    public static void main(String[] args) {
        System.out.println("=== FACTORY METHOD | UEES ===");
        
        NotificadorCreator emailCreator = new EmailCreator();
        emailCreator.notificar("estudiante@uees.edu.ec", "Su tutoria fue confirmada");
        System.out.println();

        NotificadorCreator pushCreator = new PushCreator();
        pushCreator.notificar("usuario-001", "Tiene una tutoria en 30 minutos");
        System.out.println();

        NotificadorCreator smsCreator = new SmsCreator();
        smsCreator.notificar("+593999999999", "Su tutoria fue confirmada");
        System.out.println();

        NotificadorCreator teamsCreator = new TeamsCreator();
        teamsCreator.notificar("usuario-teams", "Reunion de tutoria iniciada");
    }
}