package br.com.cepedi.Voll.api.model.records.address.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DataRegisterAddress(
        @JsonAlias("publicPlace")
        @NotBlank(message = "{publicPlace.required}")
        String publicPlace,

        @JsonAlias("neighborhood")
        @NotBlank(message = "{neighborhood.required}")
        String neighborhood,

        @JsonAlias("cep")
        @NotBlank(message = "{cep.required}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalid}")
        String cep,

        @JsonAlias("city")
        @NotBlank(message = "{city.required}")
        String city,

        @JsonAlias("uf")
        @NotBlank(message = "{uf.required}")
        String uf,

        @JsonAlias("complement")
        String complement,

        @JsonAlias("number")
        String number
) {
}