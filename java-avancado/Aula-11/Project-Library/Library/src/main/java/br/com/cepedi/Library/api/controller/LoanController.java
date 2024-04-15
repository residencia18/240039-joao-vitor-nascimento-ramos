package br.com.cepedi.Library.api.controller;


import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.model.records.loan.input.DataUpdateLoan;
import br.com.cepedi.Library.api.model.records.loan.output.DataDetailsLoan;
import br.com.cepedi.Library.api.service.loan.LoanService;
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
@RequestMapping("loans")
@SecurityRequirement(name = "bearer-key")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService service;

    @PostMapping
    public ResponseEntity<DataDetailsLoan> register(@RequestBody DataRegisterLoan data , UriComponentsBuilder uriBuilder) {
        logger.info("Registering new loan...");
        DataDetailsLoan details = service.register(data);
        URI uri =  uriBuilder.path("/loans/{id}").buildAndExpand(details.id()).toUri();
        logger.info("New loan registered with ID: {}", details.id());
        return ResponseEntity.created(uri).body(details);
    }


    @GetMapping
    public ResponseEntity<Page<DataDetailsLoan>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of loans...");
        Page<DataDetailsLoan> page = service.list(pageable);
        logger.info("List of loans fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsLoan> details(@PathVariable Long id){
        logger.info("Fetching details of loan with ID: {}", id);
        DataDetailsLoan details = service.findById(id);
        logger.info("Details of loan with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsLoan> update(@RequestBody @Valid DataUpdateLoan data){
        logger.info("Updating loan with ID: {}", data.id());
        DataDetailsLoan details = service.update(data);
        logger.info("Loan with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Finishing loan with ID: {}", id);
        service.finish(id);
        logger.info("Loan with ID {} finished successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
