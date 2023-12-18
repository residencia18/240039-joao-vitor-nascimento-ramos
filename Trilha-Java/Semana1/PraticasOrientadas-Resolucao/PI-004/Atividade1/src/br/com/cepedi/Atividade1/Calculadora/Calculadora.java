package br.com.cepedi.Atividade1.Calculadora;

import java.util.ArrayList;

import br.com.cepedi.Atividade1.Exceptions.DivisionByZeroException;

public class Calculadora {
	
    public static int dividir(int numerador, int denominador) throws DivisionByZeroException {
        return (int)dividir((float)numerador ,(float) denominador);

    }

    public static float dividir(float numerador, float denominador) throws DivisionByZeroException {
        if (denominador == 0) {
            throw new DivisionByZeroException("Divisão por zero não permitida.");
        }
        return numerador / denominador;
    }

    public static int somar(int a, int b) {
        return (int)somar((float)a ,(float) b);
    }
    
    public static float somar(float a , float b) {
    	return a+b;
    }

    public static int subtrair(int a, int b) {
        return (int)subtrair((float)a ,(float) b);
    }
    
    public static float subtrair(float a , float b) {
    	return a -b ;
    }

    public static int multiplicar(int a, int b) {
        return (int)multiplicar((float)a ,(float) b);
    }
    
    public static float multiplicar(float a , float b) {
    	return a*b ;
    }
    
    
    public static float soma(ArrayList<Float> lista) {
    	float resultado = 0;
    	
    	for(float valor : lista) {
    		resultado = somar(resultado,valor);
    	}
    	
    	return resultado;
    }
    
    public static float multiplicar(ArrayList<Float> lista) {
    	float resultado = 1;
    	
    	for(float valor : lista) {
    		resultado = multiplicar(resultado,valor);
    	}
    	
    	return resultado;
    }
    

    
    
}

