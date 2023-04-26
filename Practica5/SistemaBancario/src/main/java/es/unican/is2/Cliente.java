package es.unican.is2;
import java.util.LinkedList;
import java.util.List;


// CCog resultante: 7
// WMC resultante: 4
// WMCn resultante: 4 / 10 = 0,4 
public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

	// CCog metodo: 0
	// CC metodo: 0
 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	// CCog metodo: 0
	// CC metodo: 0
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	// CCog metodo: 0
	// CC metodo: 0
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	// CCog metodo: 7
	// CC metodo: 4
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {										// CCog: +1		// CC: +1
			if (c instanceof CuentaAhorro) {							// CCog: +2		// CC: +1
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {					// CCog: +1		// CC: +1
				for (Valor v: ((CuentaValores) c).getValores()) {		// CCog: +3		// CC: +1
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	// CCog metodo: 0
	// CC metodo: 0
	public String getNombre() {
		return nombre;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public String getCalle() {
		return calle;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public String getZip() {
		return zip;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public String getLocalidad() {
		return localidad;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public String getTelefono() {
		return telefono;
	}

	// CCog metodo: 0
	// CC metodo: 0
	public String getDni() {
		return dni;
	}	
}