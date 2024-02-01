package br.com.cepedi.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
	
	public static void main(String[] args) {
		List<String> lista1 = new ArrayList();
		List<String> lista2 = new ArrayList();
		lista1.add("Vermelho");
		lista1.add("Azul");
		lista1.add("Verde");
		lista1.add("Roxo");
		lista1.add("Violeta");
		
		lista2.add("Azul");
		lista2.add("Verde");
		lista2.add("Roxo");
		
		removeEquals(lista1,lista2);
		mostraCollection(lista1);
		
	}
	
	private static void removeEquals(Collection<String> collection1  , Collection<String> collection2) {
		Iterator<String> iterator = collection1.iterator();
		
		while(iterator.hasNext()) {
			if(collection2.contains(iterator.next())) iterator.remove();
		}
	}
	
	private static void mostraCollection(Collection<String> collection) {
		for(String valor : collection) {
			System.out.println(valor);
		}
	}
	

}
