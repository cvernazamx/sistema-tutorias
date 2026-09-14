package edu.uees.disenosoftware.domain;

import edu.uees.disenosoftware.observer.ReservaObserver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final String id;
    private final LocalDateTime fechaHora;
    private EstadoReserva estado = EstadoReserva.PENDIENTE;
    private final List<ReservaObserver> observers = new ArrayList<>();

    public Reserva(String id, LocalDateTime fechaHora) {
        this.id = id;
        this.fechaHora = fechaHora;
    }

    public void agregarObserver(ReservaObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(ReservaObserver observer) {
        observers.remove(observer);
    }

    private void notificar() {
        for (ReservaObserver observer : observers) {
            observer.actualizar(this);
        }
    }

    public void confirmar() {
        estado = EstadoReserva.CONFIRMADA;
        notificar();
    }

    public void cancelar() {
        estado = EstadoReserva.CANCELADA;
        notificar();
    }

    public long horasRestantes() {
        return Duration.between(LocalDateTime.now(), fechaHora).toHours();
    }

    public long minutosRestantes() {
        return Duration.between(LocalDateTime.now(), fechaHora).toMinutes();
    }

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public EstadoReserva getEstado() { return estado; }
}
