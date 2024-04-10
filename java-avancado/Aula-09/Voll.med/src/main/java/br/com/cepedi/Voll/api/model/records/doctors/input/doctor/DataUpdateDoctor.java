package br.com.cepedi.Voll.api.model.records.doctors.input.doctor;

import br.com.cepedi.Voll.api.model.records.doctors.input.address.DataAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataUpdateDoctor (

    @JsonAlias("id")
    @NotNull
    Long id,
    @JsonAlias("nome")
    String name,
    @JsonAlias("email")
    @Email
    String email,

    @JsonAlias("telefone")
    String phoneNumber,

    @JsonAlias("endereco")
    DataAddress dataAddress
){

}
