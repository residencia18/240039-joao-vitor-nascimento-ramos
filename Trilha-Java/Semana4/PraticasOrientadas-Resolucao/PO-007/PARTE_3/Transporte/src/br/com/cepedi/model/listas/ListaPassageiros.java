package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaPassageiros.PassageiroJaCadastrado;
import br.com.cepedi.exceptions.listaPassageiros.PassageiroNaoEncontrado;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.pessoa.Pessoa;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;

public class ListaPassageiros extends ArrayList<Passageiro>  implements Serializable{
	
	private static final long serialVersionUID = 12L;

	public ListaPassageiros() {
		super();
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
	

	public Passageiro buscar(int id) throws PassageiroNaoEncontrado {
		
		
		for(Passageiro p : this) {
			if(p.getId()==id) {
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
				System.out.println("Passageiro excluido com sucesso!");
				return;
			}
		}
		throw new PassageiroNaoEncontrado();
	}
	
	
	public void mostraTodos() {
		for(Passageiro p : this) {
			System.out.println(p);
		}
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
