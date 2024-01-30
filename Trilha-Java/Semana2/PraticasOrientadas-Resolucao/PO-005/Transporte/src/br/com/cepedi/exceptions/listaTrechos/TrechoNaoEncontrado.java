package br.com.cepedi.exceptions.listaTrechos;

public class TrechoNaoEncontrado extends Exception{
	
	public TrechoNaoEncontrado() {
		super("trecho não encontrado.");
	}
	
	public TrechoNaoEncontrado(String msg) {
		super(msg);
	}

}
