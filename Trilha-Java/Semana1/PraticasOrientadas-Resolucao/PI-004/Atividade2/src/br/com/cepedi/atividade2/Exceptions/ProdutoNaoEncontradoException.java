package br.com.cepedi.atividade2.Exceptions;

public class ProdutoNaoEncontradoException extends Exception {

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}