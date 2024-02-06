package br.com.cepedi.model.transporte;

import java.io.Serializable;
import java.util.Objects;

public class PontoDeParada  implements Serializable {
	
	private static final long serialVersionUID = 19L;

	
	public static int qtdPontosParada = 0;
	
	
	private int id;
	private String nome;
	
	

	public PontoDeParada(String nome) {
		super();
		setNome(nome);
		qtdPontosParada++;
		this.id = qtdPontosParada;
	}
	
	public PontoDeParada(int id , String nome) {
		super();
		setNome(nome);
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null) {
			throw new NullPointerException("Foi inserido um nome nulo");
		}
		if(nome.isEmpty()) {
			throw new IllegalArgumentException("Foi inserido um nome vazio");
		}
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PontoDeParada [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PontoDeParada other = (PontoDeParada) obj;
		return Objects.equals(nome, other.nome);
	}


	
	
	
	
	
	

}
