package br.com.cepedi.Business.api.model.records.supplier.input;

import br.com.cepedi.Business.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record DataUpdateSupplier(


    @JsonAlias("name")
    String name,

    @JsonAlias("CPF")
    @CPF
    String CPF,

    @JsonAlias("CNPJ")
    @CNPJ
    String CNPJ,

    @JsonAlias("email")
    @Email(message = "{email.invalid}")
    String email,

    @JsonAlias("phoneNumber1")
    String phoneNumber1,

    @JsonAlias("phoneNumber2")
    String phoneNumber2,

    @JsonAlias("address")
    @Valid
    DataRegisterAddress dataRegisterAddress


) {

}
