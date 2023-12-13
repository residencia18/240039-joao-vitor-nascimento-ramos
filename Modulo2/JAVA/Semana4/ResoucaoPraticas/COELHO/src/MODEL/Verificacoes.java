package MODEL;

import java.util.List;

public abstract class Verificacoes {

	public static boolean validarCPF(String cpf) {
	    cpf = cpf.replaceAll("[^0-9]", "");

	    if (cpf.length() != 11) {
	        return false;
	    }

	    int digito1 = calcularDigito(cpf.substring(0, 9), 10);
	    int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, 11);

	    return cpf.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
	}

	private static int calcularDigito(String str, int peso) {
	    int total = 0;
	    for (int i = 0; i < str.length(); i++) {
	        total += Integer.parseInt(String.valueOf(str.charAt(i))) * peso--;
	    }
	    int resto = total % 11;
	    return resto < 2 ? 0 : 11 - resto;
	}


}
