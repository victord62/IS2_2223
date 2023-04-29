package es.unican.is2;
import java.time.LocalDate;


// CCog resultante: 2
// WMC resultante: 8
// WMCn resultante: 8 / 6 =  1,333
public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	// CCog metodo: 0
	// CC metodo: 1
	public Debito(String numero, String titular, CuentaAhorro c) {
		super(numero, titular, c);
	}
	
	// CCog metodo: 1
	// CC metodo: 2
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {							// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero autom�tico", x);
		saldoDiarioDisponible-=x;
	}
	
	// CCog metodo: 1
	// CC metodo: 2
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {							// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadDebito() {
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * M�todo invocado autom�ticamente a las 00:00 de cada d�a
	 */
	// CCog metodo: 0
	// CC metodo: 1
	public void restableceSaldo() {
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}

}