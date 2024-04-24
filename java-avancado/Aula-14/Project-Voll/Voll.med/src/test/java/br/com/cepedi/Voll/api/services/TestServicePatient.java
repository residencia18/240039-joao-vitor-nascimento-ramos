package br.com.cepedi.Voll.api.services;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.patient.PatientService;
import br.com.cepedi.Voll.api.services.patient.validations.disabled.ValidationDisabledPatient;
import br.com.cepedi.Voll.api.services.patient.validations.update.ValidationUpdatePatient;
import com.github.javafaker.Faker;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "br.com.cepedi.Voll.api.services.patient")
public class TestServicePatient {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @MockBean
    private List<ValidationUpdatePatient> validationUpdatePatient;

    @MockBean
    private List<ValidationDisabledPatient> validationDisabledPatients;




    @Test
    @DisplayName("Register new patient")
    public void shouldRegisterNewPatient() {
        // Given
        DataRegisterPatient data = generateRandomPatientData();

        // When
        DataDetailsPatient registeredPatientDetails = patientService.register(data);

        // Then
        Patient savedPatient = patientRepository.findById(registeredPatientDetails.id()).orElse(null);
        assertEquals(data.name(), savedPatient.getName());
    }

    @Test
    @DisplayName("List patients")
    public void shouldListPatients() {
        // Given
        // Crie alguns pacientes para testar a listagem
        DataRegisterPatient patient1Data = generateRandomPatientData();
        DataRegisterPatient patient2Data = generateRandomPatientData();
        patientService.register(patient1Data);
        patientService.register(patient2Data);

        // When
        Page<DataDetailsPatient> patientPage = patientService.list(PageRequest.of(0, 10));

        // Then
        assertNotNull(patientPage);
        assertEquals(2, patientPage.getTotalElements());

        List<DataDetailsPatient> patients = patientPage.getContent();
        for (DataDetailsPatient patient : patients) {
            assertNotNull(patient.id());
            assertNotNull(patient.name());
            assertNotNull(patient.email());
            assertNotNull(patient.cpf());
            assertNotNull(patient.address());
        }
    }


    @Test
    @DisplayName("Disable patient")
    public void shouldDisablePatient() {
        // Given
        DataRegisterPatient data = generateRandomPatientData();
        Patient patient = new Patient(data);
        patient = patientRepository.save(patient);

        // When
        patientService.disabled(patient.getId());

        // Then
        Patient disabledPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertFalse(disabledPatient.getActivated());
    }

    @Test
    @DisplayName("Disable patient - Patient does not exist")
    public void shouldThrowExceptionWhenDisablingNonExistingPatient() {
        // Given
        Long nonExistingPatientId = 9999999999999L;

        // When
        assertThrows(ValidationException.class, () -> patientService.disabled(nonExistingPatientId));
    }

    @Test
    @DisplayName("Should throw exception when disabling already disabled patient")
    public void shouldThrowExceptionWhenDisablingAlreadyDisabledPatient() {
        // Given
        DataRegisterPatient data = generateRandomPatientData();

        // When
        DataDetailsPatient registeredPatientDetails = patientService.register(data);

        patientService.disabled(registeredPatientDetails.id());

        // When
        assertThrows(ValidationException.class, () -> patientService.disabled(registeredPatientDetails.id()));

        // Then: assert whatever is expected after the exception is thrown
    }

    @Test
    @DisplayName("Update patient - Patient does not exist")
    public void shouldThrowExceptionWhenUpdatingNonExistingPatient() {
        // Given
        DataUpdatePatient data = new DataUpdatePatient(99999L, "John Doe", "123456789", new DataRegisterAddress("Street", "City", "12345", "State", "XX", null, null));

        // When
        assertThrows(ValidationException.class, () -> patientService.update(data));
    }

    @Test
    @DisplayName("Update patient")
    public void shouldUpdatePatient() {
// Given
        // Crie um paciente usando o método de geração
        DataRegisterPatient registrationData = generateRandomPatientData();
        DataDetailsPatient registeredPatientDetails = patientService.register(registrationData);
        Long patientId = registeredPatientDetails.id();

        // Agora, crie os dados de atualização
        DataUpdatePatient updateData = new DataUpdatePatient(patientId, "John Doe", null,
                new DataRegisterAddress("Street", "City", "12345", "State", "XX", null, null));


        // When
        DataDetailsPatient updatedPatientDetails = patientService.update(updateData);

        // Then
        assertEquals(updateData.name(), updatedPatientDetails.name());
        assertEquals(updateData.dataAddress().cep(), updatedPatientDetails.address().cep());
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
