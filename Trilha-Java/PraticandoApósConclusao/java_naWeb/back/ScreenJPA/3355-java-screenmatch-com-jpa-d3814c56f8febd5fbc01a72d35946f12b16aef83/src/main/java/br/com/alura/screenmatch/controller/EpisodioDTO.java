package br.com.alura.screenmatch.controller;

import java.time.LocalDate;
import br.com.alura.screenmatch.model.Episodio;

public record EpisodioDTO(
    Integer temporada,
    String titulo,
    Integer numeroEpisodio,
    Double avaliacao,
    LocalDate dataLancamento
) {
    public EpisodioDTO(Episodio episodio) {
        this(
            episodio.getTemporada(),
            episodio.getTitulo(),
            episodio.getNumeroEpisodio(),
            episodio.getAvaliacao(),
            episodio.getDataLancamento()
        );
    }
}