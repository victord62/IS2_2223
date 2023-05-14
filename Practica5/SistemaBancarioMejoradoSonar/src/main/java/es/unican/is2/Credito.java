package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 10
// WMC resultante: 18
// WMCn resultante: 18 / 10 = 1,8
public class Credito extends Tarjeta {
	
	private static final double COMISION = 0.05;
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	// CCog metodo: 0
	// CC metodo: 1
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	// CCog metodo: 3
	// CC metodo: 3
	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double importe) throws SaldoInsuficienteException, DatoErroneoException {
		if (importe < 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getGastosAcumulados() + importe > mCredito)								// CCog: +1		// CC: +1
			throw new SaldoInsuficienteException("Cr�dito insuficiente");
		else {																		// CCog: +1
			registrarMovimiento(null, importe);
		}
	}

	// CCog metodo: 2
	// CC metodo: 3
	@Override
	public void pagoEnEstablecimiento(String establecimiento, double importe) throws SaldoInsuficienteException, DatoErroneoException {
		if (importe < 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getGastosAcumulados() + importe > mCredito) {							// CCog: +1		// CC: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		
		registrarMovimiento(establecimiento, importe);
	}
	
	// CCog metodo: 1
	// CC metodo: 2
    public double getGastosAcumulados() {
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {				// CCog: +1		// CC: +1
			Movimiento m = mMovimientosMensuales.get(i);
			gasto += m.getImporte();
		}
		return -gasto;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	// CCog metodo: 2
	// CC metodo: 3
	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidaci�n de operaciones tarjeta cr�dito");
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {				// CCog: +1		// CC: +1
			Movimiento m = mMovimientosMensuales.get(i);
			gasto += m.getImporte();
		}
		liq.setImporte(gasto);
		
		if (gasto != 0)																// CCog: +1		// CC: +1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	// CCog metodo: 0
	// CC metodo: 1
	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}
	
	// CCog metodo: 2
	// CC metodo: 2
	private void registrarMovimiento(String establecimiento, double importe) {
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		
		if (establecimiento == null) {												// CCog: +1		// CC: +1
			m.setConcepto("Retirada en cajero autom�tico");
			importe += importe * COMISION;
		} else {																	// CCog: +1
			m.setConcepto("Compra a cr�dito en: " + establecimiento);
		}
		
		m.setImporte(-importe);
		mMovimientosMensuales.add(m);
	}

}