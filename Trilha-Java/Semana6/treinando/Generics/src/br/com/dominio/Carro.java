package br.com.dominio;

public class Carro {
	
	private String nome;
	
	

	public Carro(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Carro [nome=" + nome + "]";
	}
	
	

}
