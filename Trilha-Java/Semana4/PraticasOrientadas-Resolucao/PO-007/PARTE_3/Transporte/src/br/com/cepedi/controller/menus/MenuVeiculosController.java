package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.persistenciaListasArquivos.Persistencia;
import br.com.cepedi.persistenciaListasJSON.PersistenciaVeiculos;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuVeiculosController {
	
	

	
	public static void selecionarAcao(Scanner sc, ListaVeiculos veiculos) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,veiculos);
				PersistenciaVeiculos.salvarArquivo(veiculos);
				break;
			case 2:
				mostra(sc,veiculos);
				break;
			case 3:
				listaTodos(veiculos);
				break;
			case 4:
				exclui(sc,veiculos);
				PersistenciaVeiculos.salvarArquivo(veiculos);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc , ListaVeiculos veiculos) {
		
		Veiculo veiculo = null;
		String modelo = null, placa = null ,montadora = null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome do modelo do veiculo");
				modelo = sc.nextLine();
				System.out.println("Digite a placa do veiculo");
				placa = sc.nextLine();
				System.out.println("Digite o nome da montadora do veiculo");
				montadora = sc.nextLine();
				veiculo =  new Veiculo(modelo,placa,montadora);
				veiculos.adicionar(veiculo);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Veiculo cadastrado com sucesso!");
			break;
			
		}while(true);
	}
	
	private static void mostra(Scanner sc , ListaVeiculos veiculos) {
		Veiculo veiculo = null;
		String placa;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite a placa do veiculo que deseja buscar");
				placa = sc.nextLine();
				veiculo = veiculos.buscar(placa);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(veiculo);
			break;
			
		}while(true);
	}
	
	private static void listaTodos(ListaVeiculos veiculos) {
		veiculos.mostraTodos();
	}
	
	private static void exclui(Scanner sc , ListaVeiculos veiculos) {
		String placa;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite a placa do veiculo que deseja buscar");
				placa = sc.nextLine();
				veiculos.deletar(placa);
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
