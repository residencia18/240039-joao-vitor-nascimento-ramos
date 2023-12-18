package br.com.cepedi.atividade2.Exceptions;

public class CpfJaRegistradoException extends Exception{

    public CpfJaRegistradoException(String mensagem) {
        super(mensagem);
    }
}