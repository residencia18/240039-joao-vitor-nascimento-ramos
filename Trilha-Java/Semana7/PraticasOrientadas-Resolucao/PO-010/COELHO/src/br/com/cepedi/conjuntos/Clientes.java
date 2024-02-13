package br.com.cepedi.conjuntos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import br.com.cepedi.dao.ClienteDAO;
import br.com.cepedi.exceptions.ConjuntoClientes.ClienteJaExistente;
import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;
import br.com.cepedi.model.Cliente;

public class Clientes extends HashSet<Cliente> {

	private static final long serialVersionUID = 1L;

	public Clientes() {

	}

	public void adicionar(Cliente cliente) throws ClienteJaExistente {
		
		if (cliente == null) throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");

		if(this.contains(cliente)) throw new ClienteJaExistente();
		
	    ClienteDAO.adicionarCliente(cliente);
	    System.out.println("Cliente adicionado com sucesso!");
	}

	public Cliente buscar(int id) throws ClienteNaoEncontradoException {
		
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");
		
	    return ClienteDAO.buscarCliente(id);
	}
	
	public void atualizar(int id, String novoValor, String atributo) throws ClienteNaoEncontradoException, CPFPessoaInvalidoException, NomePessoaInvalidoException {
	    Cliente cliente = buscar(id);

	    if (atributo.equals("nome")) {
            cliente.setNome(novoValor);
        } else {
            cliente.setCpf(novoValor);
        }

	    ClienteDAO.atualizarCliente(cliente);
	    
	    System.out.println("Cliente atualizado com sucesso!");
	}


	public void deletar(int id) throws ClienteNaoEncontradoException {
		
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");

	    Cliente cliente = buscar(id);

	    ClienteDAO.excluirCliente(id);
	    System.out.println("Cliente excluido com sucesso!");

	    
	}

	public void listar(){

		ClienteDAO.obterTodosClientes().stream()
        .sorted(Comparator.comparingInt(Cliente::getId))
        .collect(Collectors.toList()).forEach(System.out::println);
	}


	public void listarOrdenadoPorNome()  {
		
		ClienteDAO.obterTodosClientes().stream()
        .sorted(Comparator.comparing(Cliente::getNome))
        .collect(Collectors.toList()).forEach(System.out::println);
	}


	

}
