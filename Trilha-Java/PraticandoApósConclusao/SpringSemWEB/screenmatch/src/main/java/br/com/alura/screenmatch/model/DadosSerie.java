package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
					@JsonAlias("totalSeasons")Integer totalTemporadas,
					@JsonAlias("imdbRating") String avaliacao,
					@JsonAlias("imdbVotes") String votos
		) {

	@Override
	public String toString() {
		return "Serie [titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao=" + avaliacao
				+ ", votos=" + votos + "]";
	}

	
	
}
