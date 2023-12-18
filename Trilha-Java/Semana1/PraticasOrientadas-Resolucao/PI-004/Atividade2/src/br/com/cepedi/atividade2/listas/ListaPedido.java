package br.com.cepedi.atividade2.listas;

import java.util.ArrayList;

import br.com.cepedi.atividade2.Exceptions.CodigoJaCadastradoException;
import br.com.cepedi.atividade2.model.Pedido;
import br.com.cepedi.atividade2.model.Produto;

public class ListaPedido {
	
	private ArrayList<Pedido> pedidos;

	public ListaPedido() {
		pedidos = new ArrayList<>();
	}
	
	public void adicionaPedido(Pedido pedido) {
		try {
			pedidos.add(pedido);
            System.out.println("Pedido cadastrado com sucesso!");
		}catch(NullPointerException e ) {
			System.err.println("Erro : Não foi possivel cadastrar pedido");
		}
	}
	
	
	public void mostrarPedidos() {
		for(Pedido pedido : pedidos) {
			System.out.println(pedido);
			System.out.println("---------------------------");
		}
	}
	
	

}
