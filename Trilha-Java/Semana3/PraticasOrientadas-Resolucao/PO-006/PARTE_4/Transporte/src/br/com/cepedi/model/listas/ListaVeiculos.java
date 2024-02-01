package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaVeiculos.PlacaJaCadastradaException;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.persistenciaListas.Persistencia;

public class ListaVeiculos extends ArrayList<Veiculo> implements Serializable {
	
	
	public static final String CAMINHO_ARQUIVO = "./Dados/veiculos.txt";
	private static final long serialVersionUID = 8L;


	public ListaVeiculos() {
		super();
		carregarListaDoArquivo();
		corrigeGeracaoID();

	}

    private void carregarListaDoArquivo() {
        ListaVeiculos listaLida = Persistencia.lerDoArquivo(CAMINHO_ARQUIVO);
        if (listaLida != null) {
            this.addAll(listaLida);
        }
    }
    
    private void corrigeGeracaoID() {
    	for(Veiculo v : this) {
    		if(v.getId()>Veiculo.numeroDeVeiculos) {
    			Veiculo.numeroDeVeiculos=v.getId();
    		}
    	}
    	    	
    }
	
	public void adicionar(Veiculo veiculo) throws PlacaJaCadastradaException {
		
		if(veiculo==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(placaJaCadastrada(veiculo.getPlaca())) {
			throw new PlacaJaCadastradaException();
		}
		add(veiculo);
		
	}
	
	public Veiculo buscar(String placa) throws VeiculoNaoEncontrado {
		
		if(placa==null) {
			throw new NullPointerException("Foi inserida uma placa nula na busca");
		}
		
		for(Veiculo v : this) {
			if(v.getPlaca().equals(placa)) {
				return v;
			}
		}
		throw new VeiculoNaoEncontrado();
	}
	
	public void deletar(String placa) throws VeiculoNaoEncontrado{
		
		if(placa==null) {
			throw new NullPointerException("Foi inserida uma placa nula na busca");
		}
		
		for(Veiculo v : this) {
			if(v.getPlaca().equals(placa)) {
				this.remove(v);
				System.out.println("Veiculo Excluido com sucesso!");
				return;
			}
		}
		throw new VeiculoNaoEncontrado();
	}
	
	
	public void mostraTodos() {
		for(Veiculo v : this) {
			System.out.println(v);
		}
	}
	
	private boolean placaJaCadastrada(String placa){
		for(Veiculo v : this) {
			if(v.getPlaca().equals(placa)) {
				return true;
			}
		}
		return false;
	}
	
	
	

}
