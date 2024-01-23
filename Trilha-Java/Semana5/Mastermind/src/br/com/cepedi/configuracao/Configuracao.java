package br.com.cepedi.configuracao;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuracao {
	
	private String alfabeto;
	private String nome;
	private int tamanhoSenha;
	private int maxTentativas;
	
	

	public Configuracao() {

	}

	public String getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(String alfabeto) throws Exception {
		if(alfabeto==null || alfabeto.length()<=1)  {
            throw new IllegalArgumentException("o alfabeto deve possuir mais de 1 caracter"); 
		}
		for(int i = 0 ; i < alfabeto.length()-1 ; i++) {
			for(int j = i+1 ; j < alfabeto.length() ; j++) {
				if(alfabeto.charAt(i)==alfabeto.charAt(j)) {
		            throw new IllegalArgumentException("Não podem haver caracteres repetidos no alfabeto"); 
				}
			}
			
		}
		this.alfabeto= alfabeto;
	}

	public String getNome() {
		return nome;
	}

	public int getMaxTentativas() {
		return maxTentativas;
	}

	public void setMaxTentativas(int maxTentativas) {
		if(maxTentativas<2) {
			throw new  IllegalArgumentException("numero de tentativas deve ser maior que 1");
		}
		this.maxTentativas = maxTentativas;
	}

	public void setNome(String nome) {

		if(nome==null || nome.length()<=1) {
            throw new IllegalArgumentException("o nome deve possuir mais de 1 caracter"); 
		}
		
		if(verificarCaracteresEspeciais(nome)) {
            throw new IllegalArgumentException("O nome nao deve conter caracteres especiais ou digitos"); 
		}

		this.nome = nome;
	}
	
	public int getTamanhoSenha() {
		return tamanhoSenha;
	}

	public void setTamanhoSenha(int tamanhoSenha) {
		if(tamanhoSenha > alfabeto.length()) {
            throw new IllegalArgumentException("o tamanho da senha deve ser menor ou igual ao tamanho do alfabeto"); 
		}else if (tamanhoSenha <1) {
			throw new IllegalArgumentException("O tamanho da senha deve ser maior ou igual a 1");
		}
		this.tamanhoSenha = tamanhoSenha;
	}
	
    private boolean verificarCaracteresEspeciais(String str) {
    	
        String strSemAcentos = removerAcentos(str);

        // Expressão regular para verificar se há pelo menos um dígito ou caractere especial
        String regex = ".*[\\d\\W_].*";

        // Compilar a expressão regular
        Pattern pattern = Pattern.compile(regex);

        // Criar um matcher para a string de entrada
        Matcher matcher = pattern.matcher(strSemAcentos);

        // Verificar se há correspondências
        return matcher.matches();
    }
	
    private String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

	@Override
	public String toString() {
		return "Configuracao [alfabeto=" + alfabeto + ", nome=" + nome + ", tamanhoSenha=" + tamanhoSenha
				+ ", maxTentativas=" + maxTentativas + "]";
	}
    
    


	
    
    



	
	

}
