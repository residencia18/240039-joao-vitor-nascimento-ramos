package br.com.cepedi.Library.api.controller;

import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import br.com.cepedi.Library.api.model.records.author.output.DataDetailsAuthor;
import br.com.cepedi.Library.api.service.author.AuthorService;
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
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<DataDetailsAuthor> register(@RequestBody DataRegisterAuthor data ,  UriComponentsBuilder uriBuilder) {
        DataDetailsAuthor details = service.register(data);
        URI uri =  uriBuilder.path("/authors/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsAuthor>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsAuthor> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsAuthor> details(@PathVariable Long id){
        DataDetailsAuthor details = service.findById(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsAuthor> update(@RequestBody @Valid DataUpdateAuthor data){
        DataDetailsAuthor details = service.update(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }

}
