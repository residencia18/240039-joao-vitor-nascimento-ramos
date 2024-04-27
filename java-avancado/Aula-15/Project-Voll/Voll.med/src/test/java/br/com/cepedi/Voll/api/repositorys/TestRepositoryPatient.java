package br.com.cepedi.Voll.api.repositorys;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Random.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositoryPatient {

    @Autowired
    private PatientRepository patientRepository;

    private final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @BeforeEach
    void setup(){
        patientRepository.deleteAll();
    }


    @Test
    @DisplayName("Given activated and deactivated patients, when finding all activated patients, then return only activated patients")
    public void findAllActivatedPatients() {
        // Given
        Patient activatedPatient = new Patient(generationPatientRandom());
        Patient patientSaveActivated = patientRepository.save(activatedPatient);

        Patient deactivatedPatient = new Patient(generationPatientRandom());
        deactivatedPatient.setActivated(false);
        patientRepository.save(deactivatedPatient);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Patient> activatedPatientsPage = patientRepository.findAllByActivatedTrue(pageable);

        // Then
        assertEquals(1, activatedPatientsPage.getTotalElements());
        assertTrue(activatedPatientsPage.getContent().get(0).getActivated());
        assertEquals(patientSaveActivated,activatedPatientsPage.getContent().get(0));

    }

    @Test
    @DisplayName("Given activated and deactivated patients, when finding all activated patients, then return all patients")
    public void findAllPatients() {
        // Given
        Patient activatedPatient = new Patient(generationPatientRandom());
        patientRepository.save(activatedPatient);

        Patient deactivatedPatient = new Patient(generationPatientRandom());
        deactivatedPatient.setActivated(false);
        patientRepository.save(deactivatedPatient);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Patient> PatientsPage = patientRepository.findAll(pageable);

        // Then
        assertEquals(2, PatientsPage.getTotalElements());
        assertTrue(PatientsPage.getContent().get(0).getActivated());
        assertFalse(PatientsPage.getContent().get(1).getActivated());
        assertEquals(activatedPatient.getAddress(),PatientsPage.getContent().get(0).getAddress());
        assertEquals(deactivatedPatient.getAddress(),PatientsPage.getContent().get(1).getAddress());
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
        Boolean activated = patientRepository.findActivatedById(1L);

        // Then
        assertNull(activated);
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

     @Test
     @DisplayName("Find Activated Patient by Email")
     public void findActivatedPatientByEmail(){
        //given
         Patient activatedPatient = new Patient(generationPatientRandom());
         patientRepository.save(activatedPatient);
         //when
         Patient result = patientRepository.findByPatientByEmail(activatedPatient.getEmail());
         //then
         assertNotNull(result);
         assertEquals(activatedPatient.getEmail(),result.getEmail());
     }

     @Test
     @DisplayName("Find All Activated Patient By firstName")
     public void findAllActivatedPatientByFirstName(){
        //given
         Patient activatedPatient1 = new Patient(generationPatientRandom());
         activatedPatient1.setName("Joao Paulo");
         patientRepository.save(activatedPatient1);
         Patient activatedPatient2 = new Patient(generationPatientRandom());
         activatedPatient2.setName("Joao Pedro");
         patientRepository.save(activatedPatient2);
         Patient activatedPatient3 = new Patient(generationPatientRandom());
         activatedPatient3.setName("Pedro Paulo");
         patientRepository.save(activatedPatient3);
         Patient activatedPatient4 = new Patient(generationPatientRandom());
         activatedPatient4.setName("Pedro Joao");
         patientRepository.save(activatedPatient4);

         Pageable pageable = PageRequest.of(0,10);
         Page<Patient> activatedPatientsWithFirstNameJoao = patientRepository.findByPatientsByFirstName("Joao",pageable);
         assertEquals(2,activatedPatientsWithFirstNameJoao.getContent().size());
     }

    @Test
    @DisplayName("Find All Activated Patient By lastName")
    public void findAllActivatedPatientByLastName(){
        //given
        Patient activatedPatient1 = new Patient(generationPatientRandom());
        activatedPatient1.setName("Joao Paulo");
        patientRepository.save(activatedPatient1);
        Patient activatedPatient2 = new Patient(generationPatientRandom());
        activatedPatient2.setName("Joao Pedro");
        patientRepository.save(activatedPatient2);
        Patient activatedPatient3 = new Patient(generationPatientRandom());
        activatedPatient3.setName("Pedro Paulo");
        patientRepository.save(activatedPatient3);
        Patient activatedPatient4 = new Patient(generationPatientRandom());
        activatedPatient4.setName("Pedro Joao");
        patientRepository.save(activatedPatient4);

        Pageable pageable = PageRequest.of(0,10);
        Page<Patient> activatedPatientsWithFirstNameJoao = patientRepository.findByPatientsByLastName("Paulo",pageable);
        assertEquals(2,activatedPatientsWithFirstNameJoao.getContent().size());
    }


    @Test
    @DisplayName("Find All Activated Patient with determined city and neighborhood")
    public void findPatientWithDeterminedCityAndNeighborhood(){
        //given
        Patient activatedPatient1 = new Patient(generationPatientRandom());
        activatedPatient1.setName("Joao Paulo");
        activatedPatient1.getAddress().setCity("New York");
        activatedPatient1.getAddress().setNeighborhood("Local 1");
        patientRepository.save(activatedPatient1);
        Patient activatedPatient2 = new Patient(generationPatientRandom());
        activatedPatient2.setName("Joao Paulo");
        activatedPatient2.getAddress().setCity("New York");
        activatedPatient2.getAddress().setNeighborhood("Local 2");
        patientRepository.save(activatedPatient2);
        Patient activatedPatient3 = new Patient(generationPatientRandom());
        activatedPatient3.setName("Joao Paulo");
        activatedPatient3.getAddress().setCity("Sao Paulo");
        activatedPatient3.getAddress().setNeighborhood("Local 1");
        patientRepository.save(activatedPatient3);

        Pageable pageable = PageRequest.of(0,10);
        Page<Patient> activatedPatientsWithDeterminedAddress = patientRepository.findByPatientByCityAndNeighborhood(activatedPatient1.getAddress().getCity(),
                activatedPatient1.getAddress().getNeighborhood(),pageable);

        assertEquals(1,activatedPatientsWithDeterminedAddress.getContent().size());
        assertEquals(activatedPatient1.getAddress().getCity(),activatedPatientsWithDeterminedAddress.getContent().get(0).getAddress().getCity());
        assertEquals(activatedPatient1.getAddress().getNeighborhood(),activatedPatientsWithDeterminedAddress.getContent().get(0).getAddress().getNeighborhood());

    }

    @Test
    @DisplayName("Find getReferenceById ")
    public void findPatientWithGetReferenceById(){
        //given
        Patient activatedPatient1 = new Patient(generationPatientRandom());
        activatedPatient1 = patientRepository.save(activatedPatient1);
        Patient patient = patientRepository.getReferenceById(activatedPatient1.getId());
        assertNotNull(patient);
        assertEquals(activatedPatient1,patient);
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
