package br.com.cepedi.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Barco{
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




public class ClassesAnonimasTest02 {
	
	public static void main(String[] args) {
		List<Barco> lista = new ArrayList<>(List.of(new Barco("lancha"), new Barco("canoa")));
		lista.sort(new Comparator<Barco>() {
			@Override
			public int compare(Barco b1 , Barco b2) {
				return b1.getNome().compareTo(b2.getNome());
			}
		});
		
		
		System.out.println(lista);
	}

}
