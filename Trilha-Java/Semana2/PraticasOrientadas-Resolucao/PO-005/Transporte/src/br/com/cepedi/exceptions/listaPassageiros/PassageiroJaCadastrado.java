package br.com.cepedi.exceptions.listaPassageiros;

public class PassageiroJaCadastrado extends Exception{
	
	public PassageiroJaCadastrado() {
		super("passageiro já cadastrado.");
	}
	
	public PassageiroJaCadastrado(String msg) {
		super(msg);
	}

}
