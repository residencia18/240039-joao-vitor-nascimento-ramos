package br.com.cepedi;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
        
        ArrayList<Object> lista = new ArrayList<>();
        Integer a = 48;
        Float f = 48.5F;
        
        lista.add(a);
        lista.add(f);
        lista.add("Meu Pau");
        
        for (Object elemento : lista) {
            System.out.println(elemento);
        }
        
        ArrayList<Float> floats = new ArrayList<>();
        floats.add(4.8F);
        floats.add(5.8F);
        
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Oi amigo");
        strings.add("Qual é");
        
        
        ArrayList<?> novaLista = new ArrayList<>(strings);
        
        System.out.println(novaLista);
        

    }

}
