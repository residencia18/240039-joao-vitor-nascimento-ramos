package br.com.cepedi.Business.api.model.records.payment.details;

import br.com.cepedi.Business.api.model.Enums.PaymentType;
import br.com.cepedi.Business.api.model.entitys.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DataDetailsPayment(

        Long id,

        Long idSale,

        BigDecimal value,

        LocalDateTime datePayment,

        PaymentType paymentType


) {
    public DataDetailsPayment(Payment payment) {
        this(payment.getId(), payment.getSale().getId() , payment.getSale().getFinalValue() , payment.getDatePayment() , payment.getPaymentType());
    }
}
