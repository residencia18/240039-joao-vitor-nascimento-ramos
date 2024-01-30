package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;

class TesteTrajeto {

	@Test
	void test() {
		testeTrajetoPadrao();
		insereLocalDateNulo();
		insereNomeNulo();
		testaNomeAutomatico();
		testaContagemTempo();
		listaTodosOsTrechos();
		removeTrecho();
	}
	
	public void testeTrajetoPadrao() {
		try {
			Trajeto trajeto = new Trajeto(LocalDateTime.now());
			
		}catch(Exception e ) {
			fail("Não deve cair aqui");
		}

	}
	
	public void insereLocalDateNulo(){
		Trajeto trajeto = null;
		try {
			trajeto = new Trajeto(null);
		}catch(Exception e ) {
			assertEquals("A hora de inicio do trajeto não pode ser nulo",e.getMessage());
		}
		
		assertNull(trajeto);
		
	}
	
	public void insereNomeNulo() {
		Trajeto trajeto = null;
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto.setNome(null);
		}catch(Exception e ) {
			assertEquals("O nome não pode ser nulo",e.getMessage());
		}
		
		assertNull(trajeto.getNome());
	}
	
	
	public void testaNomeAutomatico() {
		Trajeto trajeto=null;

		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			PontoDeParada p4 = new PontoDeParada("Ilheus");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p3,p4,20);
			trajeto.adiciona(t1);
			assertEquals("Rodoviaria-Galileu",trajeto.getNome());
			trajeto.adiciona(t2);
			assertEquals("Rodoviaria-Uesc",trajeto.getNome());
			trajeto.adiciona(t3);
			assertEquals("Rodoviaria-Ilheus",trajeto.getNome());
		}catch(Exception e ) {
			e.getStackTrace();
		}
		assertNotNull(trajeto);

	}
	
	public void testaContagemTempo() {
		Trajeto trajeto=null;

		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			PontoDeParada p4 = new PontoDeParada("Ilheus");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p3,p4,28);
			trajeto.adiciona(t1);
			trajeto.adiciona(t2);
			trajeto.adiciona(t3);
		}catch(Exception e ) {
			e.getStackTrace();
		}
		assertNotNull(trajeto);
		assertEquals(trajeto.tempoDeTodosTrechos(),68);

	}
	
	public void listaTodosOsTrechos() {
		Trajeto trajeto=null;

		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);

			trajeto.adiciona(t1);
			trajeto.adiciona(t2);
		}catch(Exception e ) {
			e.getStackTrace();
		}
		assertNotNull(trajeto);
		assertEquals("Trecho [id=7, origem=PontoDeParada [id=9, nome=Rodoviaria], destino=PontoDeParada [id=10, nome=Galileu], minutos=20]\n"
				+ "Trecho [id=8, origem=PontoDeParada [id=10, nome=Galileu], destino=PontoDeParada [id=11, nome=Uesc], minutos=20]\n",trajeto.listaTodos());
	}

	public void removeTrecho() {
		Trajeto trajeto=null;

		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);

			trajeto.adiciona(t1);
			trajeto.adiciona(t2);
			trajeto.remove(t1);
		}catch(Exception e ) {
			fail("Não deve cair aqui");
		}
		assertTrue(trajeto.getTrechos().size()==1);

	}
	
	public void removeTrechoForaTrajeto() {
		Trajeto trajeto=null;

		
		try {
			trajeto = new Trajeto(LocalDateTime.now());
			PontoDeParada p1 = new PontoDeParada("Rodoviaria");
			PontoDeParada p2 = new PontoDeParada("Galileu");
			PontoDeParada p3 = new PontoDeParada("Uesc");
			Trecho t1 = new Trecho(p1,p2,20);
			Trecho t2 = new Trecho(p2,p3,20);
			Trecho t3 = new Trecho(p1,p3,50);

			trajeto.adiciona(t1);
			trajeto.adiciona(t2);
			trajeto.remove(t3);
		}catch(Exception e ) {
			assertEquals("Trecho não encontrado no trajeto",e.getMessage());

		}
		assertTrue(trajeto.getTrechos().size()==2);

	}
	
	

}
