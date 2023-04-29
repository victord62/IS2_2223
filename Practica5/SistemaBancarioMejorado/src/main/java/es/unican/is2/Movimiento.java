package es.unican.is2;
import java.time.LocalDateTime;


// CCog resultante: 0
// WMC resultante: 6
// WMCn resultante: 6 / 6 = 1,0
public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	// CCog metodo: 0
	// CC metodo: 1
	public double getImporte() {
		return mImporte;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public void setImporte(double newMImporte) {
		mImporte = newMImporte;
	}
	
	// CCog metodo: 0
	// CC metodo: 1
	public String getConcepto() {
		return mConcepto;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public void setConcepto(String newMConcepto) {
		mConcepto = newMConcepto;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public LocalDateTime getFecha() {
		return mFecha;
	}

	// CCog metodo: 0
	// CC metodo: 1
	public void setFecha(LocalDateTime newMFecha) {
		mFecha = newMFecha;
	}
	
}