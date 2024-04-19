package br.com.cepedi.Business.api.controller.V1;


import br.com.cepedi.Business.api.model.records.productType.details.DataDetailsProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.service.productType.ProductTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
        logger.info("Registering new type of product...");
        DataDetailsProductType dataDetailsProductType = service.register(data,uriBuilder);
        URI uri = uriBuilder.path("/product/type/{id}").buildAndExpand(dataDetailsProductType.id()).toUri();
        logger.info("New type of product registered with ID: {}", dataDetailsProductType.id());
        return ResponseEntity.created(uri).body(dataDetailsProductType);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsProductType>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of type of product...");
        Page<DataDetailsProductType> page = service.list(pageable);
        logger.info("List of type of product fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsProductType> details(@PathVariable Long id){
        logger.info("Fetching details of type of product with ID: {}", id);
        DataDetailsProductType details = service.findById(id);
        logger.info("Details of type of product with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }



}
