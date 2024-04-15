//package br.com.cepedi.Voll.api.domain.appointments.register;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.temporal.TemporalAdjusters;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//@ComponentScan(basePackages = "br.com.cepedi.Voll.api.service.doctor")

//public class TestChooseDoctorRandom {

//    @Autowired
//    private DoctorRepository repository;
//
//    @Autowired
//    private RegisterDataTest registerDataTest;
//
//
//    @Test
//    @DisplayName("Should return a doctor when they are available on the given date")
//    void chooseRandomAvailableDoctorOnDateScenario1() {
//        //given or arrange
//        var nextMondayAt10 = LocalDate.now()
//                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//                .atTime(10, 0);
//        Doctor doctor = registerDataTest.registerDoctor( Specialty.CARDIOLOGY);
//        //when or act
//        Doctor availableDoctor = repository.chooseDoctorRandomFreethisDate(Specialty.CARDIOLOGY, nextMondayAt10);
//        //then or assert
//        assertThat(availableDoctor).isEqualTo(doctor);
//    }
//
//    @Test
//    @DisplayName("It should return null when the only registered doctor is not available on the date")
//    void chooseRandomAvailableDoctorOnDateScenario2() {
//        //given or arrange
//        var nextMondayAt10 = LocalDate.now()
//                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//                .atTime(10, 0);
//        var doctor = registerDataTest.registerDoctor( Specialty.CARDIOLOGY);
//        var patient = registerDataTest.registerPatient();
//        registerDataTest.registerAppointment(doctor,patient,nextMondayAt10);
//        //when or act
//        var availableDoctor = repository.chooseDoctorRandomFreethisDate(Specialty.CARDIOLOGY, nextMondayAt10);
//        //then or assert
//        assertThat(availableDoctor).isNull();
//    }
//
//
//
//
//






