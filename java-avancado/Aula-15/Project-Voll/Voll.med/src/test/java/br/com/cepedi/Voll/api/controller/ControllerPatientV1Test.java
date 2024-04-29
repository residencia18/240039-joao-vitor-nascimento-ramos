package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.services.patient.PatientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    private List<DataRegisterPatient> dataRegisterPatients = new ArrayList<>();
    ;

    @BeforeEach
    void setup() {

        for (int i = 0; i < 15; i++) {
            dataRegisterPatients.add(generateRandomPatientData());
        }
    }

    @Test
    @DisplayName("JUnit test for Given Person Object when Create Person then Return Saved Person")
    @WithMockUser(username = "user", roles = {"USER"})
    void createPatientForRequisitionV1Authentication() throws Exception {
        DataDetailsPatient expectedDetails = new DataDetailsPatient(new Patient(dataRegisterPatients.get(0)));
        when(patientService.register(any())).thenReturn(new DataDetailsPatient(new Patient(dataRegisterPatients.get(0))));
//
        // Requisição HTTP
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dataRegisterPatients.get(0))));

        response.andDo(print()).andExpect(status().isCreated());
        ;
        String exceptedJson = response.andReturn().getResponse().getContentAsString();


        assertThat(expectedDetails.equals(exceptedJson));

    }

    @Test
    @DisplayName("JUnit test for Given not authentication for return 403")
    void createPatientForRequisitionV1WithoutAuthentication() throws Exception {
        DataDetailsPatient expectedDetails = new DataDetailsPatient(new Patient(dataRegisterPatients.get(0)));
        when(patientService.register(any())).thenReturn(new DataDetailsPatient(new Patient(dataRegisterPatients.get(0))));
//
        // Requisição HTTP
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dataRegisterPatients.get(0))));

        response.andDo(print()).andExpect(status().isForbidden());
        ;
        String exceptedJson = response.andReturn().getResponse().getContentAsString();

    }


    @Test
    @DisplayName("Test for given get all patients activated")
    @WithMockUser
    void getAllPatientsActivated() throws Exception {
        List<DataDetailsPatient> expectedDetails = dataRegisterPatients.stream()
                .map(p -> new DataDetailsPatient(new Patient(p))).toList();
        Page<DataDetailsPatient> simulatedPage = new PageImpl<>(expectedDetails);

        when(patientService.list(any(Pageable.class))).thenReturn(simulatedPage);

        ResultActions response = mockMvc.perform(get("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(simulatedPage.getContent())));

        response.andExpect(status().isOk());


        String jsonResponse = response.andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedJson = objectMapper.valueToTree(expectedDetails);
        JsonNode actualJson = objectMapper.readTree(jsonResponse);


        assertEquals(simulatedPage.getTotalElements(),actualJson.get("totalElements").asLong());
        assertEquals(expectedJson, actualJson.get("content"));
    }

    @Test
    @DisplayName("Test for fiver get patient for id")
    @WithMockUser
    void getPatientForId() throws Exception {
        DataDetailsPatient excepted = new DataDetailsPatient(new Patient(dataRegisterPatients.get(0)));
        when(patientService.details(anyLong())).thenReturn(excepted);

        ResultActions response = mockMvc.perform(get("/v1/patients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dataRegisterPatients.get(0))));

        response.andExpect(status().isOk());

        String jsonResponse = response.andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        DataDetailsPatient actualDetails = objectMapper.readValue(jsonResponse, DataDetailsPatient.class);

        assertEquals(excepted, actualDetails);

    }

    @Test
    @DisplayName("Test for giver disabled patient for id")
    @WithMockUser
    void disabledPatientForId() throws Exception {
        ResultActions response = mockMvc.perform(delete("/v1/patients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dataRegisterPatients.get(0))));

        response.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test updating patient")
    @WithMockUser
    void testUpdatePatient() throws Exception {
        // Criar um paciente inicial no banco de dados
        DataRegisterPatient initialPatient = generateRandomPatientData();
        Patient initialPatientEntity = new Patient(initialPatient);
        initialPatientEntity.setId(1L);

        DataUpdatePatient updatedPatientData = generateRandomUpdate();


        Patient forUpdate = new Patient(initialPatient);
        forUpdate.setId(1L);

        forUpdate.updateData(updatedPatientData);

        DataDetailsPatient detailsForUpdate = new DataDetailsPatient(forUpdate);


        when(patientService.update(any(Long.class), any(DataUpdatePatient.class)))
                .thenReturn(new DataDetailsPatient(forUpdate));

        ResultActions response = mockMvc.perform(put("/v1/patients/{id}", initialPatientEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(detailsForUpdate)));

        response.andExpect(status().isOk());
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

    private DataUpdatePatient generateRandomUpdate() {
        return new DataUpdatePatient(
                faker.name().fullName(),
                faker.phoneNumber().cellPhone(),
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
