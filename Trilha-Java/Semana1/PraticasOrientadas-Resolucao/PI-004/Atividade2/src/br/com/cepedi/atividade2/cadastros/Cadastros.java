package br.com.cepedi.atividade2.cadastros;

import java.util.Scanner;

import br.com.cepedi.atividade2.Exceptions.ClienteNaoEncontradoException;
import br.com.cepedi.atividade2.listas.ListaCliente;
import br.com.cepedi.atividade2.listas.ListaProduto;
import br.com.cepedi.atividade2.model.Cliente;
import br.com.cepedi.atividade2.model.Pedido;
import br.com.cepedi.atividade2.model.Produto;

public abstract class Cadastros {
	
	public static Cliente CadastraCliente(Scanner sc) {
		String nome;
		String cpf;
		System.out.println("Digite o nome do cliente");
		nome = sc.nextLine();
		System.out.println("Digite o CPF do cliente");
		cpf = sc.nextLine();
		Cliente cliente = new Cliente(nome,cpf);
		return cliente;
	}
	
	public static Produto CadastraProduto(Scanner sc) {
		String nome;
		String codigo;
		double preco;
		System.out.println("Digite o nome do produto");
		nome = sc.nextLine();
		System.out.println("Digite o codigo do produto");
		codigo = sc.nextLine();
		System.out.println("Digite o preço do produto");
		preco = Double.parseDouble(sc.nextLine());
		Produto itemProduto = new Produto(codigo,nome,preco);
		return itemProduto;
	}
	
	public static Pedido CadastraPedido(Scanner sc , ListaCliente clientes ) {
		String cpf;
		Cliente cliente;
		System.out.println("Digite o cpf do cliente");
		cpf = sc.nextLine();
		try {
			cliente = clientes.buscaClientePorCPF(cpf);
			Pedido pedido = new Pedido(cliente);
			return pedido;
		}catch(ClienteNaoEncontradoException e ) {
			System.err.println("Erro : "  + e.getMessage());
		}
		return null;
		
	}


}
