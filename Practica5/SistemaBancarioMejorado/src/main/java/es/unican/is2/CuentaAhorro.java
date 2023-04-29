package es.unican.is2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	public final double LIMITE_DEBITO = 1000;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws datoErroneoException {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double importe) throws datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		}
		
		registrarMovimiento("Ingreso en efectivo", importe);
	}

	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		if (getSaldo() < importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
			
		registrarMovimiento("Retirada de efectivo", -importe);
	}

	public void ingresar(String concepto, double importe) throws datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		}
		
		registrarMovimiento(concepto, importe);
	}

	public void retirar(String concepto, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		
		if (importe <= 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}
		
		registrarMovimiento(concepto, -importe);
	}

	public double getSaldo() {
		double gasto = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.get(i);
			gasto += m.getImporte();
		}
		return gasto;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() {
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
	}
	
	private void registrarMovimiento(String concepto, double importe) {
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(importe);
		this.mMovimientos.add(m);
	}

}