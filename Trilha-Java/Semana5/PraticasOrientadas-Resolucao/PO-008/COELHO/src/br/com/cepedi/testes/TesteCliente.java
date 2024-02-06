package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Cliente;

class TesteCliente {

	@Test
	void test() {
		instanciaCliente();
		instanciaClienteNomeComNumeros();
		instanciaClienteComCPFInvalido();
		instanciaClienteNomeNulo();
		instanciaClienteNomeVazio();
		instanciaClienteCPFNulo();
		instanciaClienteCPFVazio();
		verificaEquals();
		verificaIdsIncrementais();
	}
	
	
	public static void instanciaCliente() {
		Cliente cliente = null;
		try {
			String nome = "joão";
			String cpf = "04999695537";
			cliente = new Cliente(nome,cpf);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
			fail("não deve cair aqui");
		}

	}
	
	public static void instanciaClienteNomeComNumeros() {
		Cliente cliente = null;
		try {
			String nome = "jo5o";
			String cpf = "04999695537";
			cliente = new Cliente(nome,cpf);
		}catch(Exception e ) {
			assertEquals("O nome não pode conter numeros",e.getMessage());
		}
		assertNull(cliente);
	}
	
	public static void instanciaClienteComCPFInvalido() {
		Cliente cliente = null;
		try {
			String nome = "joão";
			String cpf = "049996957";
			cliente = new Cliente(nome,cpf);
		}catch(Exception e ) {
			assertEquals("CPF inválido",e.getMessage());
		}
		assertNull(cliente);
	}
	
	public static void instanciaClienteNomeNulo() {
		Cliente cliente = null;
		try {
			String cpf = "04999695537";
			cliente = new Cliente(null,cpf);
		}catch(Exception e ) {
			assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
		}
		assertNull(cliente);
	}
	
	public static void instanciaClienteNomeVazio() {
		Cliente cliente = null;
		try {
			String nome ="";
			String cpf = "04999695537";
			cliente = new Cliente(nome,cpf);
		}catch(Exception e ) {
			assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
		}
		assertNull(cliente);
	}
	
	public static void instanciaClienteCPFNulo() {
		Cliente cliente = null;
		try {
			String nome = "João";
			String cpf = "04999695537";
			cliente = new Cliente(nome,null);
		}catch(Exception e ) {
			assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
		}
		assertNull(cliente);
	}
	
	public static void instanciaClienteCPFVazio() {
		Cliente cliente = null;
		try {
			String nome = "João";
			String cpf = "";
			cliente = new Cliente(nome,cpf);
		}catch(Exception e ) {
			assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
		}
		assertNull(cliente);
	}
	
	
	public static void verificaEquals() {
		Cliente c1 = null , c2 = null;
		try {
			c1 = new Cliente("João","04999695537");
			c2 = new Cliente("Paulo","04999695537");
		}catch(Exception e) {
			fail("Não deve cair aqui");
		}
		
		assertEquals(c1,c2);
		
	}
	
	public static void verificaIdsIncrementais() {
		Cliente c1 = null , c2 = null;
		try {
			c1 = new Cliente("João","04999695537");
			c2 = new Cliente("Paulo","04999695537");
		}catch(Exception e) {
			fail("Não deve cair aqui");
		}
		
		assertEquals(c1.getId()+1,c2.getId());
	}
	
	

}
