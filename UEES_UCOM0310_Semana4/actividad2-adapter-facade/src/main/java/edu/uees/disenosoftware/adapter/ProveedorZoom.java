package edu.uees.disenosoftware.adapter;

public class ProveedorZoom {
    public String generarMeeting(String topic, String hostEmail) {
        System.out.println("Zoom crea reunión: " + topic + " para " + hostEmail);
        return "https://zoom.us/meeting/123";
    }
}
