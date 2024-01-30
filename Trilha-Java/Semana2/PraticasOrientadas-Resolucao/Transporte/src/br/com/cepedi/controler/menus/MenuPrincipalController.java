package br.com.cepedi.controler.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaEmbarques;
import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaJornadaTrajetoHorario;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.view.MenuPrincipalView;

public abstract class MenuPrincipalController {
	
	public static void selecionarAcao(Scanner sc) {
		ListaJornadas listaJornadas = new ListaJornadas();
		ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
		ListaPassageiros listaPassageiros = new ListaPassageiros();
		ListaPontosDeParada listaPontosDeParada = new ListaPontosDeParada();
		ListaTrechos listaTrechos = new ListaTrechos();
		ListaVeiculos listaVeiculos = new ListaVeiculos();
		ListaTrajetos listaTrajetos = new ListaTrajetos();
		ListaEmbarques listaEmbarques = new ListaEmbarques();
		ListaJornadaTrajetoHorario listaJornadaTrajetoHorario = new ListaJornadaTrajetoHorario();
		
		int escolha;
		
		do {
			
			escolha = MenuPrincipalView.selecionaMenu(sc);
			
			switch(escolha) {
			case 1:
				MenuVeiculosController.selecionarAcao(sc, listaVeiculos);
				break;
			case 2:
				MenuPassageirosController.selecionarAcao(sc, listaPassageiros);
				break;
			case 3:
				MenuFuncionariosController.selecionarAcao(sc, listaFuncionarios);
				break;
			case 4:
				MenuPontosDeParadaController.selecionarAcao(sc, listaPontosDeParada);
				break;
			case 5:
				MenuTrechosController.selecionarAcao(sc, listaPontosDeParada , listaTrechos);
				break;
			case 6:
				MenuTrajetosController.selecionarAcao(sc, listaTrajetos, listaTrechos);
				break;
			case 7:
				MenuJornadasController.selecionarAcao(sc, listaJornadas, listaFuncionarios, listaTrajetos , listaVeiculos,listaJornadaTrajetoHorario);
				break;
			case 8:
				MenuEmbarquesController.selecionarAcao(sc, listaPassageiros, listaJornadas, listaEmbarques);
				break;
			case 0:
				break;
				
			}
		}while(escolha!=0);
	}

}
