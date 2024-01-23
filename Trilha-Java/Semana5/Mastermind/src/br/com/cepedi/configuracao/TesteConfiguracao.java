package br.com.cepedi.configuracao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TesteConfiguracao {

	@Test
	void test() {
		Configuracao config = new Configuracao();
		//Teste alfabeto
		testCasoGeralAlfabeto(config);
		testAlfabetoNulo(config);
		testAlfabeto1Caracter(config);
		testCaracteresIguais(config);
		
		//Teste Nome
		testCasoGeralNome(config);
		testNomeVerificaDigito(config);
		testNomeVerificaCaracterEspecial(config);
		testNomeNulo(config);
		testTamanhoNome(config);
		testEntradaValidaNome(config);
		
		//TestaTamanhoSenha
		testArmazenaTamanhoSenha(config);
		testSenhaValorNegativo(config);
		testTamanhoSenhaMaiorAlfabeto(config);
		
		//Max tentativas
		testArmazenaMaxTentativas(config);
		testMaxTentativasNegativo(config);
		testMaxTentativasZero(config);
		testMaxTentativasUm(config);
		
	}
	
	void testCasoGeralAlfabeto(Configuracao config) {
		String alfabeto = "ABCDEFGHIJ";

		//caso geral
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e) {
			fail("gerou uma exceção valida");
		}
		
		assertEquals(config.getAlfabeto(),alfabeto);
	}
	
	void testAlfabetoNulo(Configuracao config) {
		String alfabeto=null;
		
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e) {
			assertEquals("o alfabeto deve possuir mais de 1 caracter",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto()==null);
	}

	void testAlfabeto1Caracter(Configuracao config) {
		String alfabeto="A";
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e) {
			assertEquals("o alfabeto deve possuir mais de 1 caracter",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().length() <=1);
	}
	
	void testCaracteresIguais(Configuracao config) {
		String alfabeto;
		alfabeto = "AABCDE";
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("AABCDE"));
		
		//Testar 2 caracteres iguais no fim do arquivo
		
		alfabeto="ABCDD";
		
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABCDD"));
		
		// um caracter no começo e outro no fim 
		
		alfabeto="ABCDA";
		
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABCDA"));

		// um caracter a posição do meio e outra no fim

		alfabeto="ABCDC";
		
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABCDC"));

		//Testar 1 caracter no início e outro em posição intermediária
		
		alfabeto="ABADE";
		
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABADE"));
		
		//● Testar 2 caracteres em posições intermediárias adjacentes
		
		alfabeto="ABCCD";

		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABCCD"));
		
		alfabeto="ABBCD";

		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABBCD"));

		//● Testar 3 caracteres em posições intermediárias não adjacentes
		alfabeto = "ABCBDBF";
		try {
			config.setAlfabeto(alfabeto);
		}catch(Exception e ) {
			assertEquals("Não podem haver caracteres repetidos no alfabeto",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals("ABCBDBF"));
	}

	void testCasoGeralNome(Configuracao config) {
		String nome = "medio";

		//caso geral
		try {
			config.setNome(nome);
		}catch(Exception e) {
			fail("gerou uma exceção valida");
		}
		
		assertEquals(config.getNome(),nome);
	}
	
	void testNomeVerificaDigito(Configuracao config) {
		String nome = "MEDIO15";
		try {
			config.setNome(nome);
		}catch(Exception e) {
			assertEquals("O nome nao deve conter caracteres especiais ou digitos",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals(nome));
	}
	
	void testNomeVerificaCaracterEspecial(Configuracao config) {
		String nome = "MEDIO*";
		try {
			config.setNome(nome);
		}catch(Exception e) {
			assertEquals("O nome nao deve conter caracteres especiais ou digitos",e.getMessage());
		}
		
		assertFalse(config.getAlfabeto().equals(nome));
	}
	
	void testNomeNulo(Configuracao config) {
		String nome=null;
		
		try {
			config.setNome(nome);
		}catch(Exception e) {
			assertEquals("o nome deve possuir mais de 1 caracter",e.getMessage());
		}
		
		assertFalse(config.getNome()==null);
	}
	
	void testTamanhoNome(Configuracao config) {
		String nome="A";
		
		try {
			config.setNome(nome);
		}catch(Exception e) {
			assertEquals("o nome deve possuir mais de 1 caracter",e.getMessage());
		}
		
		assertFalse(config.getNome().length() <=1);
	}
	
	void testEntradaValidaNome(Configuracao config) {
		String nome = "fácil";

		//caso geral
		try {
			config.setNome(nome);
		}catch(Exception e) {
			fail("gerou uma exceção valida");
		}
		
		assertEquals(config.getNome(),nome);
	}
	
	void testSenhaValorNegativo(Configuracao config) {
		int tam = -1;
		try {
			config.setTamanhoSenha(tam);
		} catch (Exception e) {
			assertEquals("O tamanho da senha deve ser maior ou igual a 1", e.getMessage());
		}
		//o tamanho da senha não pode ter sido aceito
		assertNotEquals(tam, config.getTamanhoSenha());
	}
	
	void testArmazenaTamanhoSenha(Configuracao config) {
		int tamanhoSenha = 2;
		try{
			config.setTamanhoSenha(tamanhoSenha);
		}catch(Exception e) {
			fail("gerou uma exceção valida");
		}
		
		assertEquals(config.getTamanhoSenha(),tamanhoSenha);
		
	}
	
	void testTamanhoSenhaMaiorAlfabeto(Configuracao config)  {
		try {
			config.setAlfabeto("ABCD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int tamanhoSenha = 18;
		try {
			config.setTamanhoSenha(tamanhoSenha);
		}catch(Exception e) {
			assertEquals("o tamanho da senha deve ser menor ou igual ao tamanho do alfabeto",e.getMessage());
		}
		
		assertFalse(config.getTamanhoSenha()==tamanhoSenha);

	}

	void testArmazenaMaxTentativas(Configuracao config) {
		int MaxTentativas = 2;
		try{
			config.setMaxTentativas(MaxTentativas);
		}catch(Exception e) {
			fail("não deveria gerar numero de tentativas");
		}
		
		assertEquals(config.getMaxTentativas(),MaxTentativas);
	}
	
	void testMaxTentativasNegativo(Configuracao config){
		int MaxTentativas = -1;
		try{
			config.setMaxTentativas(MaxTentativas);
		}catch(Exception e) {
			assertEquals("numero de tentativas deve ser maior que 1",e.getMessage());
		}
		
		assertFalse(config.getMaxTentativas()==MaxTentativas);
	}
	
	void testMaxTentativasZero(Configuracao config){
		int MaxTentativas = 0;
		try{
			config.setMaxTentativas(MaxTentativas);
		}catch(Exception e) {
			assertEquals("numero de tentativas deve ser maior que 1",e.getMessage());
		}
		
		assertFalse(config.getMaxTentativas()==MaxTentativas);
	}
	
	void testMaxTentativasUm(Configuracao config) {
		int MaxTentativas = 1;
		try{
			config.setMaxTentativas(MaxTentativas);
		}catch(Exception e) {
			assertEquals("numero de tentativas deve ser maior que 1",e.getMessage());
		}
		
		assertFalse(config.getMaxTentativas()==MaxTentativas);
	}

	
}


