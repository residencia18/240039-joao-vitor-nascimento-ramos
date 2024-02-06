package br.com.cepedi.conjuntos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;
import br.com.cepedi.model.Cliente;

public class Clientes extends HashSet<Cliente> {

	private static final long serialVersionUID = 1L;

	public Clientes() {

	}

	public void adicionar(Cliente cliente) throws CPFPessoaInvalidoException, NomePessoaInvalidoException {

		int qntDadosNaLista = this.size();

		if (cliente == null) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}

		add(cliente);

		if (this.size() == qntDadosNaLista) {
			System.out.println("Não foi possivel realizar adição à lista");
		} else {
			System.out.println("Cliente adicionado com sucesso!");
		}
	}

	public Cliente buscar(int id) throws ClienteNaoEncontradoException {
		if (id < 1) {
			throw new IllegalArgumentException("tentativa de inserir id menor que 1");
		}

		for (Cliente cliente : this) {
			if (cliente.getId() == id) {
				return cliente;
			}
		}

		throw new ClienteNaoEncontradoException();
	}

	public void deletar(int id) throws ClienteNaoEncontradoException {
		if (id < 1) {
			throw new IllegalArgumentException("tentativa de inserir id menor que 1");
		}

		Iterator<Cliente> iterator = this.iterator();
		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();
			if (cliente.getId() == id) {
				iterator.remove();
				System.out.println("Cliente removido com sucesso");
				return;
			}
		}

		throw new ClienteNaoEncontradoException();
	}

	public void listar() {
		if (this.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
			return;
		}

		for (Cliente cliente : this) {
			System.out.println(cliente);
		}
	}

	public void listarOrdenadoPorID() {
		
		if (this.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
			return;
		}
		
		Set<Cliente> clientesOrdenadosPorID = new TreeSet<>(Comparator.comparingInt(Cliente::getId));
		clientesOrdenadosPorID.addAll(this);

		for (Cliente cliente : clientesOrdenadosPorID) {
			System.out.println(cliente);
		}
	}

	public void listarOrdenadoPorNome() {
		
		if (this.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
			return;
		}
		
		Set<Cliente> clientesOrdenadosPorNome = new TreeSet<>(Comparator.comparing(Cliente::getNome));
		clientesOrdenadosPorNome.addAll(this);

		for (Cliente cliente : clientesOrdenadosPorNome) {
			System.out.println(cliente);
		}
	}

}
