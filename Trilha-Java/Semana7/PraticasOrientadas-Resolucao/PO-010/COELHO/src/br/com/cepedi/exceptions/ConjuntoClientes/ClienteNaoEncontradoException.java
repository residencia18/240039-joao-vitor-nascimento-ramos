package br.com.cepedi.exceptions.ConjuntoClientes;

public class ClienteNaoEncontradoException extends Exception{
	
	public ClienteNaoEncontradoException() {
		super("Cliente não encontrado");
	}

}
