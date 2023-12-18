package br.com.cepedi.atividade2.validacoes;

import br.com.cepedi.atividade2.Exceptions.CpfInvalidoException;

public abstract class ValidacoesCliente {
	
	public static boolean validarCpf(String cpf) throws CpfInvalidoException {

		cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new CpfInvalidoException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new CpfInvalidoException("CPF inválido. Não pode conter sequências repetidas.");
        }

        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int digitoVerificador1 = calcularDigitoVerificador(digitos, 9);
        int digitoVerificador2 = calcularDigitoVerificador(digitos, 10);

        if (digitoVerificador1 != digitos[9] || digitoVerificador2 != digitos[10]) {
            throw new CpfInvalidoException("CPF inválido. Dígitos verificadores incorretos.");
        }
        return true;
    }

    private static int calcularDigitoVerificador(int[] digitos, int posicao) {
        int soma = 0;
        for (int i = 0; i < posicao; i++) {
            soma += digitos[i] * (posicao + 1 - i);
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}
