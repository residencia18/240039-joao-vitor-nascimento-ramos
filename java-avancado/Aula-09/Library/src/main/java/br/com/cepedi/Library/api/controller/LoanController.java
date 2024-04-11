package br.com.cepedi.Library.api.controller;


import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.output.DataDetailsAuthor;
import br.com.cepedi.Library.api.model.records.book.input.DataUpdateBook;
import br.com.cepedi.Library.api.model.records.book.output.DataDetailsBook;
import br.com.cepedi.Library.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.model.records.loan.input.DataUpdateLoan;
import br.com.cepedi.Library.api.model.records.loan.output.DataDetailsLoan;
import br.com.cepedi.Library.api.service.LoanService;
import jakarta.validation.Valid;
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
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping
    public ResponseEntity<DataDetailsLoan> register(@RequestBody DataRegisterLoan data , UriComponentsBuilder uriBuilder) {
        System.out.println("OI");
        DataDetailsLoan details = service.register(data);
        URI uri =  uriBuilder.path("/loans/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }


    @GetMapping
    public ResponseEntity<Page<DataDetailsLoan>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsLoan> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsLoan> details(@PathVariable Long id){
        DataDetailsLoan details = service.findById(id);
        return ResponseEntity.ok(details);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsLoan> update(@RequestBody @Valid DataUpdateLoan data){
        DataDetailsLoan details = service.update(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.finish(id);
        return ResponseEntity.noContent().build();
    }

}
