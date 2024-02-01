package br.com.cepedi.controller.menus;

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
	
	public static final String CAMINHO_ARQUIVO_TRECHOS = "./Dados/trechos.txt";
	public static final String CAMINHO_ARQUIVO_TRAJETOS = "./Dados/trajetos.txt";
	public static final String CAMINHO_ARQUIVO_JORNADAS = "./Dados/jornadas.txt";

	
	public static void selecionarAcao(Scanner sc) {
		

		ListaVeiculos listaVeiculos = new ListaVeiculos();
		ListaPassageiros listaPassageiros = new ListaPassageiros();
		ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
		ListaPontosDeParada listaPontosDeParada = new ListaPontosDeParada();
		
		ListaTrechos listaTrechos = new ListaTrechos();
		listaTrechos.carregarListaDoArquivo(CAMINHO_ARQUIVO_TRECHOS);
		ListaTrajetos listaTrajetos = new ListaTrajetos();
		listaTrajetos.carregarListaDoArquivo(CAMINHO_ARQUIVO_TRAJETOS);
		ListaJornadas listaJornadas = new ListaJornadas();
		listaJornadas.carregarListaDoArquivo(CAMINHO_ARQUIVO_JORNADAS);
		


		
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
				MenuTrechosController.selecionarAcao(sc, listaPontosDeParada , listaTrechos,CAMINHO_ARQUIVO_TRECHOS);
				break;
			case 6:
				MenuTrajetosController.selecionarAcao(sc, listaTrajetos, listaTrechos ,listaPontosDeParada,CAMINHO_ARQUIVO_TRAJETOS);
				break;
			case 7:
				MenuJornadasController.selecionarAcao(sc, listaJornadas, listaFuncionarios, listaTrajetos , listaVeiculos,listaJornadaTrajetoHorario,CAMINHO_ARQUIVO_JORNADAS);
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
