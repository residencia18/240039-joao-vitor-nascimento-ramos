package br.com.cepedi.Voll.api.controller;


import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
@TestMethodOrder(MethodOrderer.Random.class)
public class ControllerAppointmentV1Test {

    @MockBean
    AppointmentService appointmentService;

}
