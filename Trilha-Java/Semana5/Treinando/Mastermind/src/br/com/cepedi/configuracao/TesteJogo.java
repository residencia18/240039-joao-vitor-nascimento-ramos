package br.com.cepedi.configuracao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteJogo {

	@Test
	void test() {
		
		testJogoGeral();
		testSenhaTamanhoMenor();
		testSenhaTamanhoMaior();
		testSenhaTamanhoCerto();
		testSenhaForaDoAlfabeto();
		testSenhaDentroDoAlfabetoAoContrario();
		testSenhaCaracterRepetido();
		
		testSetJogada1();
		testSetJogada2();
		testSetJogada3();
		
	}
	
	public void testJogoGeral() {
		Configuracao config;
		Jogo jogo;
		
		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
	}
		
	public void testSenhaTamanhoMenor() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABC";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			assertEquals("Senha deve ter 4 caracteres",e.getMessage());
		}	
	}
	
	public void testSenhaTamanhoMaior() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCDEFG";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			assertEquals("Senha deve ter 4 caracteres",e.getMessage());
		}	
	}
	
	public void testSenhaTamanhoCerto() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCD";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
	}
	
	public void testSenhaForaDoAlfabeto() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCO";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			assertEquals("Senha não condiz com o alfabeto",e.getMessage());
		}
	}
	
	public void testSenhaDentroDoAlfabetoAoContrario() {
		Configuracao config;
		Jogo jogo;
		String senha = "CABD";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
	}
	
	public void testSenhaCaracterRepetido() {
		Configuracao config;
		Jogo jogo;
		String senha = "AABC";

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
		}catch(Exception e ) {
			assertEquals("Não deve haver caracteres repetidos na senha",e.getMessage());
		}
	}
	
	public void testSetJogada1() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCD";
		String saida = null;

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
			saida = jogo.setJogada("AEFG");
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
		
		assertEquals(saida,"X___");
	}
	
	public void testSetJogada2() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCD";
		String saida = null;

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
			saida = jogo.setJogada("AEFB");
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
		
		assertEquals(saida,"X__O");
	}
	
	public void testSetJogada3() {
		Configuracao config;
		Jogo jogo;
		String senha = "ABCD";
		String saida = null;

		try {
			config = new Configuracao();
			config.setAlfabeto("ABCDEFG");
			config.setNome("Medio");
			config.setMaxTentativas(5);
			config.setTamanhoSenha(4);
			jogo = new Jogo(config);
			jogo.setSenha(senha);
			saida = jogo.setJogada("ADCB");
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
		
		assertEquals(saida,"XOXO");
	}
	
	
	

	

}
