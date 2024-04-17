package br.com.cepedi.Library.api.controller.V2;


import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Library.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Library.api.service.client.ClientService;
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
@RequestMapping("v2/clients")
@SecurityRequirement(name = "bearer-key")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> register(@RequestBody @Valid DataRegisterClient data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new client...");
        DataDetailsClient dataDetailsClient = service.register(data,uriBuilder);
        URI uri = uriBuilder.path("/clients/{id}").buildAndExpand(dataDetailsClient.id()).toUri();
        logger.info("New client registered with ID: {}", dataDetailsClient.id());
        return ResponseEntity.created(uri).body(dataDetailsClient);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsClient>> list(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        logger.info("Fetching list of clients...");
        Page<DataDetailsClient> page = service.list(pageable);
        logger.info("List of clients fetched successfully.");
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsClient> details(@PathVariable Long id){
        logger.info("Fetching details of client with ID: {}", id);
        DataDetailsClient details = service.findById(id);
        logger.info("Details of client with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsClient> update(@RequestBody @Valid DataUpdateClient data){
        logger.info("Updating client with ID: {}", data.id());
        DataDetailsClient details = service.update(data);
        logger.info("Client with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling client with ID: {}", id);
        service.disabled(id);
        logger.info("Client with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }

}
