package br.com.cepedi.exceptions;

public class EndGameException extends Exception{
	public EndGameException() {
		super("O jogo já terminou");
	}
}
