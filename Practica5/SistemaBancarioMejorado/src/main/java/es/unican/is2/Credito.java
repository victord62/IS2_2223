package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private static final double COMISION = 0.05;
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe < 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getGastosAcumulados() + importe > mCredito)
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		else {
			registrarMovimiento(null, importe);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String establecimiento, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe < 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getGastosAcumulados() + importe > mCredito) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		
		registrarMovimiento(establecimiento, importe);
	}
	
    public double getGastosAcumulados() {
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			gasto += m.getImporte();
		}
		return -gasto;
	}
	
	
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidaci�n de operaciones tarjeta cr�dito");
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			gasto += m.getImporte();
		}
		liq.setImporte(gasto);
	
		if (gasto != 0)
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}
	
	private void registrarMovimiento(String establecimiento, double importe) {
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		
		if (establecimiento == null) {
			m.setConcepto("Retirada en cajero autom�tico");
			importe += importe * COMISION;
		} else {
			m.setConcepto("Compra a cr�dito en: " + establecimiento);
		}
		
		m.setImporte(-importe);
		mMovimientosMensuales.add(m);
	}

}