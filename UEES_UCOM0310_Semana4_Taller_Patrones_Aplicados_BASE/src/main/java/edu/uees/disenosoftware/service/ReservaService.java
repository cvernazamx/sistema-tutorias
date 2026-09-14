package edu.uees.disenosoftware.service;

import edu.uees.disenosoftware.domain.Reserva;
import edu.uees.disenosoftware.infrastructure.*;
import edu.uees.disenosoftware.legacy.BancoAntiguo;

/**
 * CÓDIGO BASE DEL TALLER.
 *
 * Funciona, pero concentra deliberadamente cuatro fuerzas de cambio:
 * 1) proveedor de pago incompatible;
 * 2) secuencia compleja de varios subsistemas;
 * 3) políticas de descuento variables;
 * 4) múltiples receptores después de confirmar.
 *
 * NO refactorices antes de crear la línea base.
 */
public class ReservaService {

    private final InventarioService inventario = new InventarioService();
    private final ReservaRepository repositorio = new ReservaRepository();
    private final CorreoService correo = new CorreoService();
    private final AuditoriaService auditoria = new AuditoriaService();
    private final AnaliticaService analitica = new AnaliticaService();

    public void confirmar(Reserva reserva) {

        // Fuerza 1: contrato externo filtrado al dominio
        BancoAntiguo banco = new BancoAntiguo(); //adapter

        // Fuerza 2: el cliente conoce la secuencia completa //coodinacion Facade
        inventario.verificar(reserva);

        int codigo = banco.ejecutarCobro(
            String.valueOf(reserva.getTotal())
        );

        if (codigo != 0) {
            reserva.rechazar();
            return;
        }

        // Fuerza 3: políticas variables incrustadas en el mismo método // algoritmos politicas // Strategy
        if ("VIP".equals(reserva.getTipo())) {
            reserva.aplicarDescuento(reserva.getTotal() * 0.15);
        } else if ("ESTUDIANTE".equals(reserva.getTipo())) {
            reserva.aplicarDescuento(reserva.getTotal() * 0.10);
        } else if ("CAMPANIA".equals(reserva.getTipo())) {
            reserva.aplicarDescuento(reserva.getTotal() * 0.20);
        }

        repositorio.guardar(reserva);
        reserva.confirmar();

        // Fuerza 4: el emisor conoce cada receptor concreto // cepcetores // observer
        correo.enviar(reserva);
        auditoria.registrar(reserva);
        analitica.registrarEvento(reserva);
    }
}
