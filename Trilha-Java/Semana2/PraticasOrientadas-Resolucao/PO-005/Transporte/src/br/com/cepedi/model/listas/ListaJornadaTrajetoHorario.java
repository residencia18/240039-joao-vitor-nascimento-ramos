package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaJornadaTrajetoHorario.JornadaTrajetoHorarioException;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.JornadaTrajetoHorario;

public class ListaJornadaTrajetoHorario extends ArrayList<JornadaTrajetoHorario>{
	
	public ListaJornadaTrajetoHorario() {
		
	}
	
	
	public void adiciona(JornadaTrajetoHorario trajetosJornada) throws JornadaTrajetoHorarioException {
		
		if(trajetosJornada==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(JaRegistrado(trajetosJornada)) {
			throw new JornadaTrajetoHorarioException();
		}

		add(trajetosJornada);
			
	}
	
	public void mostraTodos() {
		
		if(this.isEmpty()) {
			System.out.println("Lista está vazia");
		}
		
		for(JornadaTrajetoHorario j : this) {
			System.out.println(j);
		}
	}
	
	public void mostrarPorJornada(Jornada j) {
		
		if(this.isEmpty()) {
			System.out.println("Lista está vazia");
		}
		
		for(JornadaTrajetoHorario jth : this) {
			if(jth.getJornada().equals(j)) {
				System.out.println(jth);
			}
		}
	}
	
	private boolean JaRegistrado(JornadaTrajetoHorario trajetosJornada) {
		for(JornadaTrajetoHorario t : this) {
			if(t.equals(trajetosJornada)) {
				return true;
			}
		}
		
		return false;
	}
	

}
