package br.com.cepedi.Voll.api.services.patient;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.patient.validations.disabled.ValidationDisabledPatient;
import br.com.cepedi.Voll.api.services.patient.validations.update.ValidationUpdatePatient;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class TestServicePatient {


    @Mock
    private PatientRepository patientRepository;

    @Mock
    private List<ValidationDisabledPatient> validationDisabledPatients;

    @Mock
    private List<ValidationUpdatePatient> validationUpdatePatients;

    @InjectMocks
    private PatientService patientService;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    private List<DataRegisterPatient> dataRegisterPatients = new ArrayList<>();

    @BeforeEach
    void setup() {

        patientRepository.deleteAll();

        // Given
        for (int i = 0; i < 15; i++) {
            dataRegisterPatients.add(generateRandomPatientData());
        }
        MockitoAnnotations.openMocks(this);

    }


    @Test
    @DisplayName("Register new patient")
    public void shouldRegisterNewPatient() {
        //given
        Patient savedPatient = new Patient(dataRegisterPatients.get(0));
        when(patientRepository.findById(any())).thenReturn(Optional.of(savedPatient));

        // When
        DataDetailsPatient registeredPatientDetails = patientService.register(dataRegisterPatients.get(0));

        // Then
        Patient retrievedPatient = patientRepository.findById(registeredPatientDetails.id()).orElse(null);
        assertEquals(registeredPatientDetails.name(), retrievedPatient.getName());
    }

    @Test
    @DisplayName("List patients")
    public void shouldListPatients() {
        // Given
        Patient patient1 = new Patient(dataRegisterPatients.get(0));
        Patient patient2 = new Patient(dataRegisterPatients.get(1));
        patientService.register(dataRegisterPatients.get(0));
        patientService.register(dataRegisterPatients.get(1));

        List<Patient> patientsList = Arrays.asList(patient1, patient2);
        Pageable pageable = PageRequest.of(0, 10);

        Page<Patient> patientsPage = new PageImpl<>(patientsList, pageable, patientsList.size());

        when(patientRepository.findAllByActivatedTrue(any())).thenReturn(patientsPage);

        // When
        Page<DataDetailsPatient> patientPage = patientService.list(pageable);

        // Then
        assertNotNull(patientPage);
        assertEquals(2, patientPage.getTotalElements());

        List<DataDetailsPatient> patients = patientPage.getContent();
        for (DataDetailsPatient patient : patients) {
            assertNotNull(patient.name());
            assertNotNull(patient.email());
            assertNotNull(patient.cpf());
            assertNotNull(patient.address());
        }
    }


    @Test
    @DisplayName("Disable patient with success")
    public void shouldDisablePatient() {
        // Given
        Patient patient = new Patient(dataRegisterPatients.get(0));
        when(patientRepository.getReferenceById(any())).thenReturn(patient);
        // When
        patientService.disabled(patient.getId());

        // Then
        assertFalse(patient.getActivated());
    }

    @Test
    @DisplayName("Details patient with success")
    public void dataDetailsPatient() {
        // Given
        Patient patient = new Patient(dataRegisterPatients.get(0));
        patient.setId(1L);
        when(patientRepository.getReferenceById(any())).thenReturn(patient);
        // When
        DataDetailsPatient details = patientService.details(1L);

        // Then
        assertEquals(patient.getName(),details.name());
        assertEquals(patient.getCpf(),details.cpf());
        assertEquals(patient.getEmail(),details.email());
    }


    @Test
    @DisplayName("Successful Patient Update Test")
    public void testSuccessfulPatientUpdate(){
        // Given
        Patient patient = new Patient(dataRegisterPatients.get(0));
        when(patientRepository.getReferenceById(any())).thenReturn(patient);

        DataUpdatePatient dataUpdatePatient = new DataUpdatePatient(
                "New Name",
                "New Phone Number",
                new DataRegisterAddress("New Street", "New City", "12345678", "New State", "XX", null, null)
        );

        // When
        DataDetailsPatient updatedPatientDetails = patientService.update(1L, dataUpdatePatient);

        // Then
        assertNotNull(updatedPatientDetails);
        assertEquals(dataUpdatePatient.name(), patient.getName());
        assertEquals(dataUpdatePatient.phoneNumber() ,updatedPatientDetails.phoneNumber());
        assertNotEquals(dataUpdatePatient.dataAddress(), dataRegisterPatients.get(0).dataAddress());
    }



    private DataRegisterPatient generateRandomPatientData() {
        return new DataRegisterPatient(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                generateCPF(),
                createAddressData()
        );
    }

    private String generateCPF() {
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
