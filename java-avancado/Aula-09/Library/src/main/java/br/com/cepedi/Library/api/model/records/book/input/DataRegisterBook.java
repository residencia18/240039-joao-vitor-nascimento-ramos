package br.com.cepedi.Library.api.model.records.book.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterBook(

        @JsonAlias("name")
        @NotBlank(message = "{name.required}")
        String name,

        @JsonAlias("anoPublicacao")
        @NotBlank(message = "{anoPublicacao.required}")
        Integer anoPublicacao,

        @JsonAlias("author_id")
        @NotBlank(message = "{author.requered}")
        Long author_id,


        @JsonAlias("publisher_id")
        @NotBlank(message = "{publisher.requered}")
        Long publisher_id

) {
}
