package com.controleacademico.controleacademico.controller;

import java.util.ArrayList;
import java.util.Random;

public class AleatoriosUtil {
	
	public static ArrayList<Integer> geraAletoriosEmSequencia(){
        ArrayList<Integer> numerosSequenciais = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numerosSequenciais.add(i);
        }

        return numerosSequenciais;
    }
    
    public static ArrayList<Integer> geraAletorios(){
        ArrayList<Integer> numerosAleatorios = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            numerosAleatorios.add(random.nextInt(100)); 
        }

        return numerosAleatorios;
    }

}
