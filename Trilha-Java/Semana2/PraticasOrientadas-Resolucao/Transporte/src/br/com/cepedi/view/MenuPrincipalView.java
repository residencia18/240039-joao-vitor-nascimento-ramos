package br.com.cepedi.view;

import java.util.Scanner;

public abstract class MenuPrincipalView {
	
	public static int selecionaMenu(Scanner sc ) {
		int op;
		do {
			op=-1;
			mostraMenu();
			try {
				op = Integer.parseInt(sc.nextLine());
				if(op < 0 || op > 8) {
					throw new IllegalArgumentException("Escolha inválida");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			
		}while(op < 0 || op > 8);
		
		return op;
	}
	
	private static void mostraMenu() {
		System.out.println("-----SISTEMA DE VIAGENS COELHO-----");
		System.out.println("1 - Menu Veiculos");
		System.out.println("2 - Menu Passageiros");
		System.out.println("3 - Menu Funcionarios");
		System.out.println("4 - Menu Pontos de Parada");
		System.out.println("5 - Menu Trechos");
		System.out.println("6 - Menu Trajetos");
		System.out.println("7 - Menu Jornadas");
		System.out.println("8 - Menu Embarque");
		System.out.println("0 - Sair");
	}

}
