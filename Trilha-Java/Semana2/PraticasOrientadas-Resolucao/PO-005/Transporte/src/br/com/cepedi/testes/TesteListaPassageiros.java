package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.pessoa.Passageiro;

class TesteListaPassageiros {

	@Test
	void test() {
		testeInsereNaLista();
		insereDoisNaLista();
		testeInserePassageiroNulo();
		testeInsereRepetido();
		deletarPassageiroExistente();
		deletarPassageiroNulo();
		deletarPassageiroForaDaLista();
		buscarPassageiroNaLista();
		buscarPassageiroForaDaLista();
	}
	
	public void testeInsereNaLista() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(passageiro);
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void insereDoisNaLista() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro1 = new Passageiro("Joao","04999695537", 1);
			Passageiro passageiro2 = new Passageiro("Alemao","09860932549", 1);
			lista.adiciona(passageiro1);
			lista.adiciona(passageiro2);
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertTrue(lista.size()==2);
	}

	public void testeInserePassageiroNulo() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			lista.adiciona(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());	
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void testeInsereRepetido() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro1 = new Passageiro("Joao","04999695537", 1);
			Passageiro passageiro2 = new Passageiro("Alemao","04999695537", 1);
			lista.adiciona(passageiro1);
			lista.adiciona(passageiro2);
		}catch(Exception e ) {
			assertEquals("passageiro já cadastrado.",e.getMessage());	
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarPassageiroExistente() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(passageiro);
			lista.deletar("04999695537");
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void deletarPassageiroNulo() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(passageiro);
			lista.deletar(null);
		}catch(Exception e ) {
			assertEquals("Foi inserido um cpf nulo na busca",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarPassageiroForaDaLista() {
		ListaPassageiros lista = new ListaPassageiros();
		try {
			Passageiro passageiro = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(passageiro);
			lista.deletar("09860932549");
		}catch(Exception e ) {
			assertEquals("passageiro não encontrado.",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void buscarPassageiroNaLista() {
		ListaPassageiros lista = new ListaPassageiros();
		Passageiro p1 = null , p2 = null;
		try {
			p1 = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(p1);
			p2 = lista.buscar("04999695537");
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertEquals(p1,p2);
	}
	
	public void buscarPassageiroForaDaLista() {
		ListaPassageiros lista = new ListaPassageiros();
		Passageiro p1 = null , p2 = null;
		try {
			p1 = new Passageiro("Joao","04999695537", 1);	
			lista.adiciona(p1);
			p2 = lista.buscar("09860932549");
		}catch(Exception e ) {
			assertEquals("passageiro não encontrado.",e.getMessage());
		}
		
		assertNotEquals(p1,p2);
	}
	
}
