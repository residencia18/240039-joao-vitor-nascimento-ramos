package br.com.cepedi;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Consumidor {
	
	private Long id;
	private String nome;
	
	public Consumidor(String nome) {
		super();
		this.id = ThreadLocalRandom.current().nextLong(0,1000000);
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumidor other = (Consumidor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Consumidor [id=" + id + ", nome=" + nome + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
