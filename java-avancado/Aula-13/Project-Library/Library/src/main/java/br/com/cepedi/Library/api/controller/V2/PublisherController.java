package br.com.cepedi.Library.api.controller.V2;

import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import br.com.cepedi.Library.api.model.records.publisher.output.DataDetailsPublisher;
import br.com.cepedi.Library.api.service.publisher.PublisherService;
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
@RequestMapping("v2/publishers")
@SecurityRequirement(name = "bearer-key")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    private PublisherService service;

    @PostMapping
    public ResponseEntity<DataDetailsPublisher> register(@RequestBody DataRegisterPublisher data , UriComponentsBuilder uriBuilder) {
        logger.info("Registering new publisher...");
        DataDetailsPublisher details = service.register(data);
        URI uri =  uriBuilder.path("/publishers/{id}").buildAndExpand(details.id()).toUri();
        logger.info("New publisher registered with ID: {}", details.id());
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsPublisher>> list(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        logger.info("Fetching list of publishers...");
        Page<DataDetailsPublisher> page = service.list(pageable);
        logger.info("List of publishers fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsPublisher> details(@PathVariable Long id){
        logger.info("Fetching details of publisher with ID: {}", id);
        DataDetailsPublisher details = service.findById(id);
        logger.info("Details of publisher with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsPublisher> update(@RequestBody @Valid DataUpdatePublisher data){
        logger.info("Updating publisher with ID: {}", data.id());
        DataDetailsPublisher details = service.update(data);
        logger.info("Publisher with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling publisher with ID: {}", id);
        service.disabled(id);
        logger.info("Publisher with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }

}
