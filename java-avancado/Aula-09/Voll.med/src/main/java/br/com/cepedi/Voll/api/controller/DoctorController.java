package br.com.cepedi.Voll.api.controller;



import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.output.DoctorDTO;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
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
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> register(@RequestBody @Valid DataRegisterDoctor data , UriComponentsBuilder uriBuilder){
        Doctor doctor = new Doctor(data);
        repository.save(doctor);

        URI uri =  uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActivatedTrue(pageable).map(DoctorDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsDoctor> detailsDoctor(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> updateDoctor(@RequestBody @Valid DataUpdateDoctor data){
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabledDoctor(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        doctor.logicalDelete();
        return ResponseEntity.noContent().build();
    }




}
