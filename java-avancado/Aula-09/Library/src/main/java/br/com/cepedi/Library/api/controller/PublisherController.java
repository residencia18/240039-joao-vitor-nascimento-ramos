package br.com.cepedi.Library.api.controller;

import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import br.com.cepedi.Library.api.model.records.publisher.output.DataDetailsPublisher;
import br.com.cepedi.Library.api.service.PublisherService;
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
@RequestMapping("publishers")
public class PublisherController {


    @Autowired
    private PublisherService service;

    @PostMapping
    public ResponseEntity<DataDetailsPublisher> register(@RequestBody DataRegisterPublisher data , UriComponentsBuilder uriBuilder) {
        DataDetailsPublisher details = service.register(data);

        URI uri =  uriBuilder.path("/publishers/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsPublisher>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsPublisher> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsPublisher> details(@PathVariable Long id){
        DataDetailsPublisher details = service.findById(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsPublisher> update(@RequestBody @Valid DataUpdatePublisher data){
        DataDetailsPublisher details = service.update(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }

}
