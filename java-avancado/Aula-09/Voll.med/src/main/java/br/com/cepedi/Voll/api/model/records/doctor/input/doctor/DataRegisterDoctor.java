package br.com.cepedi.Voll.api.model.records.doctor.input.doctor;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataRegisterDoctor(
        @JsonAlias("name")
        @NotBlank(message = "{name.required}")
        String name,

        @JsonAlias("email")
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,

        @JsonAlias("phoneNumber")
        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        @JsonAlias("crm")
        @NotBlank(message = "{crm.required}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,

        @JsonAlias("specialty")
        @NotNull(message = "{specialty.required}")
        Specialty specialty,

        @JsonAlias("address")
        @NotNull(message = "{address.required}")
        @Valid
        DataAddress dataAddress
) {
}