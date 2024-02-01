package br.com.cepedi.exceptions.listaTrechos;

public class TrechoJaCadastrado extends Exception{
	
	public TrechoJaCadastrado() {
		super("trecho ja cadastrado");
	}
	
	public TrechoJaCadastrado(String msg) {
		super(msg);
	}
	
}
