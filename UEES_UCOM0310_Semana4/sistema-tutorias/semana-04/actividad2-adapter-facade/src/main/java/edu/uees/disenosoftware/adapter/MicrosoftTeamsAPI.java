package edu.uees.disenosoftware.adapter;

public class MicrosoftTeamsAPI {
    public String scheduleOnlineMeeting(String subject, String organizer) {
        System.out.println("[Teams API] Scheduling meeting: " + subject + " organized by " + organizer);
        return "https://teams.microsoft.com/l/meetup-join/19%3ameeting_uees_teams";
    }
}
