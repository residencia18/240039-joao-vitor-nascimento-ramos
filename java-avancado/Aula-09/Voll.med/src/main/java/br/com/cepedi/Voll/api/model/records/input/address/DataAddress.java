package br.com.cepedi.Voll.api.model.records.input.address;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAddress(
        @JsonAlias("logradouro")
        @NotBlank
        String publicPlace,
        @JsonAlias("bairro")
        @NotBlank
        String neighborhood,
        @JsonAlias("cep")
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @JsonAlias("cidade")
        @NotBlank
        String city,
        @JsonAlias("uf")
        @NotBlank
        String uf,
        @JsonAlias("complemento")
        String complement,
        @JsonAlias("numero")
        String number
) {
}
