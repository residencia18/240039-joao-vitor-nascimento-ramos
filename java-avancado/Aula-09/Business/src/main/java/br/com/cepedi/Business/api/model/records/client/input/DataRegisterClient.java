package br.com.cepedi.Business.api.model.records.client.input;

import br.com.cepedi.Business.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DataRegisterClient(

        @JsonAlias("name")
        @NotBlank(message = "{name.required}")
        String name,

        @JsonAlias("email")
        @Email(message = "{email.invalid}")
        String email,

        @JsonAlias("birthday")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthday,

        @JsonAlias("phoneNumber")
        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        @JsonAlias("cpf")
        @NotBlank(message = "{cpf.required}")
        @CPF
        String cpf,

        @JsonAlias("address")
        @NotNull(message = "{address.required}")
        @Valid
        DataRegisterAddress dataRegisterAddress


) {
}
