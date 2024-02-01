package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.persistenciaListas.Persistencia;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuTrechosController {
	

	public static void selecionarAcao(Scanner sc, ListaPontosDeParada listaPontosDeParada , ListaTrechos trechos, String CAMINHO_ARQUIVO ) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,listaPontosDeParada, trechos);
				Persistencia.salvarEmArquivo(trechos,CAMINHO_ARQUIVO);
				break;
			case 2:
				mostra(sc,trechos);
				break;
			case 3:
				listaTodos(trechos);
				Persistencia.salvarEmArquivo(trechos,CAMINHO_ARQUIVO);
				break;
			case 4:
				exclui(sc,trechos);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	
	}
	
	
	private static void cadastra(Scanner sc, ListaPontosDeParada listaPontosDeParada , ListaTrechos trechos ) {
		Trecho trecho;
		int minutos;
		String nomeOrigem, nomeDestino;
		String escolhaContinue = "";
		PontoDeParada origem , destino;
		do {
			try {
				System.out.println("Insira o nome do ponto de parada da origem");
				nomeOrigem = sc.nextLine();
				origem = listaPontosDeParada.buscar(nomeOrigem);
				System.out.println("Insira o nome do ponto de parada do destino");
				nomeDestino = sc.nextLine();
				destino = listaPontosDeParada.buscar(nomeDestino);
				System.out.println("Insita o tempo em minutos inteiros dessa transicao");
				minutos = Integer.parseInt(sc.nextLine());
				trecho = new Trecho(origem,destino,minutos);
				trechos.adiciona(trecho);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Trecho cadastrado com sucesso!");
			break;
			
		}while(true);
		
	}
	
	
	private static void mostra(Scanner sc, ListaTrechos trechos ) {
		Trecho trecho;
		String nomeOrigem, nomeDestino;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Insira o nome do ponto de parada da origem");
				nomeOrigem = sc.nextLine();
				System.out.println("Insira o nome do ponto de parada do destino");
				nomeDestino = sc.nextLine();
				trecho = trechos.buscar(nomeOrigem, nomeDestino);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(trecho);
			break;
			
		}while(true);
	}
	
	
	private static void listaTodos( ListaTrechos trechos ) {
		trechos.mostraTodos();
	}
	
	
	private static void exclui(Scanner sc , ListaTrechos trechos) {
		String nomeOrigem, nomeDestino;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Insira o nome do ponto de parada da origem");
				nomeOrigem = sc.nextLine();
				System.out.println("Insira o nome do ponto de parada do destino");
				nomeDestino = sc.nextLine();
				trechos.deletar(nomeOrigem, nomeDestino);
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
