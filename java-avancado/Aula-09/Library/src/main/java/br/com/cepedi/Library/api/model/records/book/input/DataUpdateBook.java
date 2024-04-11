package br.com.cepedi.Library.api.model.records.book.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataUpdateBook(

        @JsonAlias("id")
        @NotNull
        Long id,
        String name,
        Integer anoPublicacao,
        Long authorId,
        Long publisherId
) {
}