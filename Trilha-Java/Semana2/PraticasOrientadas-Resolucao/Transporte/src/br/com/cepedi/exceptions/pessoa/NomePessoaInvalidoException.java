package br.com.cepedi.exceptions.pessoa;

public class NomePessoaInvalidoException extends Exception{
	
	public NomePessoaInvalidoException() {
		super("Nome inválido. deve possuir apenas letras e espaços");
	}

}
