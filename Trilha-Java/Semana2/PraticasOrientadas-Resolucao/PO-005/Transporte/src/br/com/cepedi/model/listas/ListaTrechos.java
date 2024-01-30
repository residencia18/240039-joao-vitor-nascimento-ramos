package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaTrechos.TrechoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.model.veiculo.Veiculo;

public class ListaTrechos extends ArrayList<Trecho> {
	
	public ListaTrechos() {
		
	}
	
	
	public void adiciona(Trecho trecho) throws TrechoJaCadastrado{
		if(trecho==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(trechoJaCadastrado(trecho.getOrigem().getNome(),trecho.getDestino().getNome())) {
			throw new TrechoJaCadastrado();
		}
		
		add(trecho);
		
	}
	
	public Trecho buscar(String origem, String destino) throws TrechoNaoEncontrado{
		if(origem==null || destino==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		for(Trecho t : this) {
			if(t.getOrigem().getNome().equals(origem) && t.getDestino().getNome().equals(destino)) {
				return t;
			}
		}
		
		throw new TrechoNaoEncontrado();
	}
	
	public void deletar(String origem, String destino) throws TrechoNaoEncontrado{
		if(origem==null || destino==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		for(Trecho t : this) {
			if(t.getOrigem().getNome().equals(origem) && t.getDestino().getNome().equals(destino)) {
				this.remove(t);
				System.out.println("Trecho excluido com sucesso!");
				return;
			}
		}
		
		throw new TrechoNaoEncontrado();
	}
	
	public void mostraTodos() {
		for(Trecho v : this) {
			System.out.println(v);
		}
	}
	
	

	
	private boolean trechoJaCadastrado(String origem, String destino) {
		for(Trecho t : this) {
			if(t.getOrigem().getNome().equals(origem) && t.getDestino().getNome().equals(destino)) {
				return true;
			}
		}
		return false;
	}

}
