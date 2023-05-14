package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 7
// WMC resultante: 18
// WMCn resultante: 18 / 11 = 1,636
public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	public static final double LIMITE_DEBITO = 1000;

	// CCog metodo: 0
	// CC metodo: 1
	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws DatoErroneoException {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
	}

	// CCog metodo: 1
	// CC metodo: 2
	public void ingresar(double importe) throws DatoErroneoException {
		if (importe <= 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
		}
		
		registrarMovimiento("Ingreso en efectivo", importe);
	}

	// CCog metodo: 2
	// CC metodo: 3
	public void retirar(double importe) throws SaldoInsuficienteException, DatoErroneoException {
		if (importe <= 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getSaldo() < importe) {													// CCog: +1		// CC: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
			
		registrarMovimiento("Retirada de efectivo", -importe);
	}

	// CCog metodo: 1 
	// CC metodo: 2
	public void ingresar(String concepto, double importe) throws DatoErroneoException {
		if (importe <= 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
		}
		
		registrarMovimiento(concepto, importe);
	}

	// CCog metodo: 2
	// CC metodo: 3
	public void retirar(String concepto, double importe) throws SaldoInsuficienteException, DatoErroneoException {
		if (getSaldo() < importe) {													// CCog: +1		// CC: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		
		if (importe <= 0) {															// CCog: +1		// CC: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		registrarMovimiento(concepto, -importe);
	}

	// CCog metodo: 1
	// CC metodo: 2
	public double getSaldo() {
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {						// CCog: +1		// CC: +1
			Movimiento m = mMovimientos.get(i);
			gasto += m.getImporte();
		}
		return gasto;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	// CCog metodo: 0
	// CC metodo: 1
	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadDebito() {
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	private void registrarMovimiento(String concepto, double importe) {
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(importe);
		this.mMovimientos.add(m);
	}

}