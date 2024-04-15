package br.com.cepedi.Library.api.model.records.publisher.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataUpdatePublisher(

        @JsonAlias("id")
        @NotNull
        Long id,
        @JsonAlias("name")
        String name


) {
}