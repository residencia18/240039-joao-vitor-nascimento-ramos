package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.controller.V1.PatientControllerV1;
import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.security.service.TokenService;
import br.com.cepedi.Voll.api.services.patient.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerPatientV1Test {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PatientService patientService;



    private DataRegisterPatient dataRegisterPatient;

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    @Test
    @DisplayName("JUnit test for Given Person Object when Create Person then Return Saved Person")
    @WithMockUser(username = "user", roles = {"USER"})
    void testando() throws Exception {
        DataRegisterPatient data = generateRandomPatientData();
        DataDetailsPatient expectedDetails = new DataDetailsPatient(new Patient(data));
        when(patientService.register(any())).thenReturn(new DataDetailsPatient(new Patient(data)));
//
        // Requisição HTTP
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(data)));

        response.andDo(print()).andExpect(status().isCreated());
        ;
        String exceptedJson = response.andReturn().getResponse().getContentAsString();


        assertThat(expectedDetails.equals(exceptedJson));

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
