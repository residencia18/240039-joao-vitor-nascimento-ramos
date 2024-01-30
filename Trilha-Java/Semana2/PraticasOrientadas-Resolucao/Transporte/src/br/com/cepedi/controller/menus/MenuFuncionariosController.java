package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Funcionario;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.view.MenuCRUDView;

public abstract class MenuFuncionariosController {
	
	public static void selecionarAcao(Scanner sc, ListaFuncionarios funcionarios) {

		int escolha;
		
		do {
			escolha = MenuCRUDView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,funcionarios);
				break;
			case 2:
				mostra(sc,funcionarios);
				break;
			case 3:
				listaTodos(funcionarios);
				break;
			case 4:
				exclui(sc,funcionarios);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc , ListaFuncionarios funcionarios) {
		Funcionario funcionario = null;
		String nome = null, cpf= null;
		String salario;
		int tipoFuncionario = 0;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o nome do funcionario");
				nome = sc.nextLine();
				System.out.println("Digite o cpf do funcionario");
				cpf = sc.nextLine();
				System.out.println("Digite o salario do funcionario");
				salario = sc.nextLine();
				do {
					System.out.println("O funcionario é motorista ou cobrador ? ( 1 para motorista , 2 para cobrador )");
					tipoFuncionario = Integer.parseInt(sc.nextLine());
					if(tipoFuncionario!=1 && tipoFuncionario!=2) {
						System.out.println("Tipo de funcionario inválido");
					}
				}while(tipoFuncionario!=1 && tipoFuncionario!=2);
				
				if(tipoFuncionario==1) {
					System.out.println("Qual o número da CNH do motorista ? ");
					String cnh = sc.nextLine();
					funcionario = new Motorista(nome,cpf,salario,cnh);
				}else {
					funcionario = new Cobrador(nome,cpf,salario);
				}
				
				funcionarios.adiciona(funcionario);

				
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			System.out.println("Funcionario cadastrado com sucesso!");
			break;
			
		}while(true);
	}
	
	private static void mostra(Scanner sc , ListaFuncionarios funcionarios) {
		Funcionario funcionario = null;
		String cpf= null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o cpf do passageiro que deseja buscar");
				cpf = sc.nextLine();
				funcionario = funcionarios.buscar(cpf);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(funcionario);
			break;
			
		}while(true);
	}
	
	private static void listaTodos( ListaFuncionarios funcionarios) {
		funcionarios.mostraTodos();
	}
	
	private static void exclui(Scanner sc , ListaFuncionarios funcionarios) {
		Funcionario funcionario = null;
		String cpf= null;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o cpf do passageiro que deseja buscar");
				cpf = sc.nextLine();
				funcionarios.deletar(cpf);
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

}
