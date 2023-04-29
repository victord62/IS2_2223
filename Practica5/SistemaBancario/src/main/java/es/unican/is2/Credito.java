package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 8
// WMC resultante: 16
// WMCn resultante: 16 / 9 =  1,777
public class Credito extends Tarjeta {
	
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

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	// CCog metodo: 3
	// CC metodo: 3
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)																// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero autom�tico");
		x += x * 0.05; // A�adimos una comisi�n de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito)									// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		else {																	// CCog: +1
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	// CCog metodo: 2
	// CC metodo: 3
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)																// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito)								// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a cr�dito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}
	
	// CCog metodo: 1
	// CC metodo: 2
    public double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {			// CCog: +1		// CC: +1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	// CCog metodo: 2
	// CC metodo: 3
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidaci�n de operaciones tarjeta cr�dito");
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {			// CCog: +1		// CC: +1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		if (r != 0)																// CCog: +1		// CC: +1
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

}