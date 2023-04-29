package es.unican.is2;

public class Direccion {
	
	private String calle;
	private String zip;
	private String localidad;
	
	public Direccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public String getZip() {
		return zip;
	}

	public String getLocalidad() {
		return localidad;
	}

}