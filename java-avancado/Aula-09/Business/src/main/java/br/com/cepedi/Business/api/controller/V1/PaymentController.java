package br.com.cepedi.Business.api.controller.V1;

import br.com.cepedi.Business.api.model.records.payment.details.DataDetailsPayment;
import br.com.cepedi.Business.api.model.records.payment.input.DataRegisterPayment;
import br.com.cepedi.Business.api.service.payment.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/payments")
@SecurityRequirement(name = "bearer-key")
public class PaymentController {


    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private PaymentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsPayment> register(@RequestBody @Valid DataRegisterPayment data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new payment...");
        DataDetailsPayment dataDetailsPayment = service.register(data);
        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(dataDetailsPayment.id()).toUri();
        logger.info("New payment registered with ID: {}", dataDetailsPayment.id());
        return ResponseEntity.created(uri).body(dataDetailsPayment);
    }

}
