package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.transporte.PontoDeParada;

class TesteListaPontosDeParada {

	@Test
	void test() {
		testeInsereNaLista();
		insereDoisNaLista();
		testeInserePontoNulo();
		testeInsereRepetido();
		deletarPontoExistente();
		deletarPontoNulo();
		deletarPontoForaDaLista();
		buscarPontoNaLista();
		buscarPontoForaDaLista();
	}
	
	public void testeInsereNaLista() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			lista.adiciona(ponto);
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void insereDoisNaLista() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			PontoDeParada ponto2 = new PontoDeParada("UESC");
			lista.adiciona(ponto);
			lista.adiciona(ponto2);
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertTrue(lista.size()==2);
	}

	public void testeInserePontoNulo() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			lista.adiciona(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());	
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void testeInsereRepetido() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			PontoDeParada ponto2 = new PontoDeParada("Ferradas");
			lista.adiciona(ponto);
			lista.adiciona(ponto2);
		}catch(Exception e ) {
			assertEquals("Esse ponto já foi cadastrado.",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarPontoExistente() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			lista.adiciona(ponto);
			lista.deletar("Ferradas");
		}catch(Exception e ) {
			assertEquals("oi",e.getMessage());
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void deletarPontoNulo() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			lista.adiciona(ponto);
			lista.deletar(null);
		}catch(Exception e ) {
			assertEquals("Foi inserido um nome nulo na busca.",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarPontoForaDaLista() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		try {
			PontoDeParada ponto = new PontoDeParada("Ferradas");
			lista.adiciona(ponto);
			lista.deletar("Caio");
		}catch(Exception e ) {
			assertEquals("ponto não encontrado",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void buscarPontoNaLista() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		PontoDeParada p1 = null , p2 = null;
		try {
			p1 = new PontoDeParada("Ferradas");	
			lista.adiciona(p1);
			p2 = lista.buscar("Ferradas");
		}catch(Exception e ) {
			fail("Não é para dar erro aqui");
		}
		
		assertEquals(p1,p2);
	}
	
	public void buscarPontoForaDaLista() {
		ListaPontosDeParada lista = new ListaPontosDeParada();
		PontoDeParada p1 = null , p2 = null;
		try {
			p1 = new PontoDeParada("Ferradas");	
			lista.adiciona(p1);
			p2 = lista.buscar("UESC");
		}catch(Exception e ) {
			assertEquals("ponto não encontrado",e.getMessage());
		}
		
		assertNotEquals(p1,p2);
	}
}
