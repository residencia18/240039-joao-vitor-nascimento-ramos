package br.com.cepedi.exceptions.listaPassageiros;

public class PassageiroNaoEncontrado extends Exception{
	
	public PassageiroNaoEncontrado() {
		super("passageiro não encontrado.");
	}
	
	
	public PassageiroNaoEncontrado(String msg) {
		super(msg);
	}

}
