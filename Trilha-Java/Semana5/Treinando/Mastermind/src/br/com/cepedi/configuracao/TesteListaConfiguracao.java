package br.com.cepedi.configuracao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteListaConfiguracao {

	@Test
	void test() {
		insereConfiguracaoLista();
		insereDuasConfiguracoesLista();
		insereDuplicadoNaLista();
	}
	
	public void insereConfiguracaoLista() {
		ListaConfiguracao lista = new ListaConfiguracao();
		Configuracao c1 = new Configuracao();
		try {
			c1.setAlfabeto("ABCDEF");
			c1.setMaxTentativas(2);
			c1.setTamanhoSenha(4);
			c1.setNome("facil");
			lista.novaConfiguracao(c1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		lista.mostraConfiguracoes();
	}
	
	public void insereDuasConfiguracoesLista() {
		
	}
	
	public void insereDuplicadoNaLista() {
		ListaConfiguracao lista = new ListaConfiguracao();
		Configuracao c1 = new Configuracao();
		Configuracao c2 = new Configuracao();
		try {
			c1.setAlfabeto("ABCDEF");
			c1.setMaxTentativas(2);
			c1.setTamanhoSenha(4);
			c1.setNome("Medio");
			
			c2.setAlfabeto("ABCDEF");
			c2.setMaxTentativas(3);
			c2.setTamanhoSenha(3);
			c2.setNome("Medio");
			lista.novaConfiguracao(c1);
			lista.novaConfiguracao(c2);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Já existe uma configuração com o mesmo nome. A configuração não foi adicionada",e.getMessage());
		}
		System.out.println(lista.getLista().size());
		assertTrue(lista.getLista().size()==1);
	}

}
