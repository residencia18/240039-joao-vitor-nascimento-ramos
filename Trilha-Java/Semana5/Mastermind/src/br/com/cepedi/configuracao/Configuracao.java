package br.com.cepedi.configuracao;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.cepedi.utils.Util;

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
		if(!Util.caracterRepetido(alfabeto)) {	
			alfabeto = alfabeto.toUpperCase();
			this.alfabeto= alfabeto;
		}
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

        String regex = ".*[\\d\\W_].*";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(strSemAcentos);

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
