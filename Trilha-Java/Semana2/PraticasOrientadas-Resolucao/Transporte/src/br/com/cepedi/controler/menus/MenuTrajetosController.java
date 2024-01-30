package br.com.cepedi.controler.menus;

import java.util.Scanner;

import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuTrajetosController {
	
	public static void selecionarAcao(Scanner sc, ListaTrajetos trajetos , ListaTrechos trechos) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,trajetos,trechos);
				break;
			case 2:
				mostra(sc,trajetos);
				break;
			case 3:
				listaTodos(trajetos);
				break;
			case 4:
				exclui(sc,trajetos);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc, ListaTrajetos trajetos, ListaTrechos trechos) {
	    Trajeto trajeto = null;
	    Trecho trecho = null;
	    String escolhaContinue = "";

	    do {
	        try {
	            trecho = buscaDados(sc, trechos);
	            trajeto = new Trajeto();
	            trajeto.adiciona(trecho);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar a busca novamente (0 - para parar, qualquer outra tecla para continuar) ");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	            continue;
	        }
	        break;
	    } while (true);

	    do {
	        System.out.println("Deseja inserir um novo trecho? (0 - para parar, qualquer outra tecla para continuar)  ");
	        escolhaContinue = sc.nextLine();
	        if (escolhaContinue.equals("0")) {
	            try {
	            	trajetos.adiciona(trajeto);
	    	        System.out.println("Trajeto cadastrado com sucesso!");
	                break;
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        } else {
	            try {
	                trecho = buscaDados(sc, trechos);
	                trajeto.adiciona(trecho);
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	                System.out.println("Deseja tentar a busca novamente (0 - para parar, qualquer outra tecla para continuar) ");
	                escolhaContinue = sc.nextLine();
	                if (escolhaContinue.equals("0")) {
	                    return;
	                }
	                continue;
	            }
	        }
	    } while (true);
	    

	}


	private static Trecho buscaDados(Scanner sc, ListaTrechos trechos) throws TrechoNaoEncontrado {
		Trecho trecho;
		String nomeOrigem;
		String nomeDestino;
		System.out.println("Insira o nome do ponto de parada da origem ");
		nomeOrigem = sc.nextLine();
		System.out.println("Insira o nome do ponto de parada do destino ");
		nomeDestino = sc.nextLine();
		trecho = trechos.buscar(nomeOrigem, nomeDestino);
		return trecho;
	}
	
	private static void mostra(Scanner sc, ListaTrajetos trajetos) {
		Trajeto trajeto;
		String escolhaContinue = "";
		int idTrajeto;
				
		do {
			try {
				System.out.println("Digite o id do trajeto que deseja buscar");
				idTrajeto = Integer.parseInt(sc.nextLine());
				trajeto = trajetos.buscar(idTrajeto);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(trajeto);
			break;
		}while(true);

	}
	
	private static void listaTodos(ListaTrajetos trajetos) {
		trajetos.mostraTodos();
	}
	
	private static void exclui(Scanner sc, ListaTrajetos trajetos) {
		String escolhaContinue = "";
		int idTrajeto;
				
		do {
			try {
				System.out.println("Digite o id do trajeto que deseja excluir");
				idTrajeto = Integer.parseInt(sc.nextLine());
				trajetos.deletar(idTrajeto);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
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
