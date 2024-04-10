package br.com.cepedi.Voll.api.model.records.input.doctor;

import br.com.cepedi.Voll.api.model.records.input.address.DataAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataRegisterDoctor(
        @JsonAlias("nome")
        @NotBlank
        String name,
        @JsonAlias("email")
        @NotBlank
        @Email
        String email,

        @JsonAlias("telefone")
        @NotBlank
        String phoneNumber,

        @JsonAlias("crm")
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @JsonAlias("especialidade")
        @NotNull
        Specialty specialty,
        @JsonAlias("endereco")
        @NotNull
        @Valid
        DataAddress dataAddres
) {
}
