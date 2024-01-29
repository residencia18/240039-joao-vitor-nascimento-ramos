package br.com.cepedi.exceptions.listaJornada;

public class JornadaJaCadastrada extends Exception{
	
	public JornadaJaCadastrada() {
		super("Jornada já cadastrada");
	}
	
}
