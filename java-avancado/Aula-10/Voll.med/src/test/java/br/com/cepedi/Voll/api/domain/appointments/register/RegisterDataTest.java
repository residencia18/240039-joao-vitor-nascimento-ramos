package br.com.cepedi.Voll.api.domain.appointments.register;


import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterDataTest{

    @Autowired
    private GenerationDataTest generationDataTest;

    @Autowired
    private TestEntityManager em;

    public void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    public Doctor registerDoctor(Specialty speciality) {
        var doctor = new Doctor(generationDataTest.generationDoctorRandomWithSpecialityDefined(speciality));
        em.persist(doctor);
        return doctor;
    }

    public Patient registerPatient() {
        var patient = new Patient(generationDataTest.generationPatientRandom());
        System.out.println(patient.getPhoneNumber());
        em.persist(patient);
        return patient;
    }
}