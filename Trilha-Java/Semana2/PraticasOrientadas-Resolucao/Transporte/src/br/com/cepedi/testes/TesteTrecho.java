package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trecho;

class TesteTrecho {

	@Test
	void test() {
		InstanciaTrecho();
		instanciaTrechoP1Nulo();
		instanciaTrechoP2Nulo();
		instanciaTrechoTempoNegativo();
		instanciaTrechoTempoZero();
	}

	public void InstanciaTrecho() {
		Trecho trecho = null;
		try {
			PontoDeParada p1 = new PontoDeParada("Ferradas");
			PontoDeParada p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,40);
		}catch(Exception e ) {
			fail("Não deve dar erro aqui");
		}
		
		assertNotNull(trecho);
	}
	
	public void instanciaTrechoP1Nulo() {
		Trecho trecho = null;
		try {
			PontoDeParada p2 = new PontoDeParada("UESC");
			trecho = new Trecho(null,p2,40);
		}catch(Exception e ){
			assertEquals("Foi inserido um ponto nulo",e.getMessage());
		}
		
		assertNull(trecho);
	}
	
	public void instanciaTrechoP2Nulo() {
		Trecho trecho = null;
		try {
			PontoDeParada p1 = new PontoDeParada("Ferradas");
			trecho = new Trecho(p1,null,40);
		}catch(Exception e ) {
			assertEquals("Foi inserido um ponto nulo",e.getMessage());
		}
		
		assertNull(trecho);
	}
	
	public void instanciaTrechoTempoNegativo() {
		Trecho trecho = null;
		try {
			PontoDeParada p1 = new PontoDeParada("Ferradas");
			PontoDeParada p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,-2);
		}catch(Exception e ) {
			assertEquals("O tempo em minutos de um trecho não pode ser negativo",e.getMessage());
		}
		
		assertNull(trecho);
	}
	
	public void instanciaTrechoTempoZero() {
		Trecho trecho = null;
		try {
			PontoDeParada p1 = new PontoDeParada("Ferradas");
			PontoDeParada p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,0);
		}catch(Exception e ) {
			assertEquals("O tempo em minutos de um trecho deve ser de no mínimo 1",e.getMessage());
		}
		
		assertNull(trecho);
	}
	

}
