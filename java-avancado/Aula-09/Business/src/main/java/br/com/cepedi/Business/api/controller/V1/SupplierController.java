package br.com.cepedi.Business.api.controller.V1;

import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataRegisterSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import br.com.cepedi.Business.api.service.supplier.SupplierService;
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
@RequestMapping("v1/supplier")
@SecurityRequirement(name = "bearer-key")
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private SupplierService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsSupplier> register(@RequestBody @Valid DataRegisterSupplier data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new supplier...");
        DataDetailsSupplier dataDetailsSupplier = service.register(data);
        URI uri = uriBuilder.path("/supplier/{id}").buildAndExpand(dataDetailsSupplier.id()).toUri();
        logger.info("New supplier registered with ID: {}", dataDetailsSupplier.id());
        return ResponseEntity.created(uri).body(dataDetailsSupplier);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsSupplier>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of supplier...");
        Page<DataDetailsSupplier> page = service.list(pageable);
        logger.info("List of supplier fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsSupplier> details(@PathVariable Long id){
        logger.info("Fetching details of supplier with ID: {}", id);
        DataDetailsSupplier details = service.findById(id);
        logger.info("Details of supplier with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailsSupplier> update(@PathVariable Long id,  @RequestBody @Valid DataUpdateSupplier data){
        logger.info("Updating supplier with ID: {}", id);
        DataDetailsSupplier details = service.update(id , data);
        logger.info("Supplier with ID {} updated successfully.", id);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling supplier with ID: {}", id);
        service.disabled(id);
        logger.info("Supplier with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
