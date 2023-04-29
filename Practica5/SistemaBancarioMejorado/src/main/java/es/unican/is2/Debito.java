package es.unican.is2;
import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) {
		super(numero, titular, c);
	}
	
	@Override
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero autom�tico", importe);
		saldoDiarioDisponible-=importe;
	}
	
	@Override
	public void pagoEnEstablecimiento(String establecimiento, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + establecimiento, importe);
		saldoDiarioDisponible-=importe;
	}
	
	public LocalDate getCaducidadDebito() {
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * M�todo invocado autom�ticamente a las 00:00 de cada d�a
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = mCuentaAsociada.LIMITE_DEBITO;
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}

}