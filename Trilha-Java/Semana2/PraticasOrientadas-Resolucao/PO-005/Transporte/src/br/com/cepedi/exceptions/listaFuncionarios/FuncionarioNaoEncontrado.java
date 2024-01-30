package br.com.cepedi.exceptions.listaFuncionarios;

public class FuncionarioNaoEncontrado extends Exception{
	
	public FuncionarioNaoEncontrado() {
		super("Funcionario não encontrado");
	}
	
	public FuncionarioNaoEncontrado(String msg) {
		super(msg);
	}
}
