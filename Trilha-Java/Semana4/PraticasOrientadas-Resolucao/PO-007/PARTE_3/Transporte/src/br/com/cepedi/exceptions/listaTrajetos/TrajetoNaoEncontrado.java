package br.com.cepedi.exceptions.listaTrajetos;

public class TrajetoNaoEncontrado extends Exception{

	public TrajetoNaoEncontrado() {
		super("Trajeto não encontrado");
	}
	
	public TrajetoNaoEncontrado(String msg) {
		super(msg);
	}
	
}
