package es.unican.is2;


// CCog resultante: 1
// WMC resultante: 7
// WMCn resultante: 7 / 7 = 1,0 
/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	// CCog metodo: 0
	// CC metodo: 1
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public int getNumValores() {
		return numAcciones;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public double getCotizacion() {
		return cotizacion;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public String getEntidad() {
		return entidad;
	}

	// CCog metodo: 1
	// CC metodo: 1
	@Override
	public boolean equals(Object obj) {
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) & numAcciones==other.numAcciones);			// CCog: +1
	}
	
}