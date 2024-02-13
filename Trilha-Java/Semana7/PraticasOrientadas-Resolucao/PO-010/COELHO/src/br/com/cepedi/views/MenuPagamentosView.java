package br.com.cepedi.views;

import java.util.Scanner;

public abstract class MenuPagamentosView {
	
	
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
		System.out.println("1 - Registra pagamento");
		System.out.println("2 - Lista Todas os pagamentos de uma determinada fatura");
		System.out.println("3 - Mostra pagamento com reembolso de determinada fatura");
		System.out.println("0 - Sair");
	}
	

}
