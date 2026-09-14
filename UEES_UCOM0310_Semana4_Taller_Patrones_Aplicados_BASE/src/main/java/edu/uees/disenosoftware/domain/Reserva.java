package edu.uees.disenosoftware.domain;

public class Reserva {
    private final String id;
    private final double total;
    private final String tipo;
    private double descuento;
    private EstadoReserva estado = EstadoReserva.PENDIENTE;

    public Reserva(String id, double total, String tipo) {
        this.id = id;
        this.total = total;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public double getTotal() { return total; }
    public String getTipo() { return tipo; }
    public double getDescuento() { return descuento; }
    public EstadoReserva getEstado() { return estado; }

    public void aplicarDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void confirmar() {
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void rechazar() {
        this.estado = EstadoReserva.RECHAZADA;
    }
}
