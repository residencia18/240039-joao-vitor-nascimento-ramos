package br.com.cepedi.Library.api.model.records.loan.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateLoan(

    @JsonAlias("id")
    @NotNull
    Long id,

    @JsonAlias("book_id")
    @NotBlank(message = "{book.requered}")
    Long book_id,


    @JsonAlias("client_id")
    @NotBlank(message = "{client_id.requered}")
    Long client_id
){

}
