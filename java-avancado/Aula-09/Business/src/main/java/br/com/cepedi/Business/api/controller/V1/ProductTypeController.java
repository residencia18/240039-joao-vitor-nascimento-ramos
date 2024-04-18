package br.com.cepedi.Business.api.controller.V1;


import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.service.productType.ProductTypeService;
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
@RequestMapping("product/type")
@SecurityRequirement(name = "bearer-key")
public class ProductTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ProductTypeService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsProductType> register(@RequestBody @Valid DataRegisterProductType data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new client...");
        DataDetailsProductType dataDetailsProductType = service.register(data,uriBuilder);
        URI uri = uriBuilder.path("/product/type/{id}").buildAndExpand(dataDetailsProductType.id()).toUri();
        logger.info("New client registered with ID: {}", dataDetailsProductType.id());
        return ResponseEntity.created(uri).body(dataDetailsProductType);
    }


}
