package br.com.dominio;

public class Barco {
	
	private String nome;

	public Barco(String nome) {
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
		return "Barco [nome=" + nome + "]";
	}
	
	

}
