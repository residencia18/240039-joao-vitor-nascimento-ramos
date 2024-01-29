package br.com.cepedi.controler.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.view.MenuPrincipalView;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuPrincipalController {
	
	public static void selecionarAcao(Scanner sc) {
		ListaJornadas listaJornadas = new ListaJornadas();
		ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
		ListaPassageiros listaPassageiros = new ListaPassageiros();
		ListaPontosDeParada listaPontosDeParada = new ListaPontosDeParada();
		ListaTrechos listaTrechos = new ListaTrechos();
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		ListaTrajetos listaTrajetos = new ListaTrajetos();
		int escolha;
		
		do {
			
			escolha = MenuPrincipalView.selecionaMenu(sc);
			
			switch(escolha) {
			case 1:
				MenuVeiculosController.selecionarAcao(sc, listaVeiculos);
				break;
			case 2:
				//menu Passageiros;
				break;
			case 3:
				//menu Funcionarios;
				break;
			case 4:
				//menu Pontos de parada;
				break;
			case 5:
				//menu Trechos;
				break;
			case 6:
				//menu Trajetos;
				break;
			case 7:
				//menu Jornadas;
				break;
			case 0:
				//sair;
					break;
				
			}
		}while(escolha!=0);
	}

}
