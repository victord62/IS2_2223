package es.unican.is2;



import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Clase que representa un seguro de coche.
 * Un seguro se identifica por la matr�cula
 * del coche para el que se contrata el seguro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seguro")
public class Seguro {
	
	private static final double PORCENTAJE_TRAMO_1 = 0.05;
	private static final double PORCENTAJE_TRAMO_2 = 0.2;
	private static final int INICIO_TRAMO_1 = 90;
	private static final int FIN_TRAMO_1 = 110;
	private static final double DESCUENTO_PRIMER_ANHO = 0.2;
	private static final double DESCUENTO_SEGUNDO_ANHO = 0.1;
	
    
    @XmlAttribute(required = true)
    private int potencia;
    
    @XmlAttribute(required = true)
    private String matricula;
    
    @XmlAttribute(required = true)
    private Cobertura cobertura;
    
    @XmlAttribute(name="fecha", required=true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaContratacion;
    
    public Seguro(Cobertura cobertura, LocalDate fechaContratacion) throws CoberturaNoValidaExcepcion, FechaNoValidaExcepcion {
    	if (cobertura == null) {
			throw new CoberturaNoValidaExcepcion();
			
		} else {
			LocalDate fechaActual = LocalDate.now();
			if (fechaActual.compareTo(fechaContratacion) < 0) {
				throw new FechaNoValidaExcepcion();
			} else {
				this.cobertura = cobertura;
		    	this.fechaContratacion = fechaContratacion;
			}
		}
    }

	/**
	 * Retorna la matr�cula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Define el valor para la matr�cula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Define el valor para la cobertura
	 * @throws CoberturaNoValidaExcepcion 
	 */
	public void setCobertura(Cobertura cobertura) throws CoberturaNoValidaExcepcion {
		if (cobertura == null) {
			throw new CoberturaNoValidaExcepcion();
		} else {
			this.cobertura = cobertura;
		}
	}


	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Define el valor de la potencia.
     * @throws PotenciaNoValidaExcepcion 
     */
    public void setPotencia(int value) throws PotenciaNoValidaExcepcion {
        if (value <= 0) {
        	throw new PotenciaNoValidaExcepcion();
        } else {
        	this.potencia = value;
        }
    }
    
    /**
     * Retorna el precio del seguro
     * @return
     * @throws PrecioIncorrectoExcepcion 
     */
    public double precio() throws PrecioIncorrectoExcepcion {
    	// TODO
    	double PrecioCalculado = 0.0;
    	
    	// se calcula el precio base
    	if (cobertura == Cobertura.TERCEROS) {
    		PrecioCalculado = 400.0;
    	} else if (cobertura == Cobertura.TERCEROSLUNAS) {
    		PrecioCalculado = 600.0;
    	} else if (cobertura == Cobertura.TODORIESGO) {
    		PrecioCalculado = 1000.0;
    	}
    	
    	// se aplica el incremento de precio por potencia
    	if ((potencia >= INICIO_TRAMO_1) && (potencia <= FIN_TRAMO_1)) {
    		PrecioCalculado = PrecioCalculado + (PrecioCalculado * PORCENTAJE_TRAMO_1);
    	} else if (potencia > 110) {
    		PrecioCalculado = PrecioCalculado + (PrecioCalculado * PORCENTAJE_TRAMO_2);
    	}
    	
    	// se aplica el descuento por tiempo de contratacion
    	LocalDate fechaActual = LocalDate.now();
    	LocalDate fechaSeguro = fechaContratacion;
    	if (fechaActual.compareTo(fechaSeguro.plusYears(1)) < 0) {
    		PrecioCalculado = PrecioCalculado - (PrecioCalculado * DESCUENTO_PRIMER_ANHO);	
    	} else if (fechaActual.compareTo(fechaSeguro.plusYears(2)) < 0) {
    		PrecioCalculado = PrecioCalculado - (PrecioCalculado * DESCUENTO_SEGUNDO_ANHO);
    	}
    	
    	// se comprueba que el precio resultante sea valido
    	if (PrecioCalculado <= 0) {
    		throw new PrecioIncorrectoExcepcion();
    	}
    	
    	return PrecioCalculado;
    }

}



