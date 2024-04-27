package br.com.cepedi.Voll.api.model.records.patient.details;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.details.DataDetailsAddress;

public record DataDetailsPatient(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String cpf,
        DataDetailsAddress address,
        Boolean activated
) {
    public DataDetailsPatient(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getCpf(),
                new DataDetailsAddress(patient.getAddress()),
                patient.getActivated()
        );
    }
}