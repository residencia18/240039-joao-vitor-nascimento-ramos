package br.com.cepedi.Voll.api.controller;


import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.model.records.patient.output.DataDetailsPatient;
import br.com.cepedi.Voll.api.services.patient.PatientService;
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
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsPatient> register(@RequestBody @Valid DataRegisterPatient data , UriComponentsBuilder uriBuilder){
        DataDetailsPatient details = service.register(data);
        URI uri =  uriBuilder.path("/doctors/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsPatient>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsPatient> page =  service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsPatient> detailsDoctor(@PathVariable Long id){
        DataDetailsPatient details = service.details(id);
        return ResponseEntity.ok(details);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsPatient> update(@RequestBody @Valid DataUpdatePatient data){
        DataDetailsPatient details = service.update(data);
        return ResponseEntity.ok(details);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }


}
