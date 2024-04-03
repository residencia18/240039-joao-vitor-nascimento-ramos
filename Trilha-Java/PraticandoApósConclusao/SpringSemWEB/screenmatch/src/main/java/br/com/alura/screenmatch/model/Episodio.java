package br.com.alura.screenmatch.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Episodio {

	private Integer temporada;

	private String title;

	private Integer numero;

	private BigDecimal avaliacao;

	private LocalDate dataLancamento;

	private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Episodio(Integer temporada, DadosEpisodio dados) {
		super();
		this.temporada = temporada;
		this.title = dados.title().toUpperCase();
		this.numero = dados.numero();
		try {
			this.avaliacao = new BigDecimal(dados.avaliacao());
		} catch (Exception e) {
			this.avaliacao = BigDecimal.ZERO;
		}

		try {
			this.dataLancamento = LocalDate.parse(dados.dataLancamento());
		} catch (Exception e) {
			this.dataLancamento = null;
		}

	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(BigDecimal avaliacao) {
		this.avaliacao = avaliacao;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public String toString() {
		return "[temporada=" + temporada + ", title=" + title + ", numero=" + numero + ", avaliacao=" + avaliacao
				+ ", dataLancamento=" + dataLancamento.format(FORMATADOR) + "]";
	}

}
