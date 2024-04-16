package br.com.cepedi.Voll.api.repositorys;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
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


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositoryPatient {

    @Autowired
    private PatientRepository patientRepository;



    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();


    @Test
    @DisplayName("Given activated and deactivated patients, when finding all activated patients, then return only activated patients")
    public void findAllActivatedPatients() {
        // Given
        Patient activatedPatient = new Patient(generationPatientRandom());
        patientRepository.save(activatedPatient);

        Patient deactivatedPatient = new Patient(generationPatientRandom());
        deactivatedPatient.setActivated(false);
        patientRepository.save(deactivatedPatient);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Patient> activatedPatientsPage = patientRepository.findAllByActivatedTrue(pageable);

        // Then
        assertEquals(1, activatedPatientsPage.getTotalElements());
        assertTrue(activatedPatientsPage.getContent().get(0).getActivated());
    }

    @Test
    @DisplayName("Given a patient ID, when finding activated status by ID, then return activated status")
    public void findActivatedStatusById() {
        // Given
        Patient patient = new Patient(generationPatientRandom());
        patient.setActivated(true);
        Patient savedPatient = patientRepository.save(patient);

        // When
        Boolean activated = patientRepository.findActivatedById(savedPatient.getId());

        // Then
        assertTrue(activated);
    }

    @Test
    @DisplayName("Given a patient ID, when finding activated status by ID for non-existent patient, then return null")
    public void findActivatedStatusForNonExistentPatient() {
        // When
        Boolean activated = patientRepository.findActivatedById(-1L);

        // Then
        assertNull(activated);
    }

    public void findAllActivatedPatientsWithCustomQuery() {
        // Given
        Patient activatedPatient1 = new Patient(generationPatientRandom());
        activatedPatient1.setActivated(true);
        patientRepository.save(activatedPatient1);

        Patient activatedPatient2 = new Patient(generationPatientRandom());
        activatedPatient2.setActivated(true);
        patientRepository.save(activatedPatient2);
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Patient> activatedPatients = patientRepository.findAllByActivatedTrue(pageable);

        // Then
        assertEquals(2, activatedPatients.getTotalElements());
        assertEquals(2, activatedPatients.getContent().size());
    }

    @Test
    @DisplayName("Given deactivated patients, when finding all activated patients with custom query, then return empty list")
    public void findAllActivatedPatientsWithCustomQueryNoResults() {
        // Given
        Patient deactivatedPatient1 = new Patient(generationPatientRandom());
        deactivatedPatient1.setActivated(false);
        patientRepository.save(deactivatedPatient1);

        Patient deactivatedPatient2 = new Patient(generationPatientRandom());
        deactivatedPatient2.setActivated(false);
        patientRepository.save(deactivatedPatient2);

        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Patient> activatedPatients = patientRepository.findAllByActivatedTrue(pageable);

        // Then
        assertTrue(activatedPatients.isEmpty());
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
