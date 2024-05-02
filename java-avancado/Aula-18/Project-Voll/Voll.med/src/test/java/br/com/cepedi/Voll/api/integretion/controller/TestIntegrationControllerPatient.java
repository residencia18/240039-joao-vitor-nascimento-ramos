package br.com.cepedi.Voll.api.integretion.controller;

import br.com.cepedi.Voll.api.config.TestConfig;
import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.integretion.containers.AbstractIntegrationTest;
import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.details.DataDetailsPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.security.model.records.input.DataAuth;

import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TestIntegrationControllerPatient extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private static RequestSpecification specificationLogin;

    private static RequestSpecification specificationRegisterUser;

    private static ObjectMapper objectMapper;

    private static List<DataRegisterPatient> patients;

    private static final Faker faker = new Faker();

    private static PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();

    private static String token;

    private static Long idPatient;

    private static DataUpdatePatient dataUpdatePatient;

    private static DataDetailsPatient dataDetailsPatientAfterUpdate;


    @BeforeAll
    static void setup(){
        patients = new ArrayList<>();


        for(int i = 0 ; i < 15 ; i++){
            patients.add(generationPatientRandom());
        }

        dataUpdatePatient = generateRandomUpdate();

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        specification = new RequestSpecBuilder()
                .setBasePath("v1/patients")
                .setPort(TestConfig.SERVE_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        specificationLogin = new RequestSpecBuilder()
                .setBasePath("login")
                .setPort(TestConfig.SERVE_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        specificationRegisterUser = new RequestSpecBuilder()
                .setBasePath("register")
                .setPort(TestConfig.SERVE_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }

    @Test
    @DisplayName("Test create user")
    @Order(1)
    void registerUser(){
        DataRegisterUser data = new DataRegisterUser("teste", "teste@teste.com" , "teste","Teste123*");

        String details = given().spec(specificationRegisterUser)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(data)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertNotNull(details);


    }

    @Test
    @DisplayName("Test requisition login")
    @Order(2)
    void login(){
        DataAuth data = new DataAuth("teste", "Teste123*");

        String valueToken = given().spec(specificationLogin)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(data)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("token");

            token = valueToken;

    }


    @Test
    @DisplayName("Test requisition post for create patient")
    @Order(3)
    void integrationTestGivenPatient_when_CreatePatient_then_returnDataDetails() throws JsonProcessingException {
        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .body(patients.get(0))
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        DataDetailsPatient dataDetailsPatient = objectMapper.readValue(content, DataDetailsPatient.class);

        assertNotNull(dataDetailsPatient);
        assertEquals(patients.get(0).name(),dataDetailsPatient.name());
        assertEquals(patients.get(0).cpf(),dataDetailsPatient.cpf());
        assertTrue(dataDetailsPatient.id() > 0, "Id not more zero" );

        idPatient = dataDetailsPatient.id();



    }


    @Test
    @DisplayName("Test requisition update by id")
    @Order(4)
    void integrationTestGivenPatient_when_UpdatePatient_then_returnDataDetails() throws JsonProcessingException {

        String updateEndpoint = "/" + idPatient;

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .body(dataUpdatePatient)
                .when()
                .put(updateEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DataDetailsPatient dataDetailsPatient = objectMapper.readValue(content, DataDetailsPatient.class);

        dataDetailsPatientAfterUpdate = dataDetailsPatient;


        assertNotNull(dataDetailsPatient);
        assertEquals(dataUpdatePatient.name(), dataDetailsPatient.name());
        assertEquals(dataUpdatePatient.phoneNumber(), dataDetailsPatient.phoneNumber());
        assertEquals(idPatient, dataDetailsPatient.id());
    }

    @Test
    @DisplayName("Test requisition get patient by id")
    @Order(5)
    void integrationTestGivenPatient_when_ReadPatient_then_returnDataDetails() throws JsonProcessingException {
        String readEndpoint = "/" + idPatient;

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(readEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DataDetailsPatient dataDetailsPatient = objectMapper.readValue(content, DataDetailsPatient.class);

        assertNotNull(dataDetailsPatient);
        assertEquals(dataDetailsPatientAfterUpdate.name(), dataDetailsPatient.name());
        assertEquals(dataDetailsPatientAfterUpdate.phoneNumber(), dataDetailsPatient.phoneNumber());
        assertEquals(idPatient, dataDetailsPatient.id());
    }

    @Test
    @DisplayName("Test requisition disabled patient by id")
    @Order(6)
    void integrationTestGivenPatient_when_DisabledPatient_then_returnDataDetails() throws JsonProcessingException {
        String readEndpoint = "/" + idPatient;


        //disabled patient
        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(readEndpoint)
                .then()
                .statusCode(204)
                .extract()
                .body()
                .asString();

        var contentPatientDisabled = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(readEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        DataDetailsPatient dataDetailsPatientDisabled = objectMapper.readValue(contentPatientDisabled, DataDetailsPatient.class);
        assertFalse(dataDetailsPatientDisabled.activated());

    }

    @Test
    @DisplayName("Test requisition get all patient by id")
    @Order(7)
    void integrationTestGivenPatient_when_ReadAllPatients_then_returnDataDetails() throws JsonProcessingException {

        for(int i = 1 ; i < 15 ; i++){
            var content = given().spec(specification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(patients.get(i))
                    .when()
                    .post()
                    .then()
                    .statusCode(201)
                    .extract()
                    .body()
                    .asString();

            DataDetailsPatient dataDetailsPatient = objectMapper.readValue(content, DataDetailsPatient.class);

            assertNotNull(dataDetailsPatient);
            assertEquals(patients.get(i).name(),dataDetailsPatient.name());
            assertEquals(patients.get(i).cpf(),dataDetailsPatient.cpf());
            assertTrue(dataDetailsPatient.id() > 0, "Id not more zero" );
        }

        Response response = RestAssured.given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String content = response.getBody().asString();

        int totalElements = JsonPath.from(content).getInt("totalElements");
        assertEquals(totalElements ,14);

        List<Map<String, Object>> contentList = JsonPath.from(content).getList("content");
        assertNotNull(contentList);

        // Iterar sobre os elementos do array "content"
        for (Map<String, Object> element : contentList) {
            assertNotNull(element.get("id"));
            assertNotNull(element.get("name"));
            assertNotNull(element.get("email"));
        }

        // Extrair o valor de "pageNumber" e verificar se não é nulo
        int pageNumber = JsonPath.from(content).getInt("totalPages");
        assertEquals(pageNumber,2);
    }

    private static DataUpdatePatient generateRandomUpdate() {
        return new DataUpdatePatient(
                faker.name().fullName(),
                faker.phoneNumber().cellPhone(),
                createAddressData()
        );
    }

    private static DataRegisterPatient generationPatientRandom() {
        return new DataRegisterPatient(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                generationCPF(),
                createAddressData()
        );
    }

    private static String generationCPF() {
        return cpfGenerator.getValidFormattedCpf(faker);
    }


    private static DataRegisterAddress createAddressData() {
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
