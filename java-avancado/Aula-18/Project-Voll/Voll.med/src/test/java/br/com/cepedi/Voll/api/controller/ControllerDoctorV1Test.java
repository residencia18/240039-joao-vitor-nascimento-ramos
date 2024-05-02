package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
@TestMethodOrder(MethodOrderer.Random.class)
public class ControllerDoctorV1Test {


    @MockBean
    DoctorService doctorService;
}
