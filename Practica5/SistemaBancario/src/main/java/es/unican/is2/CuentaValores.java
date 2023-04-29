package es.unican.is2;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 3
// WMC resultante: 5
// WMCn resultante: 5 / 3 = 1,666
public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	// CCog metodo: 0
	// CC metodo: 1
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public List<Valor> getValores() {
		return valores;
	}
	
	// CCog metodo: 3
	// CC metodo: 3
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) {										// CCog: +1		// CC: +1
			if (v.getEntidad().equals(valor.getEntidad()))			// CCog: +2		// CC: +1
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
