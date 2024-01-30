package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.veiculo.Veiculo;

class TesteListaVeiculos {

	@Test
	void test() {
		testeInsereNaLista();
		insereDoisVeiculos();
		testeInsereVeiculoNulo();
		testeInsereRepetido();
		deletarVeiculoExistente();
		deletarVeiculoNulo();
		deletarVeiculoForaDaLista();
		buscarVeiculoNaLista();
		buscarVeiculoForaDaLista();
	}
	
	public void testeInsereNaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo veiculo;
		try {
			veiculo = new Veiculo("Mobi","GFD-4578","Fiat");
			listaVeiculos.adicionar(veiculo);
		}catch(Exception e ) {
			fail("Não deve cair nesse catch");
		}
		
		System.out.println();
		
		assertFalse(listaVeiculos.isEmpty());
			 
	}
	
	public void insereDoisVeiculos(){
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1= null , v2 = null;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionar(v1);
			listaVeiculos.adicionar(v2);
		}catch(Exception e ) {
			assertEquals("Essa placa já foi cadastrada.",e.getMessage());
		}

	}
	public void testeInsereVeiculoNulo() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		try {
			listaVeiculos.adicionar(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(listaVeiculos.size()==0);
	}
	
	public void testeInsereRepetido() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4578","Fiat");
			listaVeiculos.adicionar(v1);
			listaVeiculos.adicionar(v2);
		}catch(Exception e ) {
			assertEquals("Essa placa já foi cadastrada.",e.getMessage());
		}
				
		assertTrue(listaVeiculos.size()==1);
	}
	
	public void deletarVeiculoExistente() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionar(v1);
			listaVeiculos.adicionar(v2);
			listaVeiculos.deletar("GFD-4578");
		}catch(Exception e ) {
			fail("Não deve cair no catch");
		}
		
		assertTrue(listaVeiculos.size()==1);
				
	}

	
	public void deletarVeiculoNulo() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionar(v1);
			listaVeiculos.adicionar(v2);
			listaVeiculos.deletar(null);
		}catch(Exception e ) {
			assertEquals("Foi inserida uma placa nula na busca",e.getMessage());
		}
		
		assertTrue(listaVeiculos.size()==2);
				
	}
	
	public void deletarVeiculoForaDaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionar(v1);
			listaVeiculos.adicionar(v2);
			listaVeiculos.deletar("KCY-7521");
		}catch(Exception e ) {
			assertEquals("Veiculo não encontrado",e.getMessage());
		}
		
		assertTrue(listaVeiculos.size()==2);
				
	}
	
	public void buscarVeiculoNaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo veiculo = null;
		Veiculo veiculo2 = null;
		try {
			veiculo = new Veiculo("Mobi","GFD-4578","Fiat");
			listaVeiculos.adicionar(veiculo);
			veiculo2 = listaVeiculos.buscar("GFD-4578");
		}catch(Exception e ) {
			fail("Não deve cair nesse catch");
		}
		
		assertEquals(veiculo,veiculo2);
	}
	
	public void buscarVeiculoForaDaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo veiculo = null;
		Veiculo veiculo2 = null;
		try {
			veiculo = new Veiculo("Mobi","GFD-4578","Fiat");
			listaVeiculos.adicionar(veiculo);
			veiculo2 = listaVeiculos.buscar("GFD-4585");
		}catch(Exception e ) {
			assertEquals("Veiculo não encontrado",e.getMessage());
		}
		
		assertNotEquals(veiculo,veiculo2);
	}
	
}
