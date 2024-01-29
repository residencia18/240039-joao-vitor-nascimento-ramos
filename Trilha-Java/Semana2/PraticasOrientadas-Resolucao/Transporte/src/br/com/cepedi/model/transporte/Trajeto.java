package br.com.cepedi.model.transporte;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaTrechos.TrechoJaCadastrado;
import br.com.cepedi.model.listas.ListaTrechos;

public class Trajeto {
	
	public static int qntdIDsGerados = 0;
	
	int id;
	LocalDateTime inicio;
	ListaTrechos trechos;
	String nome;
	
	public Trajeto(LocalDateTime inicio) {
		super();
		setInicio(inicio);
		qntdIDsGerados++;
		id = qntdIDsGerados;
		trechos = new ListaTrechos();
	}

	
	
	public String getNome() {
		return nome;
	}

	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
	    if (inicio == null) {
	        throw new IllegalArgumentException("A hora de inicio do trajeto não pode ser nulo");
	    }
	    this.inicio = inicio;
	}
	public ArrayList<Trecho> getTrechos() {
		return trechos;
	}


	public void setNome(String nome) {
		if(nome==null) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}
		
		if(nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
		this.nome = nome;
	}
	
	public void adicionaTrecho(Trecho trecho) throws TrechoJaCadastrado {
		String novoNome;
		if(trecho==null) {
			throw new IllegalArgumentException("O trecho não pode ser nulo");
		}
		this.trechos.adiciona(trecho);
		novoNome = this.trechos.get(0).getOrigem().getNome()+"-" + this.trechos.get(trechos.size()-1).getDestino().getNome();
		setNome(novoNome);

	}
	
	public void removeTrecho(Trecho trecho) {
		if(trechos.remove(trecho)) {
			System.out.println("Trecho removido com sucesso!");
		}else {
			throw new IllegalArgumentException("Trecho não encontrado no trajeto");
		}
	}
	
	public int tempoDeTodosTrechos() {
		int tempoTotal = 0;
		for(Trecho trecho : trechos) {
			tempoTotal += trecho.getMinutos();
		}
		return tempoTotal;
	}
	
	public String listaTodosOsTrechos() {
		String saida = "";
		for(Trecho trecho : trechos) {
			saida += trecho.toString()+"\n";
		}
		return saida;
	}
	
	public void mostraTodosOsTrechos() {
		if(trechos.isEmpty()) {
			System.out.println("Trajeto vazio");
		}
		System.out.println(this.listaTodosOsTrechos());
	}



	@Override
	public String toString() {
		return "Trajeto [id=" + id + ", inicio=" + inicio + ", trechos=" + trechos + ", nome=" + nome + "]";
	}
	
	
	
	
	

}
