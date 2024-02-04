package br.com.service;

import java.util.ArrayList;
import java.util.List;

import br.com.dominio.Carro;

public class MetodosGenerics {
	
	public static void main(String[] args) {
		System.out.println(criarList(new Carro("BMW")));
	}
	
	private static <T> List<T> criarList(T obj) {
		List<T> lista = new ArrayList<>();
		lista.add(obj);
		return lista;
	}

}
