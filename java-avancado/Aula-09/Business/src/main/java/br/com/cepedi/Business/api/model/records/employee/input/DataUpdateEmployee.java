package br.com.cepedi.Business.api.model.records.employee.input;

import br.com.cepedi.Business.api.model.records.address.input.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record DataUpdateEmployee(

        @JsonAlias("name")
        String name,

        @JsonAlias("email")
        @Email(message = "{email.invalid}")
        String email,


        @JsonAlias("birthday")
        @JsonFormat(pattern = "dd/MM/aaaa")
        LocalDate birthday,

        @JsonAlias("phoneNumber")
        String phoneNumber,

        @JsonAlias("cpf")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @JsonAlias("id_role")
        Long idRole,

        @JsonAlias("address")
        @Valid
        DataRegisterAddress dataRegisterAddress

) {
}
