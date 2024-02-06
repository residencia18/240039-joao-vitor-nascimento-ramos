package br.com.cepedi.exceptions.cartaoPassagem;

public class TipoDePassagemInvalidoException extends Exception{
	
    public TipoDePassagemInvalidoException() {
        super("Tipo de passagem inválido");
    }

    public TipoDePassagemInvalidoException(String mensagem) {
        super(mensagem);
    }

}
