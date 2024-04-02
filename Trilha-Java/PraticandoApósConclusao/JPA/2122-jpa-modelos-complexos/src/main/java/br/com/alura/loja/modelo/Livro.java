package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto{

	private String Autor;
	private Integer numPags;
	
	
	public Livro() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Livro(String autor, Integer numPags) {
		super();
		Autor = autor;
		this.numPags = numPags;
	}



	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public Integer getNumPags() {
		return numPags;
	}
	public void setNumPags(Integer numPags) {
		this.numPags = numPags;
	}
	
	
	
	
}
