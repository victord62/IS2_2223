package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class ClientesTest {
	
	@Test
	public void testClientes() throws CoberturaNoValidaExcepcion, FechaNoValidaExcepcion, PotenciaNoValidaExcepcion, NombreNoValidoExcepcion, DniNoValidoExcepcion  {
		
		// caso 1
		List<Seguro> seguros = new ArrayList<Seguro>();
		Seguro seguro1 = new Seguro(Cobertura.TODORIESGO, LocalDate.now().minusDays(10));
		seguro1.setPotencia(60);
		seguros.add(seguro1);
		
		Cliente cliente = new Cliente();
		cliente.setMinusvalia(true);
		cliente.setSeguros(seguros);
		try {
			assertEquals(600.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 2
		cliente.setMinusvalia(false);
		try {
			assertEquals(800.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 3
		Seguro seguro2 = new Seguro(Cobertura.TERCEROS, LocalDate.now().minusDays(10));
		seguro2.setPotencia(100);
		seguros.add(seguro2);
		
		cliente.setMinusvalia(true);
		try {
			assertEquals(852.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 4
		cliente.setMinusvalia(false);
		try {
			assertEquals(1136.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 5
		Seguro seguro3 = new Seguro(Cobertura.TERCEROSLUNAS, LocalDate.now().minusDays(10));
		seguro3.setPotencia(140);
		seguros.add(seguro3);
		
		cliente.setMinusvalia(true);
		try {
			assertEquals(1284.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 6
		cliente.setMinusvalia(false);
		try {
			assertEquals(1712.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 7
		seguros.clear();
		cliente.setMinusvalia(true);
		try {
			assertEquals(0.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 8
		cliente.setMinusvalia(false);
		try {
			assertEquals(0.0, cliente.totalSeguros());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 9
		seguros.add(seguro1);
		cliente.setSeguros(seguros);
		assertEquals(seguros, cliente.getSeguros());
		
		
		// caso 10
		seguros.add(seguro2);
		cliente.setSeguros(seguros);
		assertEquals(seguros, cliente.getSeguros());
		
		
		// caso 11
		seguros.add(seguro3);
		cliente.setSeguros(seguros);
		assertEquals(seguros, cliente.getSeguros());
		
		
		// caso 12
		seguros.clear();
		cliente.setSeguros(seguros);
		assertEquals(seguros, cliente.getSeguros());
		
		
		// caso 13
		cliente.setNombre("Paco");
		assertEquals("Paco", cliente.getNombre());
		
		
		// caso 14
		assertThrows(NombreNoValidoExcepcion.class, () -> cliente.setNombre(""));
		
		
		// caso 15
		assertThrows(NombreNoValidoExcepcion.class, () -> cliente.setNombre(null));
		
		
		// caso 16
		cliente.setDni("12345");
		assertEquals("12345", cliente.getDni());
		
		
		// caso 17
		assertThrows(DniNoValidoExcepcion.class, () -> cliente.setDni(""));
		
		
		// caso 18
		assertThrows(DniNoValidoExcepcion.class, () -> cliente.setDni(null));
		
		
		// caso 19
		cliente.setMinusvalia(false);
		assertEquals(false, cliente.getMinusvalia());
		
		
		// caso 20
		cliente.setMinusvalia(true);
		assertEquals(true, cliente.getMinusvalia());
	}
}
