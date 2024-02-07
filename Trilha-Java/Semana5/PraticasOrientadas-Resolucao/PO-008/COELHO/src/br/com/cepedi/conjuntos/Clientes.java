package br.com.cepedi.conjuntos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
		
		add(cliente);
	    System.out.println("Cliente adicionado com sucesso!");

	}

	public Cliente buscar(int id) throws ClienteNaoEncontradoException {
		
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");
		
	    return this.stream().filter(cliente -> cliente.getId() == id).findFirst().orElseThrow(ClienteNaoEncontradoException::new);
	}
	
	public void atualizar(int id, String novoValor, String atributo) throws ClienteNaoEncontradoException, CPFPessoaInvalidoException, NomePessoaInvalidoException {
	    Cliente cliente = buscar(id);

	    if (atributo.equals("nome")) {
            cliente.setNome(novoValor);
        } else {
            cliente.setCpf(novoValor);
        }


	    System.out.println("Cliente atualizado com sucesso!");
	}


	public void deletar(int id) throws ClienteNaoEncontradoException {
		
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");

	    Cliente cliente = buscar(id);

	    this.remove(cliente);
	    System.out.println("Cliente excluido com sucesso!");

	    
	}

	public void listar(){
		
		if (this.isEmpty()) System.out.println("Não há clientes cadastrados");
		

		imprimeConjunto(this);
	}


	public void listarOrdenadoPorNome()  {
		
		if (this.isEmpty()) System.out.println("Não há clientes cadastrados");

		
		Set<Cliente> clientesOrdenadosPorNome = new TreeSet<>(Comparator.comparing(Cliente::getNome));
		clientesOrdenadosPorNome.addAll(this);

		imprimeConjunto(clientesOrdenadosPorNome);
	}

	private <T> void imprimeConjunto(Set<T> lista) {
	    lista.forEach(System.out::println);
	}
	
	

}
