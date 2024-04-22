package br.com.cepedi.Business.api.model.records.payment.input;

import br.com.cepedi.Business.api.model.Enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DataRegisterPayment(

        @NotNull
        @Positive
        @JsonAlias("idSale")
        Long idSale,

        @NotNull
        @JsonAlias("typePayment")
        PaymentType paymentType


) {

}
