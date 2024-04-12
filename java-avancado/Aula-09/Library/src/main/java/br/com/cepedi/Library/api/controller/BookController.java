package br.com.cepedi.Library.api.controller;



import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.model.records.book.input.DataUpdateBook;
import br.com.cepedi.Library.api.model.records.book.output.DataDetailsBook;
import br.com.cepedi.Library.api.service.book.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("books")
@SecurityRequirement(name = "bearer-key")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsBook> register(@RequestBody DataRegisterBook data , UriComponentsBuilder uriBuilder) {
        DataDetailsBook details = service.register(data);
        URI uri =  uriBuilder.path("/books/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsBook>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsBook> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsBook> details(@PathVariable Long id){
        DataDetailsBook details = service.findById(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsBook> update(@RequestBody @Valid DataUpdateBook data){
        DataDetailsBook details = service.update(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }

}
