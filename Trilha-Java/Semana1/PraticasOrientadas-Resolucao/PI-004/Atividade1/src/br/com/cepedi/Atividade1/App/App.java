package br.com.cepedi.Atividade1.App;

import java.util.ArrayList;

import br.com.cepedi.Atividade1.Calculadora.Calculadora;
import br.com.cepedi.Atividade1.Exceptions.DivisionByZeroException;

public class App {
	
	public static void main(String[] args) {
		
		System.out.println("Soma (int): " + Calculadora.somar(5, 3));
        System.out.println("Soma (float): " + Calculadora.somar(5.0f, 3.5f));

        System.out.println("Subtração (int): " + Calculadora.subtrair(8, 4));
        System.out.println("Subtração (float): " + Calculadora.subtrair(8.0f, 4.5f));

        System.out.println("Multiplicação (int): " + Calculadora.multiplicar(3, 7));
        System.out.println("Multiplicação (float): " + Calculadora.multiplicar(3.0f, 7.5f));

        try {
            System.out.println("Divisão (int): " + Calculadora.dividir(10, 2));
            System.out.println("Divisão (float): " + Calculadora.dividir(10.0f, 2.5f));
        } catch (DivisionByZeroException e) {
            System.out.println("Erro de divisão por zero: " + e.getMessage());
        }


        ArrayList<Float> listaNumeros = new ArrayList<>();
        listaNumeros.add(0.5f);
        listaNumeros.add(5.5f);
        listaNumeros.add(10.5f);
        

        
        System.out.println("Soma da lista floats: " + Calculadora.soma(listaNumeros));
        System.out.println("Multiplicação da lista floats: " + Calculadora.multiplicar(listaNumeros));
    
		
	}

}
