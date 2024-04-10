package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.entitys.Address;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctors.input.doctor.Specialty;

public record DataDetailsDoctor(Long id , String name, String email, Specialty specialty , Address address) {

    public DataDetailsDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getSpecialty(), doctor.getAddress());
    }

}
