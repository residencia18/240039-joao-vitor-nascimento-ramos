package br.com.cepedi.Library.api.model.records.client.input;

import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataUpdateClient(


    @JsonAlias("id")
    @NotNull
    Long id,
    @JsonAlias("name")
    String name,

    @JsonAlias("email")
    @Email(message = "{email.invalid}")
    String email,

    @JsonAlias("phoneNumber")
    String phoneNumber,

    @JsonAlias("cpf")
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    String cpf,

    @JsonAlias("address")
    @Valid
    DataRegisterAddress dataRegisterAddress
){

}

