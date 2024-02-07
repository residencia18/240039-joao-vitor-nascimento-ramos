package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.conjuntos.Clientes;
import br.com.cepedi.model.Cliente;
import br.com.cepedi.views.MenuClientesView;

public abstract class MenuClientesController {
	
	public static void selecionarAcao(Scanner sc, Clientes clientes) {

		int escolha;
		
		do {
			escolha = MenuClientesView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,clientes);
				break;
			case 2:
				mostra(sc,clientes);
				break;
			case 3:
				listaTodos(clientes);
				break;
			case 4:
				listaTodosOrdenadoPorNome(clientes);
				break;
			case 5:
				atualizar(sc,clientes);
				break;
			case 6:
				deletar(sc,clientes);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	
	private static void cadastra(Scanner sc , Clientes clientes) {
		Cliente cliente;
		String nome,cpf;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome : ");
				nome = sc.nextLine();
				System.out.println("Digite o CPF : ");
				cpf = sc.nextLine();
				cliente = new Cliente(nome,cpf);
				clientes.adicionar(cliente);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			break;
			
		}while(true);
	}
	
	private static void mostra(Scanner sc , Clientes clientes) {
		Cliente cliente;
		int id;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o id do cliente que deseja buscar");
				id = Integer.parseInt(sc.nextLine());
				cliente = clientes.buscar(id);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(cliente);
			break;
			
		}while(true);
	}
	
	private static void listaTodos(Clientes clientes) {
		clientes.listar();
	}
	
	
	private static void listaTodosOrdenadoPorNome(Clientes clientes){
		clientes.listarOrdenadoPorNome();;
	}
	
	private static void deletar(Scanner sc , Clientes clientes) {
			Cliente cliente = null;
			int id;
			String escolhaContinue = "";
			do {
				try {
					System.out.println("Digite o id do cliente que deseja buscar :");
					id = Integer.parseInt(sc.nextLine());
					clientes.deletar(id);
				}catch(Exception e ) {
					System.out.println(e.getMessage());
					System.out.println("Deseja tentar a exclusão novamente (0 - para parar , qualquer outra tecla para continuar) ");
					if(escolhaContinue.equals("0")) {
						return;
					}
					continue;
				}
				
				break;
				
			}while(true);
	}
	
	private static void atualizar(Scanner sc, Clientes clientes) {
        int id;
        String escolhaContinue = "";
        Cliente cliente;

        do {
            try {
                System.out.println("Digite o id do cliente que deseja atualizar: ");
                id = Integer.parseInt(sc.nextLine());
                cliente = clientes.buscar(id);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
                escolhaContinue = sc.nextLine();
                if (escolhaContinue.equals("0")) {
                    return;
                }
            }
        } while (true);

        String atributo;
        do {
            System.out.println("Qual atributo deseja atualizar? (nome ou CPF)");
            atributo = sc.nextLine().toLowerCase();
        } while (!atributo.equals("nome") && !atributo.equals("cpf"));

        String novoValor;
        do {
            System.out.println("Digite o novo valor para " + atributo + ": ");
            novoValor = sc.nextLine();
        } while (novoValor.isEmpty());

        try {
            clientes.atualizar(id, novoValor, atributo);
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
