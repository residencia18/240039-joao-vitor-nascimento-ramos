package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trecho;

class TesteListaTrechos {

	@Test
	void test() {
		testeInsereNaLista();
		insereDoisNaLista();
		testeInsereTrechoNulo();
		testeInsereTrechoRepetido();
		deletarTrechoExistente();
		deletarTrechoNulo1();
		deletarTrechoNulo2();
		deletarTrechoForaDaLista();
		buscarTrechoNaLista();
		buscaTrechoForaDaLista();
	}
	
	public void testeInsereNaLista() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
		}catch(Exception e ) {
			fail("não deve cair aqui");
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void insereDoisNaLista() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null , trecho2=null;
		PontoDeParada p1=null , p2=null , p3=null , p4=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			p3 = new PontoDeParada("UESC");
			p4 = new PontoDeParada("Rodoviaria");
			trecho2 = new Trecho(p3,p4,35);
			lista.adiciona(trecho);
			lista.adiciona(trecho2);
		}catch(Exception e ) {
			fail("não deve cair aqui");
		}
		
		assertTrue(lista.size()==2);
	}
	
	public void testeInsereTrechoNulo() {
		ListaTrechos lista = new ListaTrechos();
		try {
			lista.adiciona(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertNotNull(lista.isEmpty());
	}
	
	public void testeInsereTrechoRepetido() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null , trecho2=null;
		PontoDeParada p1=null , p2=null , p3=null , p4=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			p3 = new PontoDeParada("Rodoviaria");
			p4 = new PontoDeParada("UESC");
			trecho2 = new Trecho(p3,p4,15);
			lista.adiciona(trecho);
			lista.adiciona(trecho2);
		}catch(Exception e ) {
			assertEquals("trecho ja cadastrado",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarTrechoExistente() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			lista.deletar("Rodoviaria", "UESC");
		}catch(Exception e ) {
			fail("não deve cair aqui");
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void deletarTrechoNulo1() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			lista.deletar("Rodoviaria", null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarTrechoNulo2() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			lista.deletar(null, "UESC");
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarTrechoForaDaLista() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			lista.deletar("Papito", "UESC");
		}catch(Exception e ) {
			assertEquals("trecho não encontrado.",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}

	public void buscarTrechoNaLista() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null , trecho2=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			trecho2 = lista.buscar("Rodoviaria", "UESC");
		}catch(Exception e ) {
			fail("não deve cair aqui");
		}
		
		assertEquals(trecho,trecho2);
	}
	
	public void buscaTrechoForaDaLista() {
		ListaTrechos lista = new ListaTrechos();
		Trecho trecho=null , trecho2=null;
		PontoDeParada p1=null , p2=null;
		try {
			p1 = new PontoDeParada("Rodoviaria");
			p2 = new PontoDeParada("UESC");
			trecho = new Trecho(p1,p2,35);
			lista.adiciona(trecho);
			trecho2 = lista.buscar("Rodoviaia", "UESC");
		}catch(Exception e ) {
			assertEquals("trecho não encontrado.",e.getMessage());
		}
		
		assertNotEquals(trecho,trecho2);
	}
	

}
