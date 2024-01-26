package br.com.cepedi.configuracao;

import java.util.HashSet;
import java.util.Set;

import br.com.cepedi.exceptions.EndGameException;

public class Jogo {

	Configuracao config;
	String senha;
	int qtdTentativas;
	ESTADO_JOGO estado;
	
	public Jogo(Configuracao config) {
		super();
		this.config = config;
		this.qtdTentativas = 0;
		this.estado = ESTADO_JOGO.JOGANDO;
	}

	public Configuracao getConfig() {
		return config;
	}

	public void setConfig(Configuracao config) {
		this.config = config;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		senha = senha.toUpperCase();
		if(senha.length()!=config.getTamanhoSenha()) {
			throw new IllegalArgumentException("Senha deve ter " + this.getConfig().getTamanhoSenha() + " caracteres");
		}
		if(!verificaContains(senha,config.getAlfabeto())) {
			throw new IllegalArgumentException("Senha não condiz com o alfabeto");
		}
		
		if(!naoExisteCaracterRepetido(senha)) {
			throw new IllegalArgumentException("Não deve haver caracteres repetidos na senha");
		}
		
		this.senha = senha;
	}

	public int getQtdTentativas() {
		return qtdTentativas;
	}

	public void setQtdTentativas(int qtdTentativas) {
		this.qtdTentativas = qtdTentativas;
	}
	
	
	public String setJogada(String tentativa) throws EndGameException {
		
		
		if(!(estado!=ESTADO_JOGO.JOGANDO)) {
			throw new EndGameException();
		}
		
		if(tentativa.length()!=senha.length()) {
			throw new IllegalArgumentException("A tentativa deve ter " + senha.length() + " caracteres");
		}
		if(!verificaContains(tentativa,config.getAlfabeto())) {
			throw new IllegalArgumentException("Senha não condiz com o alfabeto");
		}
		if(!naoExisteCaracterRepetido(tentativa)) {
			throw new IllegalArgumentException("Não deve haver caracteres repetidos");
		}
		
		String saida = "";
		
		for(int i = 0 ; i < tentativa.length() ; i++) {
			if(tentativa.charAt(i) == senha.charAt(i)) {
				saida += "X";
			}else if(senha.contains(tentativa.subSequence(i, i+1))) {
				saida += "O";
			}else {
				saida += "_";
			}
		}
		
		atualizaEstadoJogo(saida);
		
		qtdTentativas++;
		
		return saida;
		
	}

	private void atualizaEstadoJogo(String saida) {
		String valorEsperado = "";
		for(int i = 0 ; i < senha.length() ; i++) {
			valorEsperado+="X";
		}
		
		if(valorEsperado.equals(saida)) {
			this.estado = ESTADO_JOGO.GANHOU;
		}else if(!valorEsperado.equals(saida) && qtdTentativas==this.config.getMaxTentativas()) {
			this.estado = ESTADO_JOGO.PERDEU;
		}
	}
	
	private boolean verificaContains(String str1 , String str2) {
		
		Set<Character> set1 = new HashSet<>();
		Set<Character> set2 = new HashSet<>();
		
        for (char c : str1.toCharArray()) {
        	set1.add(c);
        }

        for (char c : str2.toCharArray()) {
        	set2.add(c);
        }

        return set2.containsAll(set1);		
		
	}
	
	public boolean naoExisteCaracterRepetido(String str) {
		int tamanhoSenha= str.length();
		
		Set<Character> set = new HashSet();
		
		for(char c : str.toCharArray() ) {
			set.add(c);
		}
		
		return tamanhoSenha==set.size();
		
	}
	
	
	
}
