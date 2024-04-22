package br.com.cepedi.Business.api.controller.V1;

import br.com.cepedi.Business.api.model.entitys.Sale;
import br.com.cepedi.Business.api.model.records.role.details.DataDetailsRole;
import br.com.cepedi.Business.api.model.records.sale.details.DataDetailsSale;
import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.model.records.saleProduct.input.DataRegisterSaleProduct;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;
import br.com.cepedi.Business.api.service.sale.SaleService;
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
import java.util.List;

@RestController
@RequestMapping("v1/sales")
@SecurityRequirement(name = "bearer-key")
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private SaleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsSale> register(@RequestBody @Valid DataRegisterSale data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new sale...");
        DataDetailsSale dataDetailsSale = service.register(data);
        URI uri = uriBuilder.path("/sales/{id}").buildAndExpand(dataDetailsSale.id()).toUri();
        logger.info("New sale registered with ID: {}", dataDetailsSale.id());
        return ResponseEntity.created(uri).body(dataDetailsSale);
    }

}
