package br.com.cepedi.Voll.api.model.records.doctor.details;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.address.details.DataDetailsAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;

public record DataDetailsDoctor(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String crm,
        Specialty specialty,
        DataDetailsAddress address,
        Boolean activated
) {
    public DataDetailsDoctor(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhoneNumber(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                new DataDetailsAddress(doctor.getAddress()),
                doctor.getActivated()
        );
    }
}