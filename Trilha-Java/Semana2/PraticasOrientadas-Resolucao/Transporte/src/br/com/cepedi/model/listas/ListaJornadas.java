package br.com.cepedi.model.listas;

import java.util.ArrayList;

import br.com.cepedi.exceptions.listaJornada.JornadaJaCadastrada;
import br.com.cepedi.exceptions.listaJornada.JornadaNaoEncontrada;
import br.com.cepedi.model.transporte.Jornada;

public class ListaJornadas extends ArrayList<Jornada>{
	
	public ListaJornadas() {
		
	}
	
	public void adiciona(Jornada jornada) throws JornadaJaCadastrada {
		
		if(jornada==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(jornadaJaCadastrada(jornada)) {
			throw new JornadaJaCadastrada();
		}
		
		add(jornada);
	}
	
	public Jornada buscar(int id) throws JornadaNaoEncontrada{
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		for(Jornada j : this) {
			if(j.getId()==id) {
				return j;
			}
		}
		
		throw new JornadaNaoEncontrada();
	}
	
	public void deletar(int id) throws JornadaNaoEncontrada{
		
		if(id<1) {
			throw new IllegalArgumentException("Insira um id 1 ou superior");
		}
		
		for(Jornada j : this) {
			if(j.getId()==id) {
				remove(j);
				return;
			}
		}
		
		throw new JornadaNaoEncontrada();
	}
	
	
	public void mostraTodos() {
		for(Jornada j  : this) {
			System.out.println(j);
		}
	}
	
	private boolean jornadaJaCadastrada(Jornada jornada) {
		for(Jornada j : this) {
			if(j.equals(jornada)) {
				return true;
			}
		}
		return false;
	}
	

}
