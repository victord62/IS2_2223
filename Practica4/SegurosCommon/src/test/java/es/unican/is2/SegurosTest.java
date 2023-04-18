package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;


class SegurosTest {
	
	@Test
	public void testSeguro() throws PotenciaNoValidaExcepcion, CoberturaNoValidaExcepcion, FechaNoValidaExcepcion {
		
		// caso 1
		Seguro seguroA = new Seguro(Cobertura.TERCEROS, LocalDate.now().minusDays(10));
		seguroA.setCobertura(Cobertura.TERCEROS);
		seguroA.setPotencia(60);
		
		try {
			assertEquals(320.0, seguroA.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 2
		seguroA.setCobertura(Cobertura.TERCEROSLUNAS);
		seguroA.setPotencia(89);
		
		try {
			assertEquals(480.0, seguroA.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 3
		seguroA.setCobertura(Cobertura.TODORIESGO);
		seguroA.setPotencia(89);
		
		try {
			assertEquals(800.0, seguroA.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 4
		seguroA.setCobertura(Cobertura.TERCEROS);
		seguroA.setPotencia(90);
		
		try {
			assertEquals(336.0, seguroA.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 5
		seguroA.setCobertura(Cobertura.TERCEROSLUNAS);
		seguroA.setPotencia(100);
		
		try {
			assertEquals(504.0, seguroA.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 6
		Seguro seguroB = new Seguro(Cobertura.TODORIESGO, LocalDate.now().minusDays(364));
		seguroB.setCobertura(Cobertura.TODORIESGO);
		seguroB.setPotencia(110);
		
		try {
			assertEquals(840.0, seguroB.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 7
		seguroB.setCobertura(Cobertura.TODORIESGO);
		seguroB.setPotencia(111);
		
		try {
			assertEquals(960.0, seguroB.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 8
		Seguro seguroC = new Seguro(Cobertura.TODORIESGO, LocalDate.now().minusDays(366));
		seguroC.setCobertura(Cobertura.TODORIESGO);
		seguroC.setPotencia(111);
		
		try {
			assertEquals(1080.0, seguroC.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 9
		Seguro seguroD = new Seguro(Cobertura.TERCEROSLUNAS, LocalDate.now().minusDays(450));
		seguroD.setCobertura(Cobertura.TERCEROSLUNAS);
		seguroD.setPotencia(140);
		
		try {
			assertEquals(648.0, seguroD.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 10
		Seguro seguroE = new Seguro(Cobertura.TERCEROS, LocalDate.now().minusDays(729));
		seguroE.setCobertura(Cobertura.TERCEROS);
		seguroE.setPotencia(140);
		
		try {
			assertEquals(432.0, seguroE.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 11
		Seguro seguroF = new Seguro(Cobertura.TERCEROSLUNAS, LocalDate.now().minusDays(732));
		seguroF.setCobertura(Cobertura.TERCEROSLUNAS);
		seguroF.setPotencia(140);
		
		try {
			assertEquals(720.0, seguroF.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 12
		Seguro seguroG = new Seguro(Cobertura.TODORIESGO, LocalDate.now().minusDays(1200));
		seguroG.setCobertura(Cobertura.TODORIESGO);
		seguroG.setPotencia(600);
		
		try {
			assertEquals(1200.0, seguroG.precio());
		} catch (PrecioIncorrectoExcepcion e) {
			fail();
		}
		
		
		// caso 13
		Seguro seguroH = new Seguro(Cobertura.TERCEROS, LocalDate.now().minusDays(10));
		seguroH.setCobertura(Cobertura.TERCEROS);
		assertThrows(PotenciaNoValidaExcepcion.class, () -> seguroH.setPotencia(0));
		
		
		// caso 14
		seguroH.setCobertura(Cobertura.TODORIESGO);
		assertThrows(PotenciaNoValidaExcepcion.class, () -> seguroH.setPotencia(-1));
		
		
		// caso 15
		Seguro seguroI = new Seguro(Cobertura.TERCEROS, LocalDate.now().minusDays(10));
		seguroI.setPotencia(2);
		assertThrows(CoberturaNoValidaExcepcion.class, () -> seguroI.setCobertura(null));
		assertThrows(CoberturaNoValidaExcepcion.class, () -> new Seguro(null, LocalDate.now().minusDays(10)));
		
		
		// caso 16
		assertThrows(FechaNoValidaExcepcion.class, () -> new Seguro(Cobertura.TERCEROS, LocalDate.now().plusDays(3)));

		
		// caso 17
		seguroI.setPotencia(1);
		assertEquals(1.0, seguroI.getPotencia());
		
		
		// caso 18
		seguroI.setPotencia(80);
		assertEquals(80.0, seguroI.getPotencia());
		
		
		// caso 19
		seguroI.setCobertura(Cobertura.TERCEROS);
		assertEquals(Cobertura.TERCEROS, seguroI.getCobertura());
		
		
		// caso 20
		seguroI.setCobertura(Cobertura.TODORIESGO);
		assertEquals(Cobertura.TODORIESGO, seguroI.getCobertura());
		
		
		// caso 21
		seguroI.setCobertura(Cobertura.TERCEROSLUNAS);
		assertEquals(Cobertura.TERCEROSLUNAS, seguroI.getCobertura());		
	}
}
