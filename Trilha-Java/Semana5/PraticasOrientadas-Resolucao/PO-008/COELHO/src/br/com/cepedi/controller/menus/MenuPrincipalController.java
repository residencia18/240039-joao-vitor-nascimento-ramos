package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.conjuntos.Clientes;
import br.com.cepedi.conjuntos.Imoveis;
import br.com.cepedi.views.MenuPrincipalView;

public abstract class MenuPrincipalController {
	public static void selecionarAcao(Scanner sc) {

		Clientes clientes = new Clientes();
		Imoveis imoveis = new Imoveis();
		
		
		int escolha;
		
		do {
			
			escolha = MenuPrincipalView.selecionaMenu(sc);
			
			switch(escolha) {
			case 1:
				MenuClientesController.selecionarAcao(sc, clientes);
				break;
			case 2:
				MenuImoveisController.selecionarAcao(sc, imoveis, clientes);
				break;
			case 0:
				break;
				
			}
		}while(escolha!=0);
	}
}
