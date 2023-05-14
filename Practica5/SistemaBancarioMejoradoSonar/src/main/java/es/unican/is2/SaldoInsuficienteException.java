package es.unican.is2;
@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
