package es.unican.is2;
import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	private String nombre;
	private String telefono;
	private String dni;
	private Direccion direccion;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
 	public Cliente(String titular, String telefono, String dni, Direccion direccion) {  
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
	}
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {
			if (c instanceof CuentaAhorro) {
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {
				for (Valor v: ((CuentaValores) c).getValores()) {
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public String getDni() {
		return dni;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	
}