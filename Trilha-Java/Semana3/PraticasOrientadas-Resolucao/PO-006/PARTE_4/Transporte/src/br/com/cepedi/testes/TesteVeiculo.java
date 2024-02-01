package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.veiculo.Veiculo;

class TesteVeiculo {

	@Test
	void test() {
		testInstanciaVeiculo();
		testNomeModeloIncorreto();
		testPlacaIncorreta();
		testMontadoraIncorreta();
		testGeracaoIDIncrimental();
	}
	
	public void testInstanciaVeiculo() {
		try {
			Veiculo veiculo = new Veiculo("Mobi","GFD-4578","Fiat");
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("erro");
		}
	}
	
	public void testNomeModeloIncorreto(){
		Veiculo veiculo = null;
		try {
			veiculo = new Veiculo("*Mb","GFD-4578","Fiat");
		}catch(Exception e ) {
			assertEquals("Modelo inválido. Deve possuir apenas caracteres alfanumericos e espaços",e.getMessage());
		}
		
		assertNull(veiculo);
	}
	
	public void testPlacaIncorreta() {
		Veiculo veiculo = null;
		try {
			veiculo = new Veiculo("Mobi","GFD-458","Fiat");
		}catch(Exception e ) {
			assertEquals("Placa invalida de veículo, formato correto : XXX-1111",e.getMessage());
		}
		
		assertNull(veiculo);
	}
	
	public void testMontadoraIncorreta() {
		Veiculo veiculo = null;
		try {
			veiculo = new Veiculo("Mobi","GFD-4578","Fiat5");
		}catch(Exception e ) {
			assertEquals("O nome da montadora deve possuir apenas letras e espaços",e.getMessage());
		}
		
		assertNull(veiculo);
	}
	
	public void testGeracaoIDIncrimental() {
		Veiculo v1 = null;
		Veiculo v2 = null;
		Veiculo v3 = null;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("UNO","GRT-4578","Fia6t");
		}catch(Exception e ) {
			assertEquals("O nome da montadora deve possuir apenas letras e espaços",e.getMessage());
		}
		
		try {
			v3 = new Veiculo("UNO","GRT-4579","Fiat");
		}catch(Exception e ) {
			fail("Não deve cair");
		}
		
		assertEquals(v3.getId(),v1.getId()+1);

		
	}

}
