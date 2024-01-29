package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;

class TesteListaTrajetos {

	@Test
	void test() {
		testeInsereNaLista();
		insereDoisNaLista();
		testeInsereTrajetoNulo();
		testeInsereTrajetoRepetido();
		deletarTrajetoExistente();
		deletarTrajetoForaDaLista();
		deletarTrajetoIDZero();
		buscarTrajetoNaLista();
		buscaTrajetoForaDaLista();
	}
	
	public void testeInsereNaLista() {
		Trajeto trajeto=null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			
			lt.adiciona(trajeto);
			
		}catch(Exception e ) {
			fail("Não deve dar erro aqui");

		}
	}
	
	public void insereDoisNaLista() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
		}catch(Exception e ) {
			fail("nao deve cair aqui");

		}
	}
	
	public void testeInsereTrajetoNulo() {
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			lt.adiciona(null);
			
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());

		}
	}
	
	public void testeInsereTrajetoRepetido() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t1);
			trajeto2.adicionaTrecho(t2);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
		}catch(Exception e ) {
			assertEquals("Esse trajeto já foi cadastrado.",e.getMessage());

		}
	}
	
	public void deletarTrajetoExistente() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			lt.deletar(6);
			
		}catch(Exception e ) {
			fail("não deve cair aqui");

		}
	}
	
	public void deletarTrajetoForaDaLista() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			lt.deletar(4);
			
		}catch(Exception e ) {
			assertEquals("Trajeto não encontrado",e.getMessage());

		}
	}
	
	public void deletarTrajetoIDZero() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			lt.deletar(0);
			
		}catch(Exception e ) {
			assertEquals("Insira um id 1 ou superior",e.getMessage());

		}
	}
	
	public void deletarTrajetoIDNegativo() {
		Trajeto trajeto=null , trajeto2 = null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			lt.deletar(-1);
			
		}catch(Exception e ) {
			assertEquals("Insira um id 1 ou superior",e.getMessage());

		}
	}

	
	public void buscarTrajetoNaLista() {
		Trajeto trajeto=null , trajeto2 = null , trajeto3=null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			
			trajeto3 = lt.buscar(12);
			
		}catch(Exception e ) {
			fail("Não deve cair aqui");

		}
		
		assertTrue(trajeto3==trajeto);
	}
	
	public void buscaTrajetoForaDaLista() {
		Trajeto trajeto=null , trajeto2 = null , trajeto3=null;
		ListaTrajetos lt = new ListaTrajetos();
		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adicionaTrecho(t1);
			trajeto.adicionaTrecho(t2);
			trajeto2.adicionaTrecho(t3);
			
			lt.adiciona(trajeto);
			lt.adiciona(trajeto2);
			
			
			trajeto3 = lt.buscar(18);
			
		}catch(Exception e ) {
			assertEquals("Trajeto não encontrado",e.getMessage());

		}
		
		assertNull(trajeto3);
	}
}
