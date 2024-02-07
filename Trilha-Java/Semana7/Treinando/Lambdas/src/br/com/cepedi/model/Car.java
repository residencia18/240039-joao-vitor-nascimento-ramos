package br.com.cepedi.model;

public class Car {
	
	private String nome = "JEEP";
	private String cor;
	private int ano;
	
	
	public Car(String cor, int ano) {
		super();
		this.cor = cor;
		this.ano = ano;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Car [nome=" + nome + ", ano=" + ano + "]";
	}
	
	

}
