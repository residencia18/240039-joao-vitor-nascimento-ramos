package br.com.cepedi.Library.api.controller;

import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import br.com.cepedi.Library.api.model.records.author.output.DataDetailsAuthor;
import br.com.cepedi.Library.api.service.author.AuthorService;
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
@RequestMapping("authors")
@SecurityRequirement(name = "bearer-key")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<DataDetailsAuthor> register(@RequestBody DataRegisterAuthor data ,  UriComponentsBuilder uriBuilder) {
        logger.info("Registering new author...");
        DataDetailsAuthor details = service.register(data);
        URI uri =  uriBuilder.path("/authors/{id}").buildAndExpand(details.id()).toUri();
        logger.info("New author registered with ID: {}", details.id());
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsAuthor>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of authors...");
        Page<DataDetailsAuthor> page = service.list(pageable);
        logger.info("List of authors fetched successfully.");
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsAuthor> details(@PathVariable Long id){
        logger.info("Fetching details of author with ID: {}", id);
        DataDetailsAuthor details = service.findById(id);
        logger.info("Details of author with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsAuthor> update(@RequestBody @Valid DataUpdateAuthor data){
        logger.info("Updating author with ID: {}", data.id());
        DataDetailsAuthor details = service.update(data);
        logger.info("Author with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling author with ID: {}", id);
        service.disabled(id);
        logger.info("Author with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
