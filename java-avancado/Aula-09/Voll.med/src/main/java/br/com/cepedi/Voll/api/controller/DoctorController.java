package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.input.doctor.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.input.doctor.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.output.dtos.DoctorDTO;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterDoctor data){
        Doctor doctor = new Doctor(data);
        repository.save(doctor);
    }

    @GetMapping
    public Page<DoctorDTO> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return repository.findAllByActivatedTrue(pageable).map(DoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DataUpdateDoctor data){
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void disabledDoctor(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        doctor.logicalDelete();
    }




}
