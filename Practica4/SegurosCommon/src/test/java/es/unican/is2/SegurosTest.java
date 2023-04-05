package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SegurosTest {

	private static final Cobertura TODORIESGO = null;
	private static final Cobertura TERCEROS = null;
	private static final Cobertura TERCEROSLUNAS = null;

	@Test
	void testSeguro() {
		Double valor=0.0;
		
		// caso 1
		Seguro seguro = new Seguro();
		seguro.setCobertura(TERCEROS);
		seguro.setPotencia(60);
		
		try {
			assertEquals(320.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		// caso 2
		seguro.setCobertura(TODORIESGO);
		seguro.setPotencia(60);
		
		try {
			assertEquals(480.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		// caso 3
		seguro.setCobertura(TERCEROSLUNAS);
		seguro.setPotencia(60);
		
		try {
			assertEquals(800.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		// caso 4
		seguro.setCobertura(TERCEROSLUNAS);
		seguro.setPotencia(100);
		
		try {
			assertEquals(420.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		// caso 5
		seguro.setCobertura(TERCEROSLUNAS);
		seguro.setPotencia(120);
		
		try {
			assertEquals(1200.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		// caso 6
		seguro.setCobertura(TERCEROSLUNAS);
		seguro.setPotencia(120);
		
		try {
			assertEquals(1200.0, seguro.precio());
		} catch (PrecioIncorrectoException e) {
			fail();
		}
		
		
		
	}

}
