package br.com.cepedi.sorted;

import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

public class SortedsetTest {

	public static void main(String[] args) {
		String[] colors = {"yellow","green","black","tan","gray","white",
				"orange","red","green"};
		SortedSet<String> tree = new TreeSet<>(Arrays.asList(colors));
		printSet(tree,System.out::println);
		System.out.println("-----------------------------");
		printSet(tree.headSet("orange"),System.out::println);
		System.out.println("-----------------------------");
		printSet(tree.tailSet("orange"),System.out::println);
		System.out.println("-----------------------------");
		System.out.println(tree.first());
		System.out.println("-----------------------------");
		System.out.println(tree.last());

	}
	
	private static <T> void  printSet(Set<T> conjunto , Consumer<T> c) {
		conjunto.forEach(c);
	}

}
	

