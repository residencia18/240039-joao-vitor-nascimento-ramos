package br.com.cepedi.Business.api.controller.V1;


import br.com.cepedi.Business.api.model.records.product.details.DataDetailsProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.service.Product.ProductService;
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
@RequestMapping("products")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ProductService service;


    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsProduct> register(@RequestBody @Valid DataRegisterProduct data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new product...");
        DataDetailsProduct dataDetailsProduct = service.register(data);
        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(dataDetailsProduct.id()).toUri();
        logger.info("New product registered with ID: {}", dataDetailsProduct.id());
        return ResponseEntity.created(uri).body(dataDetailsProduct);
    }


}
