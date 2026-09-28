package ec.edu.uees;

public class ServicioTutorias {
    private static final int HORAS_MINIMAS_CONFIRMACION = 2;

    public void confirmarReserva(
            Reserva reserva,
            int horasAnticipacion) {
        if (!esReservaProcesable(reserva, horasAnticipacion)) {
            return;
        }
        reserva.confirmar();
        System.out.println("Reserva confirmada");
    }

    private boolean esReservaProcesable(
            Reserva reserva,
            int horasAnticipacion) {
        if (reserva == null) {
            return false;
        }
        if (reserva.getEstudiante() == null) {
            return false;
        }
        if (reserva.isCancelada()) {
            return false;
        }
        return horasAnticipacion >= HORAS_MINIMAS_CONFIRMACION;
    }
}
