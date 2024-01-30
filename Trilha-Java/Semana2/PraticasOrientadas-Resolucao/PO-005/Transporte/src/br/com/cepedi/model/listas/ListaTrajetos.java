package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.model.transporte.Trajeto;

public class ListaTrajetos extends ArrayList<Trajeto>{

	
	public void adiciona(Trajeto trajeto) throws TrajetoJaCadastrado  {
		
		if(trajeto==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(trajetoJaCadastrado(trajeto)) {
			throw new TrajetoJaCadastrado();
		}

		add(trajeto);
			
	}

	public Trajeto buscar(int id) throws TrajetoNaoEncontrado {
		
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		for(Trajeto t : this) {
			if(t.getId()==id) {
				return t;
			}
		}
		
		throw new TrajetoNaoEncontrado();
	}
	
	
	public void deletar(int id) throws TrajetoNaoEncontrado{
		
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		
		for(Trajeto t : this) {
			if(t.getId()==id) {
				this.remove(t);
				System.out.println("Trajeto excluido com sucesso!");
				return;
			}
		}
		

		throw new TrajetoNaoEncontrado();
	}
	
	
	public void mostraTodos() {
		for(Trajeto v : this) {
			System.out.println(v);
		}
	}
	
	
	private boolean trajetoJaCadastrado(Trajeto trajeto) {
		for(Trajeto t : this) {
			if(trajetoIgual(t,trajeto)) {
				return true;
			}
		}
		return false;
	}

	private boolean trajetoIgual(Trajeto t1 , Trajeto t2) {
		int tamanhoTrajetosT1 = t1.getTrechos().size();
		int qtdIguais=0;
		
		
		if(tamanhoTrajetosT1!=t2.getTrechos().size()) {
			return false;
		}
		
		
		for(int i = 0 ; i < tamanhoTrajetosT1 ; i++) {
			if(t1.getTrechos().get(i).equals(t2.getTrechos().get(i))) {
				qtdIguais++;
			}
		}
		
		return qtdIguais==tamanhoTrajetosT1;
	}
	
	
}
