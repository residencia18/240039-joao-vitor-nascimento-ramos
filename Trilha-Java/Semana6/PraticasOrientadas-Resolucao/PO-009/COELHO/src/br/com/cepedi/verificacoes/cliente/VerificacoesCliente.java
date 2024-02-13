package br.com.cepedi.verificacoes.cliente;

import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;

public abstract class VerificacoesCliente {

    public static void verificaNome(String nome) throws NomePessoaInvalidoException {
    	if(!nome.matches("\\D+")) {
			throw new NomePessoaInvalidoException();
    	}
    }

    public static void verificaCPF(String cpf) throws CPFPessoaInvalidoException {
        // Remova caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
			throw new CPFPessoaInvalidoException();
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
			throw new CPFPessoaInvalidoException();
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
			throw new CPFPessoaInvalidoException();
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }

        // Verifica o segundo dígito verificador        
        if(!(Character.getNumericValue(cpf.charAt(10)) == segundoDigito)) {
			throw new CPFPessoaInvalidoException();
        }
    }
}
