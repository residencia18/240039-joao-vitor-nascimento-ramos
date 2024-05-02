package br.com.cepedi.Voll.api.model.record.doctor.details;

import br.com.cepedi.Voll.api.model.entitys.Address;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.address.details.DataDetailsAddress;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDataDetailsDoctor {

    @Test
    public void testConstructorWithDoctor() {
        // Create a Doctor object
        Doctor doctor = new Doctor(
                1L,
                "John Doe",
                "john.doe@example.com",
                "123456789",
                "CRM123",
                Specialty.DERMATOLOGY,
                new Address(new DataRegisterAddress("123 Main St", "Suburb", "12345678", "City", "State", "Apt 101","123")),
                true
        );

        // Create a DataDetailsDoctor object using the constructor that takes a Doctor object
        DataDetailsDoctor dataDetailsDoctor = new DataDetailsDoctor(doctor);

        // Check if the DataDetailsDoctor object was constructed correctly
        assertEquals(doctor.getId(), dataDetailsDoctor.id());
        assertEquals(doctor.getName(), dataDetailsDoctor.name());
        assertEquals(doctor.getEmail(), dataDetailsDoctor.email());
        assertEquals(doctor.getPhoneNumber(), dataDetailsDoctor.phoneNumber());
        assertEquals(doctor.getCrm(), dataDetailsDoctor.crm());
        assertEquals(doctor.getSpecialty(), dataDetailsDoctor.specialty());
        assertEquals(new DataDetailsAddress(doctor.getAddress()), dataDetailsDoctor.address());
        assertEquals(doctor.getActivated(), dataDetailsDoctor.activated());
    }

}
