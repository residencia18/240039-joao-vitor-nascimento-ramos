package br.com.cepedi.Voll.api.repositorys;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositoryAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    @DisplayName("Given a patient ID, start date, and end date, when checking if appointments exist for the patient within the specified date range, then return true if appointments exist, otherwise return false")
    public void existsByPatientIdAndDataBetween() {
        // Given
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(7);

        // Adding a patient to the database
        Patient patient = new Patient(generationPatientRandom());
        patientRepository.save(patient);

        // Creating an appointment for the patient within the specified date range
        Appointment appointment = new Appointment(null, null, patient, LocalDateTime.now(), null);
        appointmentRepository.save(appointment);




        System.out.println(patient.getId());
        // When
        Boolean exists = appointmentRepository.existsByPatientIdAndDataBetween(patient.getId(),  startDate.minusDays(1), endDate);

        // Then
        assertTrue(exists);

    }

    @Test
    @DisplayName("Given a patient ID, start date, and end date, when checking if appointments exist for the patient within the specified date range, then return false if no appointments exist")
    public void existsByPatientIdAndDataBetween_NoAppointments() {
        // Given
        LocalDateTime startDate = LocalDateTime.now().plusDays(8);
        LocalDateTime endDate = startDate.plusDays(7);

        // Adding a patient to the database
        Patient patient = new Patient(generationPatientRandom());
        patientRepository.save(patient);

        // Adding another appointment for the patient after the specified date range
        Appointment appointment = new Appointment(null, null, patient, endDate.plusDays(1), null);
        appointmentRepository.save(appointment);

        // When checking again
        Boolean exists = appointmentRepository.existsByPatientIdAndDataBetween(appointment.getPatient().getId(), startDate, endDate);

        // Then
        assertFalse(exists);
    }


    private DataRegisterPatient generationPatientRandom() {
        return new DataRegisterPatient(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                generationCPF(),
                createAddressData()
        );
    }

    private String generationCPF() {
        return cpfGenerator.getValidFormattedCpf(faker);
    }


    private DataAddress createAddressData() {
        return new DataAddress(
                faker.address().streetName(),
                faker.address().city(),
                faker.number().digits(8),
                faker.address().city(),
                "XX",
                null,
                null
        );
    }


}
