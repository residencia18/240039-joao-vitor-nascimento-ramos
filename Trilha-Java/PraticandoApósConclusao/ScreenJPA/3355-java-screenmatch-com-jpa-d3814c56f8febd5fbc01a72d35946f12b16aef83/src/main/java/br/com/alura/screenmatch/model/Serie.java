package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import br.com.alura.screenmatch.traducao.Tradutor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Series")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "TOTAL_TEMPORADAS")
	private Integer totalTemporadas;

	@Column(name = "AVALIACAO")
	private Double avaliacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENERO")
	private Categoria genero;

	@Column(name = "ATORES")
	private String atores;

	@Column(name = "POSTER")
	private String poster;

	@Column(name = "SINOPSE")
	private String sinopse;

	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
	List<Episodio> epsodios = new ArrayList<>();
	
	public Serie() {
		// TODO Auto-generated constructor stub
	}

	public Serie(DadosSerie dados) {
		this.titulo = dados.titulo();
		this.totalTemporadas = dados.totalTemporadas();
		this.avaliacao = OptionalDouble.of(Double.valueOf(dados.avaliacao())).orElse(0);
		this.genero = Categoria.fromString(dados.genero().split(",")[0]);
		this.atores = dados.atores();
		this.poster = dados.poster();
		this.sinopse = Tradutor.traduzirInglesParaPortugues(dados.sinopse());
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Categoria getGenero() {
		return genero;
	}

	public void setGenero(Categoria genero) {
		this.genero = genero;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Episodio> getEpsodios() {
		return epsodios;
	}

	public void setEpsodios(List<Episodio> epsodios) {
		epsodios.stream().forEach(e -> e.setSerie(this));
		this.epsodios = epsodios;
	}

	@Override
	public String toString() {
		return "Serie [titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao=" + avaliacao
				+ ", genero=" + genero + ", atores=" + atores + ", poster=" + poster + ", sinopse=" + sinopse + "]";
	}

}
