package br.com.cepedi.Library.api.model.records.author.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataUpdateAuthor(

        @JsonAlias("id")
        @NotNull
        Long id,
        @JsonAlias("name")
        String name


        ) {
}
