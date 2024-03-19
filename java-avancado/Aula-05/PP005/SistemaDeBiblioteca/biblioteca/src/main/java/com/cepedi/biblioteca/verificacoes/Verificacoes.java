package com.cepedi.biblioteca.verificacoes;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class Verificacoes {
	
    public static boolean verificarNomeSemNumeros(String nome) {
        return Pattern.matches("^[^0-9]*$", nome);
    }
    

    
 // Verifica se um CPF é válido
    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular os dígitos verificadores
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso;
            peso--;
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso;
            peso--;
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        // Verificar se os dígitos verificadores calculados correspondem aos dígitos informados
        return (cpf.charAt(9) - '0') == digito1 && (cpf.charAt(10) - '0') == digito2;
    }

}
