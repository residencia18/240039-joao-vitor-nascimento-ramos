package com.controleacademico.controleacademico.controller;

import java.util.ArrayList;
import java.util.Random;

public abstract class PrimosUtil {
	
    public static ArrayList<Integer> geraAletoriosPrimosEmSequencia(){
        ArrayList<Integer> numerosPrimos = new ArrayList<>();

        for (int i = 2; i <= 30; i++) {
            if (isPrimo(i)) {
                numerosPrimos.add(i);
            }
        }

        return numerosPrimos;
    }

    protected static boolean isPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }
    
    protected static boolean isPrimo(Long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= Math.sqrt(n)) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }
    
    

}
