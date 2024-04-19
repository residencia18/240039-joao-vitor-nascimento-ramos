package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.Enums.PaymentType;
import jakarta.persistence.*;

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Sale sale;

    @Enumerated
    private PaymentType paymentType;





}
