package br.com.cepedi.atividade2.app;

import br.com.cepedi.atividade2.listas.ListaCliente;
import br.com.cepedi.atividade2.listas.ListaPedido;
import br.com.cepedi.atividade2.listas.ListaProduto;
import br.com.cepedi.atividade2.menus.Menus;

public abstract class App {
	
	public static void main(String[] args) {
		
		ListaCliente listaClientes = new ListaCliente();
		ListaProduto listaProdutos = new ListaProduto();
		ListaPedido listaPedidos = new ListaPedido();
		
		Menus.menuPrincipal(listaClientes,listaProdutos, listaPedidos);
		
		
		
	}
	
	

}
