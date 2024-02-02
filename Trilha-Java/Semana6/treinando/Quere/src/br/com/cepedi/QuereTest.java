package br.com.cepedi;

import java.util.PriorityQueue;
import java.util.Queue;

public class QuereTest {
	
	public static void main(String[] args) {
		Queue<String> fila = new PriorityQueue<>();
		fila.add("C");
		fila.add("A");
		fila.add("B");
		for(String v : fila) {
			System.out.println(v);
		}
		System.out.println("-----------------------");
		System.out.println(fila.peek());
		System.out.println(fila.poll());
		System.out.println("-----------------------");
		fila.clear();

		for(String v : fila) {
			System.out.println(v);
		}
		
	}

}
