package br.com.cepedi.calculadoraDivisao.Calculadora;

import br.com.cepedi.calculadoraDivisao.Exceptions.DivisionByZeroException;

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

}
