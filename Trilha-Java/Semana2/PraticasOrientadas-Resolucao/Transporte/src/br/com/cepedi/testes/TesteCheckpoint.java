package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.transporte.Checkpoint;
import br.com.cepedi.model.transporte.PontoDeParada;

class TesteCheckpoint {

	@Test
	void test() {
		testInstanciaCheckpoint();
		testInstanciaCheckpointNulo();
		testInsereLocalDateTimeNull();
	}
	
	public void testInstanciaCheckpoint() {
		try {
			PontoDeParada p1 = new PontoDeParada("Ferradas");
			Checkpoint c1 = new Checkpoint(p1);	
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("Não deve cair aqui");
		}
		
	}
	
	public void testInstanciaCheckpointNulo() {
		PontoDeParada p1;
		Checkpoint c1 = null;
		try {
			p1 = new PontoDeParada("Ferradas");
			c1 = new Checkpoint(null);	
		}catch(Exception e ) {
			assertEquals("Foi inserido um ponto nulo",e.getMessage());
		}
		
		assertNull(c1);
		
	}
	
	public void testInsereLocalDateTimeNull() {
		PontoDeParada p1;
		Checkpoint c1 = null;
		try {
			p1 = new PontoDeParada("Ferradas");
			c1 = new Checkpoint(p1);	
			c1.setHoraChegada(null);
		}catch(Exception e ) {
			assertEquals("Foi inserido uma hora nula",e.getMessage());
		}
		
		assertNull(c1.getHoraChegada());
		
	}

}
