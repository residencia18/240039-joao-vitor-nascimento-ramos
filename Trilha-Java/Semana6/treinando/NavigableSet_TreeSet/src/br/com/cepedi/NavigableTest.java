package br.com.cepedi;

import java.util.NavigableSet;
import java.util.TreeSet;



public class NavigableTest {
	
	public static void main(String[] args) {
        NavigableSet<Smartphone> set = new TreeSet<>();
        
        Smartphone smartphone3 = new Smartphone("789", "iPhone");
        set.add(smartphone3);

        Smartphone smartphone1 = new Smartphone("123", "Nokia");
        set.add(smartphone1);

        Smartphone smartphone2 = new Smartphone("456", "Samsung");
        set.add(smartphone2);

        Smartphone smartphone4 = new Smartphone("456", "Samsung");
        set.add(smartphone4);
        
        for(Smartphone s : set.descendingSet()) {
        	System.out.println(s);
        }
	}

}
