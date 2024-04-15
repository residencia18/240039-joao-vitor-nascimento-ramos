package br.com.cepedi.Library.api.model.records.author.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterAuthor(

        @JsonAlias("name")
        @NotBlank(message = "{name.required}")
        String name

) {
}
