package es.unican.is2;


import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Cliente")
public class Cliente {

	@XmlElement(required=true, name="seguro")
    private List<Seguro> seguros = new LinkedList<Seguro>();
    
    @XmlAttribute(required = true)
    private String nombre;
    
    @XmlAttribute(required = true)
    private String dni;
    
    @XmlAttribute(required = true)
    private boolean minusvalia;
    
    
    public Cliente(){}  

	/**
     * Retorna los seguros del cliente 
     */
    public List<Seguro> getSeguros() {
        return seguros;
    }
    
    /**
     * Define el valor de la propiedad seguros
     */
    public void setSeguros(List<Seguro> value) {
		this.seguros = value;
		
	}

    /**
     * Retorna el nombre del cliente.   
     */
    public String getNombre() {
        return nombre;
    }
    
	/**
     * Define el valor de la propiedad nombre.  
	 * @throws NombreNoValidoExcepcion 
     */
    public void setNombre(String value) throws NombreNoValidoExcepcion {
    	if (value == "" || value == null) {
    		throw new NombreNoValidoExcepcion();
    	} else {
    		this.nombre = value;
    	}
        
    }

    /**
     * Retorna el dni del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Define el valor de la propiedad dni.
     * @throws DniNoValidoExcepcion 
     */
    public void setDni(String value) throws DniNoValidoExcepcion {
    	if (value == "" || value == null) {
    		throw new DniNoValidoExcepcion();
    	} else {
    		this.dni = value;
    	}
    }
    
    /**
     * Indica si el cliente es minusv�lido
     */
    public boolean getMinusvalia() {
    	return minusvalia;
    }
    
    public void setMinusvalia(boolean minus)  {
    	minusvalia = minus;
    }
    
    /**
     * Calcula el total a pagar por el cliente por 
     * todos los seguros a su nombre
     * @throws PrecioIncorrectoExcepcion 
     */
    public double totalSeguros() throws PrecioIncorrectoExcepcion {
    	// TODO
    	double PrecioTotal = 0.0;
    	
    	// se suma el precio de todos los seguros
    	for (int i = 0; i < seguros.size(); i++) {
    		PrecioTotal = PrecioTotal + seguros.get(i).precio();
    	}
    	
    	// se aplica el descuento por minsuvalia
    	if (minusvalia == true && seguros.isEmpty() == false) {
    		PrecioTotal = PrecioTotal - (PrecioTotal * 0.25);
    	}
    	
    	// se comprueba que el precio resultante sea valido
    	if (seguros.isEmpty() == false) {
    		if (PrecioTotal <= 0) {
        		throw new PrecioIncorrectoExcepcion();
        	}
    	}
    	
    	return PrecioTotal;
    }
}
