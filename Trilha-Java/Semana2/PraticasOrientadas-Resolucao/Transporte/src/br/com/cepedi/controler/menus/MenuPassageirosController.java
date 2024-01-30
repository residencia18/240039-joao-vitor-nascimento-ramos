package br.com.cepedi.controler.menus;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.view.MenuPassageirosView;

public abstract class MenuPassageirosController {
	
	public static void selecionarAcao(Scanner sc, ListaPassageiros passageiros) {

		int escolha;
		
		do {
			escolha = MenuPassageirosView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,passageiros);
				break;
			case 2:
				mostra(sc,passageiros);
				break;
			case 3:
				listaTodos(passageiros);
				break;
			case 4:
				exclui(sc,passageiros);
				break;
			case 5:
				recarrega(sc,passageiros);
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc , ListaPassageiros passageiros) {
		
		Passageiro passageiro = null;
		String nome = null, cpf= null;
		int numCartao = 0;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome do passageiro");
				nome = sc.nextLine();
				System.out.println("Digite o cpf do passageiro");
				cpf = sc.nextLine();
				System.out.println("Qual Cartão usa ? (1 - Transporte , 2 - Estudantil , 3 - Idoso)");
				numCartao = Integer.parseInt(sc.nextLine());
				passageiro = new Passageiro(nome,cpf,numCartao);
				passageiros.adiciona(passageiro);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Passageiro cadastrado com sucesso!");
			break;
			
		}while(true);
	}
	
	private static void mostra(Scanner sc , ListaPassageiros passageiros) {
		Passageiro passageiro = null;
		String cpf= null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o cpf do passageiro que deseja buscar");
				cpf = sc.nextLine();
				passageiro = passageiros.buscar(cpf);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(passageiro);
			break;
			
		}while(true);
	}
	
	private static void listaTodos( ListaPassageiros passageiros) {
		passageiros.mostraTodos();
	}
	
	private static void exclui(Scanner sc ,  ListaPassageiros passageiros) {
		String cpf= null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o cpf do passageiro que deseja excluir");
				cpf = sc.nextLine();
				passageiros.deletar(cpf);
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
	
	private static void recarrega(Scanner sc , ListaPassageiros passageiros) {
		Passageiro passageiro = null;
		String cpf= null;
		String escolhaContinue = "";
		String valor;
		do {
			try {
				System.out.println("Digite o cpf do passageiro");
				cpf = sc.nextLine();
				passageiro = passageiros.buscar(cpf);
			    System.out.println("Qual valor a ser recarregado ? ");
			    valor = sc.nextLine();
			    passageiro.getCartao().recarregar(new BigDecimal(valor));
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Recarga feita com sucesso!");
			break;
			
		}while(true);
	}

}
