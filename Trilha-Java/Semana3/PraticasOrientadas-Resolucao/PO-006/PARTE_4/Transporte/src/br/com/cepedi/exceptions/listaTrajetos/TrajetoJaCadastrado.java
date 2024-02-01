package br.com.cepedi.exceptions.listaTrajetos;

public class TrajetoJaCadastrado extends Exception{

	public TrajetoJaCadastrado() {
		super("Esse trajeto já foi cadastrado.");
	}
	
	public TrajetoJaCadastrado(String msg) {
		super(msg);
	}
	
}
