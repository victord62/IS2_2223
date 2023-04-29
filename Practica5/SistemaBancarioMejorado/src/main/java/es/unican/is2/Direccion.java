package es.unican.is2;


// CCog resultante: 0
// WMC resultante: 4
// WMCn resultante: 4 / 4 = 1,0
public class Direccion {
	
	private String calle;
	private String zip;
	private String localidad;
	
	// CCog metodo: 0
	// CC metodo: 1
	public Direccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public String getCalle() {
		return calle;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public String getZip() {
		return zip;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public String getLocalidad() {
		return localidad;
	}

}