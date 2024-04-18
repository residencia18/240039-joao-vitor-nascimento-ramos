package br.com.cepedi.Business.api.model.records.address.input;

import br.com.cepedi.Business.api.model.Enums.State;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull(message = "{uf.required}")
        State state,

        @JsonAlias("complement")
        String complement,

        @JsonAlias("number")
        String number
) {
}