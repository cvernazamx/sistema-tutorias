package edu.uees.disenosoftware.legacy;

/**
 * API de un proveedor legado.
 * 0 = aprobado; cualquier otro valor = rechazado.
 */
public class BancoAntiguo {

    public int ejecutarCobro(String monto) {
        System.out.println("[BANCO LEGADO] cobrando $" + monto);
        return 0;
    }
}
