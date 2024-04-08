package br.com.alura.screesound.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column
	private String titulo;
	
	@ManyToOne
	private Artista artista;
	
	public Musica() {
		// TODO Auto-generated constructor stub
	}

	public Musica(String nomeMusica) {
		this.titulo = nomeMusica;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	

	@Override
	public String toString() {
		return "Musica [titulo=" + titulo + ", artista=" + artista.getNome() + "]";
	}

	
	
}
