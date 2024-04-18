package br.com.cepedi.Library.api.controller.V1;



import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.model.records.book.input.DataUpdateBook;
import br.com.cepedi.Library.api.model.records.book.details.DataDetailsBook;
import br.com.cepedi.Library.api.service.book.BookService;
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
@RequestMapping("v1/books")
@SecurityRequirement(name = "bearer-key")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsBook> register(@RequestBody DataRegisterBook data , UriComponentsBuilder uriBuilder) {
        logger.info("Registering new book...");
        DataDetailsBook details = service.register(data);
        URI uri =  uriBuilder.path("/books/{id}").buildAndExpand(details.id()).toUri();
        logger.info("New book registered with ID: {}", details.id());
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsBook>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of books...");
        Page<DataDetailsBook> page = service.list(pageable);
        logger.info("List of books fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsBook> details(@PathVariable Long id){
        logger.info("Fetching details of book with ID: {}", id);
        DataDetailsBook details = service.findById(id);
        logger.info("Details of book with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsBook> update(@RequestBody @Valid DataUpdateBook data){
        logger.info("Updating book with ID: {}", data.id());
        DataDetailsBook details = service.update(data);
        logger.info("Book with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling book with ID: {}", id);
        service.disabled(id);
        logger.info("Book with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
