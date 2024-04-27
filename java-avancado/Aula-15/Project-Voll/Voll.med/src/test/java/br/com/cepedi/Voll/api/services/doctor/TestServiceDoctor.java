package br.com.cepedi.Voll.api.services;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import br.com.cepedi.Voll.api.services.doctor.validations.disabled.ValidationDisabledDoctor;
import br.com.cepedi.Voll.api.services.doctor.validations.update.ValidationUpdateDoctor;
import com.github.javafaker.Faker;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
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
@ComponentScan(basePackages = "br.com.cepedi.Voll.api.services.doctor")
public class TestServiceDoctor {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @MockBean
    private List<ValidationUpdateDoctor> validationsUpdate;

    @MockBean
    private List<ValidationDisabledDoctor> validationsDisabled;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();


    @BeforeEach
    public void setUp() {
        doctorRepository.deleteAll();
    }

    @Test
    @DisplayName("Register new doctor")
    public void shouldRegisterNewDoctor() {
        // Given
        DataRegisterDoctor data = generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY);

        // When
        DataDetailsDoctor registeredDoctorDetails = doctorService.register(data);

        // Then
        assertNotNull(registeredDoctorDetails);
        assertNotNull(registeredDoctorDetails.id());
        assertEquals(data.name(), registeredDoctorDetails.name());
        assertEquals(data.email(), registeredDoctorDetails.email());
        assertEquals(data.specialty(), registeredDoctorDetails.specialty());
    }

    @Test
    @DisplayName("List doctors")
    public void shouldListDoctors() {
        // Given
        // Crie alguns médicos para testar a listagem
        DataRegisterDoctor doctor1Data = generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY);
        DataRegisterDoctor doctor2Data = generationDoctorRandomWithSpecialityDefined(Specialty.ORTHOPEDICS);
        doctorService.register(doctor1Data);
        doctorService.register(doctor2Data);

        // When
        Page<DataDetailsDoctor> doctorPage = doctorService.list(PageRequest.of(0, 10));

        // Then
        assertNotNull(doctorPage);
        assertEquals(2, doctorPage.getTotalElements());

        List<DataDetailsDoctor> doctors = doctorPage.getContent();
        for (DataDetailsDoctor doctor : doctors) {
            assertNotNull(doctor.id());
            assertNotNull(doctor.name());
            assertNotNull(doctor.email());
            assertNotNull(doctor.address());
        }
    }

    @Test
    @DisplayName("Update doctor")
    public void shouldUpdateDoctor() {

        // Given
        DataRegisterDoctor registrationData = generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY);
        DataDetailsDoctor registeredDoctorDetails = doctorService.register(registrationData);
        Long doctorId = registeredDoctorDetails.id();

        // Agora, crie os dados de atualização
        DataUpdateDoctor updateData = new DataUpdateDoctor(doctorId, "New Name", "joao@email.com", null ,
                new DataRegisterAddress("Street", "City", "12345", "State", "XX", null, null));

        // When
        DataDetailsDoctor updatedDoctorDetails = doctorService.update(updateData);

        // Then
        assertEquals(updateData.name(), updatedDoctorDetails.name());
        assertNotEquals(updateData.email(), updatedDoctorDetails.email());
        assertEquals(updateData.dataAddress().cep(), updatedDoctorDetails.address().cep());
    }

    @Test
    @DisplayName("Update doctor - Doctor is disabled")
    public void shouldThrowExceptionWhenUpdatingDisabledDoctor() {
        // Given
        DataRegisterDoctor registrationData = generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY);
        DataDetailsDoctor registeredDoctorDetails = doctorService.register(registrationData);

        Doctor doctor = doctorRepository.findById(registeredDoctorDetails.id()).orElseThrow();
        doctorService.disabled(doctor.getId());


        DataUpdateDoctor data = new DataUpdateDoctor(registeredDoctorDetails.id(), "New Name", "new@email.com", null,
                new DataRegisterAddress("Street", "City", "12345", "State", "XX", null, null));


        // When/Then
        assertThrows(ValidationException.class, () -> doctorService.update(data));
    }

    // Métodos de geração de dados aleatórios para médicos (simulados)
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
