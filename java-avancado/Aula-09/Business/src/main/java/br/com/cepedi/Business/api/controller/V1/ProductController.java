package br.com.cepedi.Business.api.controller.V1;


import br.com.cepedi.Business.api.model.records.product.details.DataDetailsProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.service.Product.ProductService;
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
@RequestMapping("v1/products")
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

    @GetMapping
    public ResponseEntity<Page<DataDetailsProduct>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of product...");
        Page<DataDetailsProduct> page = service.list(pageable);
        logger.info("List of product fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsProduct> details(@PathVariable Long id){
        logger.info("Fetching details of product with ID: {}", id);
        DataDetailsProduct details = service.findById(id);
        logger.info("Details of product with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailsProduct> update(@PathVariable Long id , @RequestBody @Valid DataUpdateProduct data){
        logger.info("Updating product with ID: {}", id);
        DataDetailsProduct details = service.update(id , data);
        logger.info("Product with ID {} updated successfully.", id);
        return ResponseEntity.ok(details);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling product with ID: {}", id);
        service.disabled(id);
        logger.info("Product with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
