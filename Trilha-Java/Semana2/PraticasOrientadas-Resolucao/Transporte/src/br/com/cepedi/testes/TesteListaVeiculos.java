package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.veiculo.Veiculo;

class TesteListaVeiculos {

	@Test
	void test() {
		testeInsereNaLista();
		testeInsereVeiculoNulo();
		testeInsereRepetido();
		deletarVeiculoExistente();
		deletarVeiculoNulo();
		deletarVeiculoForaDaLista();
	}
	
	public void testeInsereNaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo veiculo;
		try {
			veiculo = new Veiculo("Mobi","GFD-4578","Fiat");
			listaVeiculos.adicionarVeiculo(veiculo);
		}catch(Exception e ) {
			fail("Não deve cair nesse catch");
		}
		
		System.out.println();
		
		assertFalse(listaVeiculos.getListaDeVeiculos().isEmpty());
			
	}
	
	public void testeInsereVeiculoNulo() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		try {
			listaVeiculos.adicionarVeiculo(null);
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir um valor nulo",e.getMessage());
		}
		
		assertTrue(listaVeiculos.getListaDeVeiculos().size()==0);
	}
	
	public void testeInsereRepetido() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4578","Fiat");
			listaVeiculos.adicionarVeiculo(v1);
			listaVeiculos.adicionarVeiculo(v2);
		}catch(Exception e ) {
			assertEquals("Essa placa já foi cadastrada.",e.getMessage());
		}
				
		assertTrue(listaVeiculos.getListaDeVeiculos().size()==1);
	}
	
	public void deletarVeiculoExistente() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionarVeiculo(v1);
			listaVeiculos.adicionarVeiculo(v2);
			listaVeiculos.deletarVeiculo("GFD-4578");
		}catch(Exception e ) {
			fail("Não deve cair no catch");
		}
		
		assertTrue(listaVeiculos.getListaDeVeiculos().size()==1);
				
	}

	
	public void deletarVeiculoNulo() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionarVeiculo(v1);
			listaVeiculos.adicionarVeiculo(v2);
			listaVeiculos.deletarVeiculo(null);
		}catch(Exception e ) {
			assertEquals("Foi inserida uma placa nula na busca",e.getMessage());
		}
		
		assertTrue(listaVeiculos.getListaDeVeiculos().size()==2);
				
	}
	
	public void deletarVeiculoForaDaLista() {
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		Veiculo v1 , v2;
		try {
			v1 = new Veiculo("Mobi","GFD-4578","Fiat");
			v2 = new Veiculo("Palio","GFD-4579","Fiat");
			listaVeiculos.adicionarVeiculo(v1);
			listaVeiculos.adicionarVeiculo(v2);
			listaVeiculos.deletarVeiculo("KCY-7521");
		}catch(Exception e ) {
			assertEquals("Veiculo não encontrado",e.getMessage());
		}
		
		assertTrue(listaVeiculos.getListaDeVeiculos().size()==2);
				
	}
	
}
