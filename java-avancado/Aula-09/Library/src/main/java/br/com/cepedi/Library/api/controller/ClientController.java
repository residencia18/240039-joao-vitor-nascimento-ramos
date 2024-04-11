package br.com.cepedi.Library.api.controller;


import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Library.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Library.api.service.ClientService;
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
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> register(@RequestBody @Valid DataRegisterClient data , UriComponentsBuilder uriBuilder){
        DataDetailsClient dataDetailsClient = service.register(data,uriBuilder);
        URI uri = uriBuilder.path("/clients/{id}").buildAndExpand(dataDetailsClient.id()).toUri();
        return ResponseEntity.created(uri).body(dataDetailsClient);
    }
    @GetMapping
    public ResponseEntity<Page<DataDetailsClient>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsClient> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsClient> details(@PathVariable Long id){
        DataDetailsClient details = service.findById(id);
        return ResponseEntity.ok(details);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> update(@RequestBody @Valid DataUpdateClient data){
        DataDetailsClient details = service.update(data);
        return ResponseEntity.ok(details);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }

}
