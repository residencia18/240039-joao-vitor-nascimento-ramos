package br.com.cepedi.atividade2.Exceptions;

public class ClienteNaoEncontradoException extends Exception {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}