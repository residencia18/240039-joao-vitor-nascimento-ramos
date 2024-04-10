package br.com.cepedi.Library.api.controller;


import br.com.cepedi.Library.api.model.entitys.Client;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Library.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Library.api.repository.ClientRepository;
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
    private ClientRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> register(@RequestBody @Valid DataRegisterClient data , UriComponentsBuilder uriBuilder){
        Client client = new Client(data);
        repository.save(client);
        URI uri =  uriBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsClient(client));
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsClient>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActivatedTrue(pageable).map(DataDetailsClient::new);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsClient> detailsDoctor(@PathVariable Long id){
        Client client = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsClient(client));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> updateDoctor(@RequestBody @Valid DataUpdateClient data){
        Client client = repository.getReferenceById(data.id());
        client.updateData(data);

        return ResponseEntity.ok(new DataDetailsClient(client));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabledDoctor(@PathVariable Long id){
        Client client = repository.getReferenceById(id);
        client.logicalDelete();
        return ResponseEntity.noContent().build();
    }




}
