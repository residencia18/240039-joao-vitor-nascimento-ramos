package br.com.cepedi.exceptions.cliente;

public class NomePessoaInvalidoException extends Exception{
	
	public NomePessoaInvalidoException() {
		super("O nome não pode conter numeros");
	}

}
