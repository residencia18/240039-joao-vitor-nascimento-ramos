package br.com.cepedi.Voll.api.model.records.patient.input;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataUpdatePatient(

        @JsonAlias("id")
        @NotNull
        Long id,
        @JsonAlias("nome")
        String name,

        @JsonAlias("telefone")
        String phoneNumber,

        @JsonAlias("endereco")
        DataAddress dataAddress
) {


}
