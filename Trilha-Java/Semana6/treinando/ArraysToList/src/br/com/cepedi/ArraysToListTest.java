package br.com.cepedi;

import java.util.Arrays;
import java.util.LinkedList;

public class ArraysToListTest {
	
	public static void main(String[] args) {
		String[] colors = {"black","blue","Yellow"};
		LinkedList<String> links = new LinkedList(Arrays.asList(colors));
		
		links.addLast("red");
		links.add("pink");
		links.add(3,"green");
		links.addFirst("cyan");
		
		colors = links.toArray(new String[links.size()]);
		
		for(String color: colors) {
			System.out.println(color);
		}
		
	}

}
