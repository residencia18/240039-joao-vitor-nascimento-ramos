package br.com.cepedi.view;

import java.util.Scanner;

public abstract class MenuCRUDView {
	
	public static int selecionaAcao(Scanner sc ) {
		int op;
		do {
			op=-1;
			mostraMenu();
			try {
				op = Integer.parseInt(sc.nextLine());
				if(op < 0 || op > 4) {
					throw new IllegalArgumentException("Escolha inválida");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			
		}while(op < 0 || op > 4);
		
		return op;
	}
	
	private static void mostraMenu() {
		System.out.println("-----MENU GESTÃO SECUNDARIO-----");
		System.out.println("1 - Cadastra");
		System.out.println("2 - Busca");
		System.out.println("3 - Lista Todos");
		System.out.println("4 - Exclui");
		System.out.println("0 - Sair");
	}


}
