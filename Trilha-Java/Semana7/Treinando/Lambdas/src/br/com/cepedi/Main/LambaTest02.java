package br.com.cepedi.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambaTest02 {
	
	public static void main(String[] args) {
		List<String> strings = List.of("Naruto","KAKAshi","PAIN" , "Vamos");
		List<Integer> integers = map(strings, (String s) -> s.length());
	 	List<String> map = map(strings , (String s ) -> s.toUpperCase());
	 	imprimeList(integers, (Integer i) -> System.out.println(i));
	 	imprimeList(map, (String i) -> System.out.println(i));

	}
	
	private static <T, R> List<R> map(List<T> list, Function <T,R> function){
		List<R> result = new ArrayList<>();
		for (T t : list) {
			R r = function.apply(t);
			result.add(r);
		}
		
		return result;
	}
	
	public static <T> void imprimeList(List<T> list , Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

}
