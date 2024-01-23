package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.pessoa.Cobrador;

class TesteFuncionarioANDCobrador {

	@Test
	void test() {
		testArmazenaCobrador();
		testSalarioNegativo();
		testSalarioFormatoInvalido();
	}
	
	public void testArmazenaCobrador() {
		
		Cobrador cobrador=null;
		
		try {
			cobrador = new Cobrador("Caio","098.609.325-49","1200");			
		}catch(Exception e ) {
			fail("Não deve entrar em exception");
		}
		
		assertNotNull(cobrador);
				
	}
	
	public void testSalarioNegativo() {
		Cobrador cobrador=null;
		
		try {
			cobrador = new Cobrador("Caio","098.609.325-49","-2");			
		}catch(Exception e ) {
			assertEquals("Salário inválido. Deve ser maior do que R$ 0.0",e.getMessage());
		}
		
		assertNull(cobrador);
	}
	
	public void testSalarioFormatoInvalido() {
		Cobrador cobrador=null;
		
		try {
			cobrador = new Cobrador("Caio","098.609.325-49","A");			
		}catch(Exception e ) {
			assertEquals("Formato inválido para o salário",e.getMessage());
		}
		
		assertNull(cobrador);
	}

}




