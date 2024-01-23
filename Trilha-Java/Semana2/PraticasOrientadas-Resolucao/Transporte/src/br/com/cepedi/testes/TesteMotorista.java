package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;

class TesteMotorista {

	@Test
	void test() {
		testArmazenaMotorista();
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
	
}
