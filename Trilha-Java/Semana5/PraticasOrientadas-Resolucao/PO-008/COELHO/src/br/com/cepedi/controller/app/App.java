package br.com.cepedi.controller.app;

import java.util.Scanner;

import br.com.cepedi.controller.menus.MenuPrincipalController;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		MenuPrincipalController.selecionarAcao(sc);
		

		
	}
	
}
