package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaVeiculos.PlacaJaCadastradaException;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.model.veiculo.Veiculo;

public class ListaVeiculos {
	
	ArrayList<Veiculo> listaDeVeiculos;

	public ListaVeiculos() {
		super();
		listaDeVeiculos = new ArrayList<>();
	}

	public ArrayList<Veiculo> getListaDeVeiculos() {
		return listaDeVeiculos;
	}
	
	public void adicionarVeiculo(Veiculo veiculo) throws PlacaJaCadastradaException {
		
		if(veiculo==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(placaJaCadastrada(veiculo.getPlaca())) {
			throw new PlacaJaCadastradaException();
		}
		this.listaDeVeiculos.add(veiculo);
		
	}
	
	public Veiculo buscarVeiculo(String placa) throws VeiculoNaoEncontrado {
		
		if(placa==null) {
			throw new NullPointerException("Foi inserida uma placa nula na busca");
		}
		
		for(Veiculo v : listaDeVeiculos) {
			if(v.getPlaca().equals(placa)) {
				return v;
			}
		}
		throw new VeiculoNaoEncontrado();
	}
	
	public void deletarVeiculo(String placa) throws VeiculoNaoEncontrado{
		
		if(placa==null) {
			throw new NullPointerException("Foi inserida uma placa nula na busca");
		}
		
		for(Veiculo v : listaDeVeiculos) {
			if(v.getPlaca().equals(placa)) {
				this.listaDeVeiculos.remove(v);
				return;
			}
		}
		throw new VeiculoNaoEncontrado();
	}
	
	private boolean placaJaCadastrada(String placa){
		for(Veiculo v : listaDeVeiculos) {
			if(v.getPlaca().equals(placa)) {
				return true;
			}
		}
		return false;
	}
	
	
	

}
