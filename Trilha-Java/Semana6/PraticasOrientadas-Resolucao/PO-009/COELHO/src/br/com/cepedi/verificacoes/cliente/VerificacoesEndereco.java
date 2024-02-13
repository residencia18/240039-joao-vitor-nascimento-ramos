package br.com.cepedi.verificacoes.cliente;

public abstract class VerificacoesEndereco {
	
    public static boolean verificaEstado(String nome) {
        return nome.matches("\\D+");
    }
    
    public static boolean verificaCEP(String cep) {
    	return cep.matches("[\\d]{5}-[\\d]{3}");
    }

}
