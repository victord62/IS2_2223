package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VistaAgenteTest {
	private FrameFixture demo;
	private IGestionClientes clientes;
	private IGestionSeguros seguros;
	private IInfoSeguros info;
	private IClientesDAO clientesDao;
	private ISegurosDAO segurosDao;


	@BeforeEach
	public void setUp() {
		clientesDao = new ClientesDAO();
		segurosDao = new SegurosDAO();
		seguros = new GestionSeguros(clientesDao, segurosDao);
		VistaAgente gui = new VistaAgente(clientes, seguros, info);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnBuscar").requireText("Buscar");
		
		
		//  Prueba de busqueda con un DNI registrado 
		// Escribimos un DNI
		demo.textBox("txtDNICliente").enterText("61276767Y");
		// Pulsamos el botón
		demo.button("btnBuscar").click();		
		// Comprobamos la salida
		
		Cliente c = clientesDao.cliente("61276767Y");
		demo.textBox("txtNombreCliente").requireText(c.getNombre());
		for (Seguro v:c.getSeguros()) {
			assertEquals(demo.list("listSeguros").contents()
			,v.getMatricula() + " "+v.getCobertura());
			


		}
		demo.textBox("txtToalCliente").requireText(Double.toString(c.totalSeguros()));
		
		
	    //Prueba de busqueda con un DNI no registrado
		demo.textBox("txtDNICliente").setText("");
		demo.button("btnPulsar").click();
		demo.textBox("txtTotalCliente").requireText("");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
