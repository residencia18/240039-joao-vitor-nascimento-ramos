package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoJaCadastrado;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.model.veiculo.Veiculo;

class TesteListaJornada {

	@Test
	void test() {
		criaJornadaInseneNaLista();
		testeInsereJornadaNula();
		testeInsereJornadaRepetida();
		deletarJornadaExistente();
		deletarJornadaForaDaLista();
	}
	
	public void criaJornadaInseneNaLista() {
		Jornada jornada = null;
		ListaJornadas lista = null;
		try {
			lista = new ListaJornadas();
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Cobrador cobrador = new Cobrador("Paulo","04999695537","1320");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 5, 2, 17, 30);
			jornada = new Jornada(veiculo,cobrador,motorista,nome,ldt);
			lista.adiciona(jornada);
			
		}catch(Exception e ) {
			fail("nao deve cair aqui");
		}
		
		assertTrue(lista.size()==1);
		assertNotNull(jornada);
	}
	
	public void testeInsereJornadaNula() {
		ListaJornadas lista = new ListaJornadas();
		try {
			lista.adiciona(null);
			
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(lista.isEmpty());
		
	}
	
	public void testeInsereJornadaRepetida() {
		Jornada jornada = null , jornada2 = null;
		Trajeto trajeto = null , trajeto2 = null;
		ListaJornadas lista = null;
		try {
			lista = new ListaJornadas();
			trajeto = new Trajeto(LocalDateTime.now());
			trajeto2 = new Trajeto(LocalDateTime.now());
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Cobrador cobrador = new Cobrador("Paulo","04999695537","1320");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 5, 2, 17, 30);
			jornada = new Jornada(veiculo,cobrador,motorista,nome,ldt);
			jornada2 = new Jornada(veiculo,cobrador,motorista,nome,ldt);

			

			preencheJornadaComTrajetos(jornada, jornada2, trajeto, trajeto2);
			
			lista.adiciona(jornada);
			lista.adiciona(jornada2);
			
		}catch(Exception e ) {
			assertEquals("Jornada já cadastrada",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}

	
	public void deletarJornadaExistente() {
		Jornada jornada = null;
		ListaJornadas lista = null;
		try {
			lista = new ListaJornadas();
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Cobrador cobrador = new Cobrador("Paulo","04999695537","1320");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 5, 2, 17, 30);
			jornada = new Jornada(veiculo,cobrador,motorista,nome,ldt);
			lista.adiciona(jornada);
			lista.mostraTodos();
			lista.deletar(4);
			
		}catch(Exception e ) {
			fail("nao deve cair aqui");
		}
		
		assertTrue(lista.isEmpty());
	}
	
	public void deletarJornadaForaDaLista() {
		Jornada jornada = null;
		ListaJornadas lista = null;
		try {
			lista = new ListaJornadas();
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Cobrador cobrador = new Cobrador("Paulo","04999695537","1320");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 5, 2, 17, 30);
			jornada = new Jornada(veiculo,cobrador,motorista,nome,ldt);
			lista.adiciona(jornada);
			lista.mostraTodos();
			lista.deletar(7);
			
		}catch(Exception e ) {
			assertEquals("Jornada não encontrada",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	private void preencheJornadaComTrajetos(Jornada jornada, Jornada jornada2, Trajeto trajeto, Trajeto trajeto2)
			throws TrechoJaCadastrado, TrajetoJaCadastrado {
		PontoDeParada p1 = new PontoDeParada("Rodoviaria");
		PontoDeParada p2 = new PontoDeParada("Galileu");
		PontoDeParada p3 = new PontoDeParada("Uesc");
		Trecho t1 = new Trecho(p1,p2,20);
		Trecho t2 = new Trecho(p2,p3,20);
		Trecho t3 = new Trecho(p1,p3,50);

		trajeto.adicionaTrecho(t1);
		trajeto.adicionaTrecho(t2);
		trajeto2.adicionaTrecho(t3);
		
		jornada.getTrajetos().adiciona(trajeto);
		jornada.getTrajetos().adiciona(trajeto2);
		
		jornada2.getTrajetos().adiciona(trajeto);
		jornada2.getTrajetos().adiciona(trajeto2);
	}
	
	
	

}
