package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;
import br.com.cepedi.persistenciaListasJSON.PersistenciaPontosDeParada;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuPontosDeParadaController {
	
	public static void selecionarAcao(Scanner sc, ListaPontosDeParada listaPontosDeParada) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,listaPontosDeParada);
				PersistenciaPontosDeParada.salvarArquivo(listaPontosDeParada);
				break;
			case 2:
				mostra(sc,listaPontosDeParada);
				break;
			case 3:
				listaTodos(listaPontosDeParada);
				break;
			case 4:
				exclui(sc,listaPontosDeParada);
				PersistenciaPontosDeParada.salvarArquivo(listaPontosDeParada);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc , ListaPontosDeParada listaPontosDeParada) {
		PontoDeParada ponto = null;
		String nome;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Insira o nome do ponto de parada ");
				nome = sc.nextLine();
				ponto = new PontoDeParada(nome);
				listaPontosDeParada.adiciona(ponto);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Ponto de parada cadastrado com sucesso!");
			break;
			
		}while(true);
	}
	
	private static void mostra(Scanner sc , ListaPontosDeParada listaPontosDeParada) {
		PontoDeParada ponto = null;
		String nome;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome do ponto que deseja buscar");
				nome = sc.nextLine();
				ponto = listaPontosDeParada.buscar(nome);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(ponto);
			break;
			
		}while(true);
	}
	
	private static void listaTodos(ListaPontosDeParada listaPontosDeParada) {
		listaPontosDeParada.mostraTodos();
	}
	
	private static void exclui(Scanner sc , ListaPontosDeParada listaPontosDeParada) {
		PontoDeParada ponto = null;
		String nome= null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome do ponto que deseja excluir");
				nome = sc.nextLine();
				listaPontosDeParada.deletar(nome);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a exclusão novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			break;
			
		}while(true);
	}
	

}
