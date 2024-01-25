package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaPassageiros.PassageiroJaCadastrado;
import br.com.cepedi.exceptions.listaPassageiros.PassageiroNaoEncontrado;
import br.com.cepedi.model.pessoa.Passageiro;

public class ListaPassageiros extends ArrayList<Passageiro>{
	
	public ListaPassageiros() {
		
	}
	
	public void adiciona(Passageiro passageiro) throws PassageiroJaCadastrado {
		
		if(passageiro==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(passageiroJaCadastrado(passageiro.getCPF())) {
			throw new PassageiroJaCadastrado();
		}

		add(passageiro);
			
	}

	public Passageiro buscar(String cpf) throws PassageiroNaoEncontrado {
		
		if(cpf==null) {
			throw new NullPointerException("Foi inserido um cpf nulo na busca");
		}
		
		for(Passageiro p : this) {
			if(p.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				return p;
			}
		}
		
		throw new PassageiroNaoEncontrado();
	}
	
	
	public void deletar(String cpf) throws PassageiroNaoEncontrado{
		
		if(cpf==null) {
			throw new NullPointerException("Foi inserido um cpf nulo na busca");
		}
		
		for(Passageiro p : this) {
			if(p.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				this.remove(p);
				return;
			}
		}
		throw new PassageiroNaoEncontrado();
	}
	
	
	private boolean passageiroJaCadastrado(String cpf) {
		for(Passageiro p : this) {
			if(p.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				return true;
			}
		}
		return false;
	}
	
	

}
