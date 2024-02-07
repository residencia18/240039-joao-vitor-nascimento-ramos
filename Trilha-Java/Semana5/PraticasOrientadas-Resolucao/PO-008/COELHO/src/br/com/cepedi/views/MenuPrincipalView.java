package br.com.cepedi.views;

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
		System.out.println("1 - Menu Clientes");
		System.out.println("2 - Menu Imoveis");
		System.out.println("0 - Sair");
	}

}
