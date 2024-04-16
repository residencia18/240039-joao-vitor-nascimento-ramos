package br.com.cepedi.Voll.api.services;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.output.DataDetailsAppointment;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.model.records.doctor.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.output.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import br.com.cepedi.Voll.api.services.appointment.validations.register.ValidationAcheduleAppointment;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import br.com.cepedi.Voll.api.services.patient.PatientService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;



@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "br.com.cepedi.Voll.api.services")
public class TestServiceAppointment {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorService doctorService;

    @Mock
    private PatientService patientService;

    @Mock
    private List<ValidationAcheduleAppointment> validators;

    @InjectMocks
    private AppointmentService appointmentService;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();


    @BeforeEach
    public void setUp() {
        appointmentRepository.deleteAll();
    }

    @Test
    @Disabled("Test in construction")
    @DisplayName("Register new appointment")
    public void shouldRegisterNewAppointment() {
        // Given

        // Save patient
        DataDetailsPatient detailsPatient = patientService.register(generateRandomPatientData());
        DataDetailsDoctor detailsDoctor = doctorService.register(generationDoctorRandomWithSpecialityDefined(Specialty.DERMATOLOGY));


        // Create an appointment for the next Monday
        LocalDateTime nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        DataRegisterAppointment data = new DataRegisterAppointment(detailsDoctor.id(), detailsPatient.id(), nextMonday, null);

        // When
        DataDetailsAppointment registeredAppointment = appointmentService.register(data);

        // Then
        assertNotNull(registeredAppointment);
    }

//
//    @Test
//    @DisplayName("Cancel appointment")
//    public void shouldCancelAppointment() {
//        // Given
//        Long appointmentId = 1L;
//        String reason = "Reason for cancellation";
//        DataCancelAppointment data = new DataCancelAppointment(appointmentId, reason);
//        Appointment appointment = new Appointment();
//        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
//        when(appointmentRepository.getReferenceById(appointmentId)).thenReturn(appointment);
//
//        // When
//        appointmentService.cancel(data);
//
//        // Then
//        assertTrue(appointment.isCancelled());
//        assertEquals(reason, appointment.getCancelReason());
//    }
//
//    @Test
//    @DisplayName("Cancel appointment - Appointment not found")
//    public void shouldThrowExceptionWhenCancellingNonexistentAppointment() {
//        // Given
//        Long appointmentId = 1L;
//        String reason = "Reason for cancellation";
//        DataCancelAppointment data = new DataCancelAppointment(appointmentId, reason);
//        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);
//
//        // When/Then
//        assertThrows(ValidationException.class, () -> appointmentService.cancel(data));
//    }


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
