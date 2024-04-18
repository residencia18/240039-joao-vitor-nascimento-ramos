package br.com.cepedi.Library.api.model.records.author.details;

import br.com.cepedi.Library.api.model.entitys.Author;

public record DataDetailsAuthor(
        Long id,
        String name,
        Boolean activated
) {
    public DataDetailsAuthor(Author author) {
        this(author.getId(), author.getName(), author.getActivated());
    }
}

