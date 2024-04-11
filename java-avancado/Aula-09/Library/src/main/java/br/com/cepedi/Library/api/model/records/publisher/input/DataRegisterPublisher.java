package br.com.cepedi.Library.api.model.records.publisher.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterPublisher(

        @JsonAlias("name")
        @NotBlank(message = "{name.required}")
        String name

) {


}
