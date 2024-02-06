package br.com.cepedi.controller.menus;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.transporte.Checkpoint;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;
import br.com.cepedi.persistenciaListasJSON.PersistenciaTrajetos;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuTrajetosController {
	
	public static void selecionarAcao(Scanner sc, ListaTrajetos trajetos , ListaTrechos trechos, ListaPontosDeParada pontos) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,trajetos,trechos , pontos);
				PersistenciaTrajetos.salvarArquivo(trajetos);
				break;
			case 2:
				mostra(sc,trajetos);
				break;
			case 3:
				listaTodos(trajetos);
				break;
			case 4:
				exclui(sc,trajetos);
				PersistenciaTrajetos.salvarArquivo(trajetos);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc, ListaTrajetos trajetos, ListaTrechos trechos , ListaPontosDeParada pontos) {
	    Trajeto trajeto = null;
	    Trecho trecho = null;
	    String escolhaContinue = "";
	    Checkpoint checkpoint;
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
	                checkpoint = defineCheckpoint(sc,trajeto,pontos);
	                trajeto.setCheckpoint(checkpoint);
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
	                	try {

	                	}catch(Exception ee ) {
	                		System.out.println(ee.getMessage());
	                		continue;
	                	}

	                    return;
	                }
	                continue;
	            }
	        }
	    } while (true);
	    

	}
	
	private static Checkpoint defineCheckpoint(Scanner sc , Trajeto trajeto , ListaPontosDeParada pontos) {
		int idPonto;
		Checkpoint checkpoint;
		PontoDeParada ponto;
		String escolhaContinue;
		int tempoDeslocamento;

		do {
			System.out.println("Defina o checkpoint do trajeto");
			System.out.println("As opcoes são : ");
			listaPontosDeParada(trajeto);
			try {
				System.out.println("Insira o id do ponto");
				idPonto = Integer.parseInt(sc.nextLine());
				ponto = pontos.buscar(idPonto);
				checkpoint = new Checkpoint(ponto);
				tempoDeslocamento = tempoAteOCheckpoint(trajeto,checkpoint);
				checkpoint.setHoraChegada(tempoDeslocamento);
				return checkpoint;
			} catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Deseja tentar a busca novamente (0 - para parar, qualquer outra tecla para continuar) ");
                escolhaContinue = sc.nextLine();
                if (escolhaContinue.equals("0")) {
                    return null; 
                }
                continue;
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
	
	private static void listaPontosDeParada(Trajeto trajeto) {
		Set<PontoDeParada> pontos = new HashSet<>();
		for(Trecho trecho : trajeto.getTrechos()) {
			pontos.add(trecho.getOrigem());
			pontos.add(trecho.getDestino());
		}
		
		for(PontoDeParada ponto : pontos) {
			System.out.println(ponto);
		}
	}
	
	private static PontoDeParada buscaPontoPorID(int id , Trajeto trajeto) {

		for(Trecho trecho : trajeto.getTrechos()) {
			if(trecho.getOrigem().getId()==id) {
				return trecho.getOrigem();
			}else if(trecho.getDestino().getId()==id) {
				return trecho.getDestino();
			}
		}
		
		return null;
	}
	
	private static int tempoAteOCheckpoint(Trajeto trajeto , Checkpoint checkpoint) {
		int tempo = 0;
		for(Trecho trecho : trajeto.getTrechos()) {
			if(trecho.getOrigem()==checkpoint.getPonto()) {
				return tempo;
			}else if(trecho.getDestino()==checkpoint.getPonto()) {
				tempo += trecho.getMinutos();
				return tempo;
			}else {
				tempo+=trecho.getMinutos();
			}
		}
		return tempo;
	}

}
