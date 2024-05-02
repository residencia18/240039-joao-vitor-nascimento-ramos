package br.com.cepedi.Voll.api.model.records.patient.input;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegisterPatient (

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

        @JsonAlias("cpf")
        @NotBlank(message = "{cpf.required}")
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @JsonAlias("address")
        @NotNull(message = "{address.required}")
        @Valid
        DataRegisterAddress dataAddress

        )

        {
}
