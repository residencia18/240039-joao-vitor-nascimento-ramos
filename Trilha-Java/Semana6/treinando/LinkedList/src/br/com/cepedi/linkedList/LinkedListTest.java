package br.com.cepedi.linkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {

	public static void main(String[] args) {
		
		String[] colors = {"black" , "yellow" , "green" , "blue" , "violet" , "silver"};
		List<String> list1 = new LinkedList<>();
		
		for(String color : colors) {
			list1.add(color);
		}
		
		String[] colors2 = {"gold" , "white" , "brown" , "blue" , "gray" , "silver"};
		List<String> list2 = new LinkedList<>();
		
		for(String color : colors2) {
			list2.add(color);
		}
		
		list1.addAll(list2);
		list2 = null;
		
		printList(list1);
		convertToUpperCaseStrings(list1);
		System.out.println("----------------------");
		removeItens(list1,2,4);
		printList(list1);
//		printInverse(list1);

	}
	
	
	private static void printList(List<String> list) {
		for(String s : list) {
			System.out.println(s);
		}
	}
	
	private static void convertToUpperCaseStrings(List<String> list) {
		ListIterator<String> itera = list.listIterator();
		
		while(itera.hasNext()) {
			String color = itera.next();
			itera.set(color.toUpperCase());
 		}

	}
	
	private static void printInverse(List<String> list) {
		ListIterator<String> itera = list.listIterator(list.size());
		
		while(itera.hasPrevious()) {
			System.out.println(itera.previous());
		}
		
	}
	
	private static void removeItens(List<String> list, int start , int end) {
		list.subList(start, end).clear();
	}
	
	
}
