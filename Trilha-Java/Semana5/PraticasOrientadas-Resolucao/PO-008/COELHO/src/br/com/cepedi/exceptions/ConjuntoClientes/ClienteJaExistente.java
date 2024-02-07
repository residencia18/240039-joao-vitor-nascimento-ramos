package br.com.cepedi.exceptions.ConjuntoClientes;

public class ClienteJaExistente extends Exception{
	public ClienteJaExistente() {
		super("Cliente já existente");
	}
}
