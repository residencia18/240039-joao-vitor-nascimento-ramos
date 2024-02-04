package br.com.service;

import java.util.ArrayList;
import java.util.List;

import br.com.dominio.Carro;

public class RentalService<T> {
	
	private List<T> objetosDisponiveis;
	
	public RentalService(List<T> objetosDisponiveis) {
		this.objetosDisponiveis = objetosDisponiveis;	
	}
	
	public T buscarObjetoDisponivel() {
		System.out.println("Buscando objeto disponivel");
		T c = objetosDisponiveis.remove(0);
		System.out.println("Alugando objeto : " + c);
		System.out.println("Objetos disponiveis pra alugar : ");
		System.out.println(objetosDisponiveis);
		return c;
	}
	
	public void retornarCarroAlugado(T objeto) {
		System.out.println("Devolvendo carro " + objeto);
		objetosDisponiveis.add(objeto);
		System.out.println("Objetos disponiveis pra alugar : ");
		System.out.println(objetosDisponiveis);
	}
	
}
