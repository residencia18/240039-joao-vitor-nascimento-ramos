package br.com.cepedi.Voll.api.repositorys;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositoryDoctor {


    @Autowired
    private DoctorRepository doctorRepository;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @Test
    @DisplayName("Given activated doctors, when finding all activated doctors, then return only activated doctors")
    public void findAllActivatedDoctors() {
        // Given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        doctorRepository.save(activatedDoctor1);

        Doctor activatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.ORTHOPEDICS));
        doctorRepository.save(activatedDoctor2);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Doctor> activatedDoctorsPage = doctorRepository.findAllByActivatedTrue(pageable);

        // Then
        assertEquals(2, activatedDoctorsPage.getTotalElements());
        assertTrue(activatedDoctorsPage.getContent().stream().allMatch(Doctor::getActivated));
    }

    @Test
    @DisplayName("Given a specialty and a date, when choosing a random free doctor, then return a doctor of the specified specialty who is available on the given date")
    public void chooseDoctorRandomFreethisDate() {
        // Given
        Specialty specialty = Specialty.CARDIOLOGY;
        LocalDateTime date = LocalDateTime.now();

        // Add activated doctors with specific specialties
        Doctor doctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(specialty));
        Doctor doctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));
        Doctor doctor3 = new Doctor(generationDoctorRandomWithSpecialityDefined(specialty));

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        // When
        Doctor chosenDoctor = doctorRepository.chooseDoctorRandomFreethisDate(specialty, date);

        // Then
        assertNotNull(chosenDoctor);
        assertEquals(specialty, chosenDoctor.getSpecialty());
        assertFalse(doctorRepository.existsByDoctorIdAndData(chosenDoctor.getId(), date));
    }


    @Test
    @DisplayName("Given an activated doctor, when finding by ID, then return true")
    public void findActivatedById() {
        // Given
        Doctor doctor = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));
        doctorRepository.save(doctor);

        // When
        Boolean activated = doctorRepository.findActivatedById(doctor.getId());

        // Then
        assertTrue(activated);
    }

    @Test
    @DisplayName("Given a doctor ID and a date, when checking if an appointment exists, then return true if an appointment exists for the given doctor on the given date, otherwise return false")
    public void existsByDoctorIdAndData() {
        // Given
        Doctor doctor = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));
        doctorRepository.save(doctor);
        LocalDateTime date = LocalDateTime.now();

        // When
        Boolean exists = doctorRepository.existsByDoctorIdAndData(doctor.getId(), date);

        // Then
        assertFalse(exists); // Assuming no appointment exists for this doctor at this date
    }



    private DataRegisterDoctor generationDoctorRandomWithSpecialityDefined(Specialty specialty) {
        return new DataRegisterDoctor(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                faker.number().digits(6),
                specialty,
                createAddressData()
        );
    }



    private DataRegisterAddress createAddressData() {
        return new DataRegisterAddress(
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




