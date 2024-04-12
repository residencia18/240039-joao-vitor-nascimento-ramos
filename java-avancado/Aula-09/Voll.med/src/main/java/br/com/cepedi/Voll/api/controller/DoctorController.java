package br.com.cepedi.Voll.api.controller;



import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
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
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> register(@RequestBody @Valid DataRegisterDoctor data , UriComponentsBuilder uriBuilder){
        DataDetailsDoctor details = service.register(data);
        URI uri =  uriBuilder.path("/doctors/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsDoctor>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<DataDetailsDoctor> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsDoctor> details(@PathVariable Long id){
        DataDetailsDoctor details = service.details(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> update(@RequestBody @Valid DataUpdateDoctor data){
        DataDetailsDoctor details = service.update(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }




}
