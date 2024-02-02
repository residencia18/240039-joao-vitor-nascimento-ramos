package br.com.cepedi;

import java.util.Objects;

public class Manga implements Comparable<Manga> {
	
	private Long id;
	private String nome;
	private double preco;
	int quantidade;
	
	
	public Manga(Long id, String nome, double preco) {
		Objects.requireNonNull(id,"O id não pode ser nulo");
		Objects.requireNonNull(nome,"O nome não pode ser nulo");
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Manga(Long id, String nome, double preco, int quantidade) {
		Objects.requireNonNull(id,"O id não pode ser nulo");
		Objects.requireNonNull(nome,"O nome não pode ser nulo");
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	@Override
	public String toString() {
		return "Manga [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}




	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manga other = (Manga) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public int compareTo(Manga o) {
		return this.nome.compareTo(o.nome);
	}
	


	

}
