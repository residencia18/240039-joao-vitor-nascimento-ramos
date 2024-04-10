package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.patients.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.doctors.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.patients.output.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
