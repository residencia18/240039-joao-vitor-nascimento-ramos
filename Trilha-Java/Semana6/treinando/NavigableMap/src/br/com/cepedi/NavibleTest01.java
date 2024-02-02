package br.com.cepedi;

import java.util.NavigableMap;
import java.util.TreeMap;

public class NavibleTest01 {
	public static void main(String[] args) {
		NavigableMap<String , String> map = new TreeMap<>();
		map.put("A", "Letra A");
        map.put("B", "Letra B");
        map.put("C", "Letra C");
        map.put("D", "Letra D");
        map.put("E", "Letra E");
        
        System.out.println(map.headMap("C").remove("A"));
        System.out.println(map.size());
        System.out.println(map.headMap("C",true));
        System.out.println(map.higherKey("C"));


	}

}
