package br.com.cepedi.Voll.api.services.doctor;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import br.com.cepedi.Voll.api.services.doctor.validations.disabled.ValidationDisabledDoctor;
import br.com.cepedi.Voll.api.services.doctor.validations.register.ValidationRegisterDoctor;
import br.com.cepedi.Voll.api.services.doctor.validations.update.ValidationUpdateDoctor;
import com.github.javafaker.Faker;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class TestServiceDoctor {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private List<ValidationUpdateDoctor> validationUpdateDoctors;

    @Mock
    private List<ValidationDisabledDoctor> validationDisabledDoctors;


    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();
    private List<DataRegisterDoctor> dataRegisterDoctors = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        doctorRepository.deleteAll();

        // Given
        for (int i = 0; i < 15; i++) {
            dataRegisterDoctors.add(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));
        }
        for (int i = 0; i < 15; i++) {
            dataRegisterDoctors.add(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        }
    }

    @Test
    @DisplayName("Register new doctor")
    public void shouldRegisterNewDoctor() {
        // Given
        Doctor doctorSaved = new Doctor(dataRegisterDoctors.get(0));

        // When
        DataDetailsDoctor registeredDoctorDetails = doctorService.register(dataRegisterDoctors.get(0));

        // Then
        assertNotNull(registeredDoctorDetails);
        assertEquals(registeredDoctorDetails.name(), doctorSaved.getName());
        assertEquals(registeredDoctorDetails.email(), doctorSaved.getEmail());
        assertEquals(registeredDoctorDetails.specialty(), doctorSaved.getSpecialty());
    }

    @Test
    @DisplayName("List doctors")
    public void shouldListDoctors() {
        Doctor doctor1 = new Doctor(dataRegisterDoctors.get(0));
        Doctor doctor2 = new Doctor(dataRegisterDoctors.get(10));
        doctorService.register(dataRegisterDoctors.get(0));
        doctorService.register(dataRegisterDoctors.get(10));

        // When
        List<Doctor> doctorsList = Arrays.asList(doctor1, doctor2);
        Pageable pageable = PageRequest.of(0, 10);

        Page<Doctor> doctorsPage = new PageImpl<>(doctorsList, pageable, doctorsList.size());

        when(doctorRepository.findAllByActivatedTrue(any())).thenReturn(doctorsPage);

        Page<DataDetailsDoctor> patientPage = doctorService.list(pageable);


        // Then
        assertNotNull(doctorsPage);
        assertEquals(2, doctorsPage.getTotalElements());

        List<DataDetailsDoctor> doctors = patientPage.getContent();
        for (DataDetailsDoctor doctor : doctors) {
            assertNotNull(doctor.name());
            assertNotNull(doctor.email());
            assertNotNull(doctor.address());
        }
    }

    @Test
    @DisplayName("Update doctor")
    public void shouldUpdateDoctor() {

        // Given
        Doctor doctor = new Doctor(dataRegisterDoctors.get(0));
        when(doctorRepository.getReferenceById(anyLong())).thenReturn(doctor);


        // Agora, crie os dados de atualização
        DataUpdateDoctor updateData = new DataUpdateDoctor("New Name", "joao@email.com", null ,
                new DataRegisterAddress("Street", "City", "12345", "State", "XX", null, null));

        // When
        DataDetailsDoctor updatedDoctorDetails = doctorService.update(1L,updateData);

        // Then
        assertNotNull(updatedDoctorDetails);
        assertEquals(updateData.name(), updatedDoctorDetails.name());
        assertNotEquals(updateData.email(), updatedDoctorDetails.email());
        assertEquals(updateData.dataAddress().cep(), updatedDoctorDetails.address().cep());
    }

    @Test
    @DisplayName("Disabled doctor - Doctor is disabled")
    public void shouldThrowExceptionWhenUpdatingDisabledDoctor() {
        // Given
        Doctor doctor = new Doctor(dataRegisterDoctors.get(0));
        when(doctorRepository.getReferenceById(any())).thenReturn(doctor);

        //When
        doctorService.disabled(doctor.getId());

        //Then
        assertFalse(doctor.getActivated());

    }

    @Test
    @DisplayName("Details doctor with success")
    public void dataDetailsDoctor() {
        // Given
        Doctor doctor = new Doctor(dataRegisterDoctors.get(0));
        doctor.setId(1L);
        when(doctorRepository.getReferenceById(any())).thenReturn(doctor);
        // When
        DataDetailsDoctor details = doctorService.details(1L);

        // Then
        assertEquals(doctor.getName(),details.name());
        assertEquals(doctor.getCrm(),details.crm());
        assertEquals(doctor.getEmail(),details.email());
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
