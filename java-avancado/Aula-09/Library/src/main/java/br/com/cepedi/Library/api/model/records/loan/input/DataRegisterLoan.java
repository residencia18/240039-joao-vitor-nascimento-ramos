package br.com.cepedi.Library.api.model.records.loan.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterLoan(

        @JsonAlias("book_id")
        @NotBlank(message = "{book_id.required}")
        Long book_id,


        @JsonAlias("client_id")
        @NotBlank(message = "{client_id.required}")
        Long client_id


) {
}
