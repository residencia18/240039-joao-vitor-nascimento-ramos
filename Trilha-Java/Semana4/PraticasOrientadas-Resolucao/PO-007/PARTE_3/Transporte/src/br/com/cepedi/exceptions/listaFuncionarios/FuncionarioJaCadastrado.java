package br.com.cepedi.exceptions.listaFuncionarios;

public class FuncionarioJaCadastrado extends Exception{

	public FuncionarioJaCadastrado() {
		super("Esse funcionario já foi cadastrado.");
	}
	
	public FuncionarioJaCadastrado(String msg) {
		super(msg);
	}
	
}
