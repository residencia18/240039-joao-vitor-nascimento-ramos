package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.model.records.patient.output.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
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
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsPatient> register(@RequestBody @Valid DataRegisterPatient data , UriComponentsBuilder uriBuilder){
        Patient patient = new Patient(data);
        repository.save(patient);

        URI uri =  uriBuilder.path("/doctors/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsPatient(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsPatient>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActivatedTrue(pageable).map(DataDetailsPatient::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsPatient> detailsDoctor(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailsPatient> updateDoctor(@RequestBody @Valid DataUpdatePatient data){
        Patient patient = repository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabledDoctor(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        patient.logicalDelete();
        return ResponseEntity.noContent().build();
    }


}
