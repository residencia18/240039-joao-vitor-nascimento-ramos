package br.com.cepedi.views;

import java.util.Scanner;

public abstract class MenuFaturasView {
	
	public static int selecionaAcao(Scanner sc ) {
		int op;
		do {
			op=-1;
			mostraMenu();
			try {
				op = Integer.parseInt(sc.nextLine());
				if(op < 0 || op > 3) {
					throw new IllegalArgumentException("Escolha inválida");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			
		}while(op < 0 || op > 3);
		
		return op;
	}
	
	private static void mostraMenu() {
		System.out.println("-----MENU GESTÃO FATURAS-----");
		System.out.println("1 - Registra nova leitura");
		System.out.println("2 - Lista Todas as faturas de um imovel");
		System.out.println("3 - Lista Todas as faturas em aberto de um imovel");
		System.out.println("0 - Sair");
	}
	

}
