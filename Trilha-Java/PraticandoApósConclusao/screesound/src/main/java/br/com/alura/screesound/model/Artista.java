package br.com.alura.screesound.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artistas")
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private TipoArtista tipo;
	
	@OneToMany (mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	private List<Musica> musicas = new ArrayList<>();
	
	
	
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	
	


	public Artista(String nome, TipoArtista tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoArtista getTipo() {
		return tipo;
	}


	public void setTipo(TipoArtista tipo) {
		this.tipo = tipo;
	}


	public List<Musica> getMusicas() {
		return musicas;
	}


	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}


	@Override
	public String toString() {
		return "Artista [nome=" + nome + ", tipo=" + tipo + "]";
	}
	
	
	

}
