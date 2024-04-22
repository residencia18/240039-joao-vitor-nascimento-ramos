package br.com.cepedi.Business.api.service.payment;

import br.com.cepedi.Business.api.model.entitys.Payment;
import br.com.cepedi.Business.api.model.entitys.Sale;
import br.com.cepedi.Business.api.model.records.payment.details.DataDetailsPayment;
import br.com.cepedi.Business.api.model.records.payment.input.DataRegisterPayment;
import br.com.cepedi.Business.api.repository.PaymentRepository;
import br.com.cepedi.Business.api.repository.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SaleRepository saleRepository;


    public DataDetailsPayment register(@Valid DataRegisterPayment data) {
        Sale sale = saleRepository.getReferenceById(data.idSale());
        Payment payment = new Payment(sale,data.paymentType());
        paymentRepository.save(payment);
        return new DataDetailsPayment(payment);
    }

}
