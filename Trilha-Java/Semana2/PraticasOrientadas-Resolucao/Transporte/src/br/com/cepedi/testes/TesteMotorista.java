package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.pessoa.Motorista;

class TesteMotorista {

	@Test
	void test() {
		testArmazenaMotorista();
		testCNHInvalida();
		testCNHNula();
	}

	public void testArmazenaMotorista() {
		
		Motorista motorista=null;
		
		try {
			motorista = new Motorista("Caio","098.609.325-49","1200","12345678901");			
		}catch(Exception e ) {
			fail("Não deve entrar em exception");
		}
		
		assertNotNull(motorista);
				
	}
	
	public void testCNHInvalida() {
		Motorista motorista=null;
		
		try {
			motorista = new Motorista("Caio","098.609.325-49","1200","123478901");			
		}catch(Exception e ) {
			assertEquals("CNH inválida",e.getMessage());
		}
		
		assertNull(motorista);
	}
	
	public void testCNHNula() {
		Motorista motorista=null;
		
		try {
			motorista = new Motorista("Caio","098.609.325-49","1200",null);			
		}catch(Exception e ) {
			assertEquals("CNH inválida",e.getMessage());
		}
		
		assertNull(motorista);
	}
	
	
	
}
