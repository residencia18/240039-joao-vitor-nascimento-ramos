package br.com.cepedi.verificacoes.pessoa;

public class VerificacoesMotorista {
	
    public static boolean verificaCNH(String cnh) {
        // Remova caracteres não numéricos da CNH
        cnh = cnh.replaceAll("[^0-9]", "");

        // Verifica se a CNH tem 11 dígitos
        if (cnh.length() != 11) {
            return false;
        }

        // Calcula o dígito verificador
        int soma = 0;
        int peso = 9;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cnh.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        int digitoVerificador = 11 - resto;

        // Verifica o dígito verificador
        if (digitoVerificador > 9) {
            digitoVerificador = 0;
        }

        return Character.getNumericValue(cnh.charAt(9)) == digitoVerificador;
    }

}
