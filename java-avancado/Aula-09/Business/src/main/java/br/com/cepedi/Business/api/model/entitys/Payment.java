package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.Enums.PaymentType;
import br.com.cepedi.Business.api.model.records.payment.input.DataRegisterPayment;
import br.com.cepedi.Business.api.service.payment.PaymentService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @Enumerated
    private PaymentType paymentType;

    private LocalDateTime datePayment;


    public Payment(Sale sale, PaymentType paymentType) {
        this.sale = sale;
        this.paymentType = paymentType;
        this.datePayment = LocalDateTime.now();
    }
}
