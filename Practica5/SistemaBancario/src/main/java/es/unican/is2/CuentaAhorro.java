package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 7
// WMC resultante: 7
// WMCn resultante: 7 / 11 = 0,363  
public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	// CCog metodo: 0
	// CC metodo: 0
	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws datoErroneoException {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	// CCog metodo: 1
	// CC metodo: 1
	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0)														// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.mMovimientos.add(m);
	}

	// CCog metodo: 2
	// CC metodo: 2
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x <= 0)														// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)												// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	// CCog metodo: 1
	// CC metodo: 1
	public void ingresar(String concepto, double x) throws datoErroneoException {
		if (x <= 0)														// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.mMovimientos.add(m);
	}

	// CCog metodo: 2
	// CC metodo: 2
	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x)												// CCog: +1		// CC: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)														// CCog: +1		// CC: +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	// CCog metodo: 1
	// CC metodo: 1
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {			// CCog: +1		// CC: +1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	// CCog metodo: 0
	// CC metodo: 0
	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public LocalDate getCaducidadDebito() {
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
	}
	
	// CCog metodo: 0
	// CC metodo: 0
	public double getLimiteDebito() {
		return limiteDebito;
	}

}