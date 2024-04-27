package br.com.cepedi.Voll.api.repositorys;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.print.Doc;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Random.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositoryDoctor {


    @Autowired
    private DoctorRepository doctorRepository;

    private final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @BeforeEach
    void setup(){
        doctorRepository.deleteAll();
    }

    @Test
    @DisplayName("Given activated doctors, when finding all activated doctors, then return only activated doctors")
    public void findAllActivatedDoctors() {
        // Given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        doctorRepository.save(activatedDoctor1);

        Doctor activatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.ORTHOPEDICS));
        activatedDoctor2.setActivated(false);

        doctorRepository.save(activatedDoctor2);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Doctor> activatedDoctorsPage = doctorRepository.findAllByActivatedTrue(pageable);

        // Then
        assertEquals(1, activatedDoctorsPage.getTotalElements());
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

    @Test
    @DisplayName("Given activated and deactivated doctors, when finding all activated doctors, then return all doctors")
    public void findAllPatients() {
        // Given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        doctorRepository.save(activatedDoctor1);

        Doctor desactivatedDoctor = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.ORTHOPEDICS));
        desactivatedDoctor.setActivated(false);
        doctorRepository.save(desactivatedDoctor);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        // Then
        assertEquals(2, doctorPage.getTotalElements());
        assertTrue(doctorPage.getContent().get(0).getActivated());
        assertFalse(doctorPage.getContent().get(1).getActivated());
        assertEquals(activatedDoctor1.getAddress(),doctorPage.getContent().get(0).getAddress());
        assertEquals(desactivatedDoctor.getAddress(),doctorPage.getContent().get(1).getAddress());
    }

    @Test
    @DisplayName("Given a doctor ID, when finding activated status by ID for non-existent doctor, then return null")
    public void findActivatedStatusForNonExistentPatient() {
        // When
        Boolean activated = doctorRepository.findActivatedById(1L);

        // Then
        assertNull(activated);
    }

    @Test
    @DisplayName("Given deactivated doctors, when finding all activated doctors with custom query, then return empty list")
    public void findAllActivatedPatientsWithCustomQueryNoResults() {
        // Given
        Doctor desactivatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        desactivatedDoctor1.setActivated(false);
        doctorRepository.save(desactivatedDoctor1);

        Doctor desactivatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.ORTHOPEDICS));
        desactivatedDoctor2.setActivated(false);
        doctorRepository.save(desactivatedDoctor2);

        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Doctor> activatedDoctors = doctorRepository.findAllByActivatedTrue(pageable);

        // Then
        assertTrue(activatedDoctors.isEmpty());
    }

    @Test
    @DisplayName("Find All Activated Patient By firstName")
    public void findAllActivatedPatientByFirstName(){
        //given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor1.setName("Joao Paulo");
        doctorRepository.save(activatedDoctor1);
        Doctor activatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor2.setName("Joao Pedro");
        doctorRepository.save(activatedDoctor2);
        Doctor activatedDoctor3 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor3.setName("Pedro Paulo");
        doctorRepository.save(activatedDoctor3);
        Doctor activatedDoctor4 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor4.setName("Pedro Joao");
        doctorRepository.save(activatedDoctor4);

        Pageable pageable = PageRequest.of(0,10);
        Page<Doctor> activatedDoctorsWithFirstNameJoao = doctorRepository.findByDoctorsByFirstName("Joao",pageable);
        assertEquals(2,activatedDoctorsWithFirstNameJoao.getContent().size());
    }

    @Test
    @DisplayName("Find All Activated Patient By lastName")
    public void findAllActivatedPatientByLastName(){
        //given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor1.setName("Joao Paulo");
        doctorRepository.save(activatedDoctor1);
        Doctor activatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor2.setName("Joao Pedro");
        doctorRepository.save(activatedDoctor2);
        Doctor activatedDoctor3 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor3.setName("Pedro Paulo");
        doctorRepository.save(activatedDoctor3);
        Doctor activatedDoctor4 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor4.setName("Pedro Joao");
        doctorRepository.save(activatedDoctor4);

        Pageable pageable = PageRequest.of(0,10);
        Page<Doctor> activatedDoctorsWithLastNamePaulo = doctorRepository.findByDoctorsByLastName("Paulo",pageable);
        assertEquals(2,activatedDoctorsWithLastNamePaulo.getContent().size());
    }

    @Test
    @DisplayName("Find All Activated Patient with determined city and neighborhood")
    public void findPatientWithDeterminedCityAndNeighborhood(){
        //given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor1.setName("Joao Paulo");
        activatedDoctor1.getAddress().setCity("New York");
        activatedDoctor1.getAddress().setNeighborhood("Local 1");
        doctorRepository.save(activatedDoctor1);
        Doctor activatedDoctor2 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor2.setName("Joao Paulo");
        activatedDoctor2.getAddress().setCity("New York");
        activatedDoctor2.getAddress().setNeighborhood("Local 2");
        doctorRepository.save(activatedDoctor2);
        Doctor activatedDoctor3 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor3.setName("Joao Paulo");
        activatedDoctor3.getAddress().setCity("Sao Paulo");
        activatedDoctor3.getAddress().setNeighborhood("Local 1");
        doctorRepository.save(activatedDoctor3);

        Pageable pageable = PageRequest.of(0,10);
        Page<Doctor> activatedPatientsWithDeterminedAddress = doctorRepository.findByDoctorByCityAndNeighborhood(activatedDoctor1.getAddress().getCity(),
                activatedDoctor1.getAddress().getNeighborhood(),pageable);

        assertEquals(1,activatedPatientsWithDeterminedAddress.getContent().size());
        assertEquals(activatedDoctor1.getAddress().getCity(),activatedPatientsWithDeterminedAddress.getContent().get(0).getAddress().getCity());
        assertEquals(activatedDoctor1.getAddress().getNeighborhood(),activatedPatientsWithDeterminedAddress.getContent().get(0).getAddress().getNeighborhood());

    }

    @Test
    @DisplayName("Find getReferenceById ")
    public void findPatientWithGetReferenceById(){
        //given
        Doctor activatedDoctor1 = new Doctor(generationDoctorRandomWithSpecialityDefined(Specialty.CARDIOLOGY));
        activatedDoctor1 = doctorRepository.save(activatedDoctor1);
        Doctor doctor = doctorRepository.getReferenceById(activatedDoctor1.getId());
        assertNotNull(doctor);
        assertEquals(activatedDoctor1,doctor);
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




