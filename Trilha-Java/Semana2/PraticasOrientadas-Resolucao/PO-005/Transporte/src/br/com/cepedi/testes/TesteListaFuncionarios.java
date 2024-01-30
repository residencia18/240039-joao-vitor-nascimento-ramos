package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;

class TesteListaFuncionarios {

	@Test
	void test() {
		testeInsereFuncionarioNulo();
		testeInsereMotoristaRepetido();
		deletarFuncionarioExistente();
		deletarFuncionarioNulo();
		deletarFuncionarioForaDaLista();
		
		testeInsereMotoristaNaLista();
		buscarMotoristaNaLista();
		buscarMotoristaForaDaLista();
		
		
		testeInsereCobradorNaLista();
		testeInsereCobradorEMotoristaNaLista();
		testeCobradorEMotoristaMesmoCPF();
		
		buscarCobradorNaLista();
		buscarCobradorForaDaLista();
	}
	
	public void testeInsereMotoristaNaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista motorista;
		try {
			motorista = new Motorista("Joao","049.996.955-37","1250","12345678901");
			lista.adiciona(motorista);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("Não deve cair neste catch");
		}
		
		assertFalse(lista.isEmpty());	
	}
	
	public void testeInsereFuncionarioNulo() {
		ListaFuncionarios lista = new ListaFuncionarios();
		try {
			lista.adiciona(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(lista.isEmpty());	
	}
	
	public void testeInsereMotoristaRepetido() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista motorista1;
		Motorista motorista2;
		try {
			motorista1 = new Motorista("Joao","049.996.955-37","1250","12345678901");
			motorista2 = new Motorista("Paulo","04999695537","3000","12345678901");

			lista.adiciona(motorista1);
			lista.adiciona(motorista2);

		}catch(Exception e ) {
			assertEquals("Esse funcionario já foi cadastrado.",e.getMessage());

		}
		
		assertTrue(lista.size()==1);
	}
	
	public void deletarFuncionarioExistente() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista motorista;
		try {
			motorista = new Motorista("Joao","049.996.955-37","1250","12345678901");
			lista.adiciona(motorista);
			lista.deletar("04999695537");
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("Não deve cair neste catch");
		}
		
		assertTrue(lista.isEmpty());	
	}
	
	public void deletarFuncionarioNulo() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista m1;
		try {
			m1 = new Motorista("Joao","049.996.955-37","1250","12345678901");
			lista.adiciona(m1);
			lista.deletar(null);
		}catch(Exception e ) {
			assertEquals("Foi inserido um cpf nulo na busca",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	
	public void deletarFuncionarioForaDaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista m1;
		try {
			m1 = new Motorista("Joao","049.996.955-37","1250","12345678901");
			lista.adiciona(m1);
			lista.deletar("098.609.325-49");
		}catch(Exception e ) {
			assertEquals("Funcionario não encontrado",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	public void buscarMotoristaNaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Motorista m1 = null, m2=null;
		try {
			m1 = new Motorista("Joao","049.996.955-37","1250","12345678901");
			lista.adiciona(m1);
			m2 = (Motorista) lista.buscar("04999695537");
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
		
		assertEquals(m1,m2);
	}
	
	
	public void buscarMotoristaForaDaLista() {
		ListaFuncionarios listaMotoristas = new ListaFuncionarios();
		Motorista m1 = null, m2=null;
		try {
			m1 = new Motorista("Joao","049.996.955-37","1250","12345678901");
			listaMotoristas.adiciona(m1);
			m2 = (Motorista) listaMotoristas.buscar("09860932549");
		}catch(Exception e ) {
			assertEquals("Funcionario não encontrado",e.getMessage());
		}
		
		assertNotEquals(m1,m2);
	}
	
	
	public void testeInsereCobradorNaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Cobrador cobrador;
		try {
			cobrador = new Cobrador("Joao","049.996.955-37","1250");
			lista.adiciona(cobrador);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("Não deve cair neste catch");
		}
		
		assertFalse(lista.isEmpty());	
	}

	public void testeInsereCobradorEMotoristaNaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Cobrador cobrador;
		Motorista motorista;
		try {
			motorista = new Motorista("Joao","049.996.955-37","1250","12345678901");
			cobrador = new Cobrador("Joao","09860932549","1250");
			lista.adiciona(motorista);
			lista.adiciona(cobrador);
		}catch(Exception e ) {
			assertEquals("Funcionario não encontrado",e.getMessage());
		}
		
		assertTrue(lista.size()==2);
	}
	
	public void testeCobradorEMotoristaMesmoCPF() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Cobrador cobrador;
		Motorista motorista;
		try {
			motorista = new Motorista("Joao","049.996.955-37","1250","12345678901");
			cobrador = new Cobrador("Joao","04999695537","1250");
			lista.adiciona(motorista);
			lista.adiciona(cobrador);
		}catch(Exception e ) {
			assertEquals("Esse funcionario já foi cadastrado.",e.getMessage());
		}
		
		assertTrue(lista.size()==1);
	}
	
	
	public void buscarCobradorNaLista() {
		ListaFuncionarios lista = new ListaFuncionarios();
		Cobrador c1 = null, c2=null;
		try {
			c1 = new Cobrador("Joao","049.996.955-37","1250");
			lista.adiciona(c1);
			c2 = (Cobrador) lista.buscar("04999695537");
		}catch(Exception e ) {
			fail("Não deve cair aqui!");
		}
		
		assertEquals(c1,c2);
	}
	
	
	public void buscarCobradorForaDaLista() {
		ListaFuncionarios listaMotoristas = new ListaFuncionarios();
		Cobrador c1 = null, c2 = null;
		try {
			c1 = new Cobrador("Joao","049.996.955-37","1250");
			listaMotoristas.adiciona(c1);
			c2 = (Cobrador) listaMotoristas.buscar("09860932549");
		}catch(Exception e ) {
			assertEquals("Funcionario não encontrado",e.getMessage());
		}
		
		assertNotEquals(c1,c2);
	}
	
	
}
