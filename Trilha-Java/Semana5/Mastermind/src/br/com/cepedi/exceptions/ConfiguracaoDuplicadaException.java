package br.com.cepedi.exceptions;

public class ConfiguracaoDuplicadaException extends RuntimeException {

    public ConfiguracaoDuplicadaException() {
        super("Já existe uma configuração com o mesmo nome. A configuração não foi adicionada");
    }
}