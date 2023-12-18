package br.com.cepedi.atividade2.listas;

import java.util.ArrayList;

import br.com.cepedi.atividade2.Exceptions.ClienteNaoEncontradoException;
import br.com.cepedi.atividade2.Exceptions.CpfJaRegistradoException;
import br.com.cepedi.atividade2.model.Cliente;

public class ListaCliente {

	ArrayList<Cliente> clientes;

	public ListaCliente() {
		super();
		this.clientes = new ArrayList<>();
	}
	
	public void adicionaCliente(Cliente cliente) {
		try {
			if(this.CpfNaoCadastrado(cliente.getCPF())) {
				clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso!");

			}
		}catch(CpfJaRegistradoException e ) {
			System.err.println("Erro : " + e.getMessage());
		}catch(NullPointerException e ) {
			System.err.println("Erro : Não foi possivel cadastrar cliente");
		}


	}
	
	private boolean CpfNaoCadastrado(String cpf) throws CpfJaRegistradoException {
		boolean encontrado = true;
		cpf = cpf.replaceAll("[^0-9]", "");
		String cpfClienteSomenteNumeros;
		
		for(Cliente cliente : clientes) {
			cpfClienteSomenteNumeros = cliente.getCPF().replaceAll("[^0-9]", "");
			if(cpfClienteSomenteNumeros.equals(cpf)) {
				encontrado = false;
	            throw new CpfJaRegistradoException("Cpf : " + cpf.toString() + "já  está registrado registrado");
			}
		}
		return encontrado;
	}
	
	
	public void mostrarClientes() {
		for(Cliente cliente : clientes) {
			System.out.println(cliente);
			System.out.println("---------------------------");
		}
	}
	
	public Cliente buscaClientePorCPF(String cpf) throws ClienteNaoEncontradoException {
		for(Cliente cliente : clientes) {
			if(cliente.getCPF().equals(cpf)) {
				return cliente;
			}
		}
		throw new ClienteNaoEncontradoException("Cliente não encontrado");
	}
	
}
