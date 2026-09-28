package edu.uees.disenosoftware.payment;

import edu.uees.disenosoftware.legacy.BancoAntiguo;

public class BancoAntiguoAdapter implements ProcesadorPago {

	private final BancoAntiguo banco;
		
	
	public BancoAntiguoAdapter(BancoAntiguo banco) {
		this.banco=banco;
	}
	
	@Override
	public boolean pagar(double total) {
		int codigo = banco.ejecutarCobro(String.valueOf(total));
	return codigo ==0;
	}

}
