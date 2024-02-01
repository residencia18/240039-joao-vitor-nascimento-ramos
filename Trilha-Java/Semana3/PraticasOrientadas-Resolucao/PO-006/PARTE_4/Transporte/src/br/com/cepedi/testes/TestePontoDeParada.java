package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.transporte.PontoDeParada;

class TestePontoDeParada {

	@Test
	void test() {
		InstanciarPontoParada();
		inserirNomeNulo();
	}
	
	public void InstanciarPontoParada() {
		
		PontoDeParada p1 = null;
		try {
			p1 = new PontoDeParada("Ferradas 4");
		}catch(Exception e ) {
			fail("Não deve cair aqui");
		}
		
		assertNotNull(p1);
	}
	
	public void inserirNomeNulo() {
		PontoDeParada p1 = null;
		try {
			p1 = new PontoDeParada(null);
		}catch(Exception e ) {
			assertEquals("Foi inserido um nome nulo",e.getMessage());
		}
		
		assertNull(p1);	
	}
	
	

}
