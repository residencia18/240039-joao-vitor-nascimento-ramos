package br.com.cepedi.Voll.api.services.appointment;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.appointment.enums.ReasonCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.details.DataDetailsAppointment;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import br.com.cepedi.Voll.api.services.appointment.validations.register.ValidationAcheduleAppointment;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import br.com.cepedi.Voll.api.services.patient.PatientService;
import com.github.javafaker.Faker;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class TestServiceAppointment {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;


    @Mock
    private List<ValidationAcheduleAppointment> validators;

    @InjectMocks
    private AppointmentService appointmentService;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    private List<DataRegisterPatient> dataRegisterPatients = new ArrayList<>();

    private List<DataRegisterDoctor> dataRegisterDoctors = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
        doctorRepository.deleteAll();
        appointmentRepository.deleteAll();

        for (int i = 0; i < 3; i++) {
            dataRegisterDoctors.add(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));
        }

        for (int i = 0; i < 3; i++) {
            dataRegisterPatients.add(generateRandomPatientData());
        }

    }

    @Test
    @DisplayName("Register new appointment")
    public void shouldRegisterNewAppointment() {
        // Given

        // Save patient
        Doctor doctor = new Doctor(dataRegisterDoctors.get(0));
        Patient patient = new Patient(dataRegisterPatients.get(0));
        doctor.setId(1L);
        patient.setId(1L);
        when(patientRepository.getReferenceById(any())).thenReturn(patient);
        when(doctorRepository.getReferenceById(any())).thenReturn(doctor);


        // Create an appointment for the next Monday
        LocalDateTime nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        DataRegisterAppointment data = new DataRegisterAppointment(doctor.getId(), patient.getId(), nextMonday, null);

        // When
        DataDetailsAppointment registeredAppointment = appointmentService.register(data);

        // Then
        assertNotNull(registeredAppointment);
    }

    @Test
    @DisplayName("Register new appointment")
    public void shouldRegisterNewAppointmentTheyDoctor() {
        // Given

        // Save patient
        Doctor doctor = new Doctor(dataRegisterDoctors.get(0));
        Patient patient = new Patient(dataRegisterPatients.get(0));
        doctor.setId(1L);
        patient.setId(1L);
        when(patientRepository.getReferenceById(any())).thenReturn(patient);
        when(doctorRepository.chooseDoctorRandomFreethisDate(any(),any())).thenReturn(doctor);

        // Create an appointment for the next Monday
        LocalDateTime nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        DataRegisterAppointment data = new DataRegisterAppointment(null, patient.getId(), nextMonday, Specialty.DERMATOLOGY);

        // When
        DataDetailsAppointment registeredAppointment = appointmentService.register(data);
        assertEquals(registeredAppointment.doctorId(),doctor.getId());

        // Then
        assertNotNull(registeredAppointment);
    }



    @Test
    @DisplayName("Cancel appointment")
    public void shouldCancelAppointment() {
        // Given
        Long appointmentId = 1L;

        Appointment appointment = new Appointment();
        when(appointmentRepository.existsById(anyLong())).thenReturn(true);
        when(appointmentRepository.getReferenceById(anyLong())).thenReturn(appointment);

        String reason = "Reason for cancellation";
        DataCancelAppointment data = new DataCancelAppointment(appointmentId, ReasonCancelAppointment.DOCTOR_CANCEL);


        // When
        appointmentService.cancel(data);

        // Then
        assertEquals(appointment.getReasonCancel(), ReasonCancelAppointment.DOCTOR_CANCEL);
    }

    @Test
    @DisplayName("Cancel appointment - Appointment not found")
    public void shouldThrowExceptionWhenCancellingNonexistentAppointment() {
        // Given
        Long appointmentId = 1L;
        String reason = "Reason for cancellation";
        DataCancelAppointment data = new DataCancelAppointment(appointmentId, ReasonCancelAppointment.DOCTOR_CANCEL);
        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);

        String expectedException = "The provided appointment ID does not exist!";

        // When/Then
        Exception exception = assertThrows(ValidationException.class, () -> appointmentService.cancel(data));
        assertEquals(expectedException,exception.getMessage());
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


}
