package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;

public record SerieDTO(
    Long id,
    String titulo,
    Integer totalTemporadas,
    Double avaliacao,
    Categoria genero,
    String atores,
    String poster,
    String sinopse
) {

    public SerieDTO(Serie s) {
        this(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
    }

    public SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse
    ) {
        this.id = id;
        this.titulo = titulo;
        this.totalTemporadas = totalTemporadas;
        this.avaliacao = avaliacao;
        this.genero = genero;
        this.atores = atores;
        this.poster = poster;
        this.sinopse = sinopse;
    }

}
