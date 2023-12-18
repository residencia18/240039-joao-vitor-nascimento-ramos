package br.com.cepedi.atividade2.menus;

import java.util.Scanner;

import br.com.cepedi.atividade2.Exceptions.ProdutoNaoEncontradoException;
import br.com.cepedi.atividade2.cadastros.Cadastros;
import br.com.cepedi.atividade2.listas.ListaCliente;
import br.com.cepedi.atividade2.listas.ListaPedido;
import br.com.cepedi.atividade2.listas.ListaProduto;
import br.com.cepedi.atividade2.model.Cliente;
import br.com.cepedi.atividade2.model.Pedido;
import br.com.cepedi.atividade2.model.Produto;

public class Menus {

    public static void menuPrincipal(ListaCliente clientes , ListaProduto produtos , ListaPedido pedidos) {

        Scanner scanner = new Scanner(System.in);
        final int OP_SAIR = 0;
        final int OP_CADASTROS = 1;
        final int OP_LISTAR = 2;
        final int OP_PEDIDO = 3;

        int opcao = 5 ;
        do {
            exibirMenu();
            try {
            	opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                case OP_CADASTROS:
                    menuCadastros(scanner,clientes, produtos);
                    break;
                case OP_LISTAR:
                	menuListar(scanner,clientes, produtos);
                    break;
                case OP_PEDIDO:
                	realizarPedido(scanner,clientes,produtos,pedidos);
                	break;
                case OP_SAIR:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            }catch(NumberFormatException e ) {
            	System.out.println("Erro : escolha inválida  -- " + e.getMessage());
            }
            

        } while (opcao != OP_SAIR);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Cadastros");
        System.out.println("2. Ver listas");
        System.out.println("3. Realizar Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha a opção desejada: ");
    }

    private static void menuCadastros(Scanner scanner, ListaCliente clientes ,  ListaProduto produtos) {
        System.out.println("=== Menu de Cadastros ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Produto");
        System.out.println("0. Voltar");
        System.out.print("Escolha a opção desejada: ");
        int opcaoCadastro = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcaoCadastro) {
            case 1:
                Cliente cliente = Cadastros.CadastraCliente(scanner);
                clientes.adicionaCliente(cliente);
                break;
            case 2:
                Produto produto = Cadastros.CadastraProduto(scanner);
                produtos.adicionaProduto(produto);
                break;
            case 0:
                System.out.println("Voltando ao menu principal.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    private static void menuListar(Scanner scanner, ListaCliente clientes ,  ListaProduto produtos) {
        System.out.println("=== Menu de Cadastros ===");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Listar Produtos");
        System.out.println("0. Voltar");
        System.out.print("Escolha a opção desejada: ");
        int opcaoCadastro = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcaoCadastro) {
            case 1:
                clientes.mostrarClientes();
                break;
            case 2:
                produtos.mostrarProdutos();
                break;
            case 0:
                System.out.println("Voltando ao menu principal.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    private static void realizarPedido(Scanner scanner, ListaCliente clientes ,  ListaProduto produtos , ListaPedido pedidos) {
    	Pedido pedido = Cadastros.CadastraPedido(scanner, clientes);
    	pedido.preenchePedido(scanner, produtos);
    	mostraValorTotal(scanner,pedido);

    }
    
    private static void mostraValorTotal(Scanner scanner, Pedido pedido) {
    	int quantidadePrestacoes = 0;
    	do {
        	try {
            	System.out.println("Digite o numero de prestacoes: 0 pra a vista");
            	quantidadePrestacoes = Integer.parseInt(scanner.nextLine());
            	if(quantidadePrestacoes < 0 ) {
            		System.out.println("Quantidade de prestações deve ser maior igual a 0");
            		continue;
            	}else if(quantidadePrestacoes == 0){
            		pedido.mostraValorTotalPedido(10);
            	}else {
            		pedido.mostraValorTotalPedido(quantidadePrestacoes, 2);
            	}
        	}catch(NumberFormatException e) {
        		System.err.println("Erro " + e.getMessage());
        	}
    	}while(quantidadePrestacoes < 0);
    }

}
