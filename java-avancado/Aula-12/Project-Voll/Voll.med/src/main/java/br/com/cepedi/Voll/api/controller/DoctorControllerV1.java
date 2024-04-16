package br.com.cepedi.Voll.api.controller;



import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
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
@RequestMapping("v1/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(DoctorControllerV1.class);

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> register(@RequestBody @Valid DataRegisterDoctor data, UriComponentsBuilder uriBuilder) {
        log.info("Registering new doctor...");
        DataDetailsDoctor details = service.register(data);
        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(details.id()).toUri();
        log.info("New doctor registered with ID: {}", details.id());
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsDoctor>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        log.info("Fetching list of doctors...");
        Page<DataDetailsDoctor> page = service.list(pageable);
        log.info("List of doctors fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsDoctor> details(@PathVariable Long id) {
        log.info("Fetching details of doctor with ID: {}", id);
        DataDetailsDoctor details = service.details(id);
        log.info("Details of doctor with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> update(@RequestBody @Valid DataUpdateDoctor data) {
        log.info("Updating doctor with ID: {}", data.id());
        DataDetailsDoctor details = service.update(data);
        log.info("Doctor with ID {} updated successfully.", data.id());
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id) {
        log.info("Disabling doctor with ID: {}", id);
        service.disabled(id);
        log.info("Doctor with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }




}
