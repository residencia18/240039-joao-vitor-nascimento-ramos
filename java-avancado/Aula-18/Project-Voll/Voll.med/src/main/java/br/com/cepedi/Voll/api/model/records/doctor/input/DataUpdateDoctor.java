package br.com.cepedi.Voll.api.model.records.doctor.input;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataUpdateDoctor (

    @JsonAlias("nome")
    String name,
    @JsonAlias("email")
    @Email
    String email,

    @JsonAlias("telefone")
    String phoneNumber,

    @JsonAlias("endereco")
    DataRegisterAddress dataAddress
){

}
