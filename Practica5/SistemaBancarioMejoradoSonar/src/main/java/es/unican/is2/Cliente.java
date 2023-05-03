package es.unican.is2;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 7
// WMC resultante: 11
// WMCn resultante: 11 / 7  = 1,571
public class Cliente {
	
	private String nombre;
	private String telefono;
	private String dni;
	private Direccion direccion;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
	// CCog metodo: 0
	// CC metodo: 1
 	public Cliente(String titular, String telefono, String dni, Direccion direccion) {  
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	// CCog metodo: 7
	// CC metodo: 5
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {													// CCog: +1		// CC: +1
			if (c instanceof CuentaAhorro) {										// CCog: +2		// CC: +1
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {								// CCog: +1		// CC: +1
				for (Valor v: ((CuentaValores) c).getValores()) {					// CCog: +3		// CC: +1
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public String getNombre() {
		return nombre;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public String getTelefono() {
		return telefono;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public String getDni() {
		return dni;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public Direccion getDireccion() {
		return direccion;
	}
	
}