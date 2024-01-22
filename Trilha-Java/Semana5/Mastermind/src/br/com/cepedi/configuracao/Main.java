package br.com.cepedi.configuracao;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		
		Configuracao config = new Configuracao();
		Scanner sc = new Scanner(System.in);
		
		
	}
	
	public static int sorteiaLetra() {
        Random random = new Random();

		return random.nextInt(122-97)+97;
	}


}
