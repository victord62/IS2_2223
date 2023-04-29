package es.unican.is2;


// CCog resultante: 0
// WMC resultante: 3
// WMCn resultante: 3 / 3 = 1,0
public abstract class Tarjeta {
	
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;

	// CCog metodo: 0
	// CC metodo: 1
	public Tarjeta(String numero, String titular, CuentaAhorro c) {
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// CCog metodo: 0
	// CC metodo: 1
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// CCog metodo: 0
	// CC metodo: 1
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;
	
}