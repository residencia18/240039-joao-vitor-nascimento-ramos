package br.com.cepedi;

import java.util.List;


public class WildCardTest02 {

	public static void main(String[] args) {
		List<Cachorro> cachorros = List.of(new Cachorro() , new Cachorro());
		List<Gato> gatos = List.of(new Gato() , new Gato());
		printConsultaAnimal(cachorros);
	}
	
	private static void printConsulta(List<? extends Animal> animais) {
		for (Animal animal : animais) {
			animal.consulta();
		}
	}
	
	private static void printConsultaAnimal(List<? super  Cachorro> animais) {
		for (Object animal : animais) {
			if(animal instanceof Cachorro) {
				Cachorro a = (Cachorro) animal;
				a.consulta();
			}
		}
	}
	
}
