package es.unican.is2;
import java.time.LocalDate;


// CCog resultante: 2
// WMC resultante: 8
// WMCn resultante: 8 / 6 = 1,333
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
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importe) {										// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero autom�tico", importe);
		saldoDiarioDisponible-=importe;
	}
	
	// CCog metodo: 1
	// CC metodo: 2
	@Override
	public void pagoEnEstablecimiento(String establecimiento, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importe) {										// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + establecimiento, importe);
		saldoDiarioDisponible-=importe;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadDebito() {
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	/**
	 * M�todo invocado autom�ticamente a las 00:00 de cada d�a
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = mCuentaAsociada.LIMITE_DEBITO;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}

}