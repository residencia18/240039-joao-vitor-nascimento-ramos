package br.com.cepedi.Voll.api.security.integration;

import br.com.cepedi.Voll.api.config.TestConfig;
import br.com.cepedi.Voll.api.security.model.records.input.DataAuth;
import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import br.com.cepedi.Voll.api.security.model.records.input.DataRequestResetPassword;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TestIntegrationRecoveryPassword {

    private static RequestSpecification specification;

    private static RequestSpecification specificationLogin;

    private static RequestSpecification specificationRegisterUser;

    private static RequestSpecification specificationRequestRecoveryPassword;

    private static ObjectMapper objectMapper;

    private static String token;

    private static String emailUser;

    private static String newPasswordUser;

    @BeforeAll
    static void setup(){


        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


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

        specificationRequestRecoveryPassword = new RequestSpecBuilder()
                .setBasePath("reset-password/request")
                .setPort(TestConfig.SERVE_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }

    @Test
    @DisplayName("Admin do login")
    @Order(1)
    void loginAdmin(){

        DataAuth data = new DataAuth("admin", "123456");

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
    @DisplayName("Test create user")
    @Order(2)
    void registerUser(){
        DataRegisterUser data = new DataRegisterUser("loginTest", "jvitorsb98@outlook.com" , "loginTest","Teste123*");

        String details = given().spec(specificationRegisterUser)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + token)
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
    @Order(3)
    void login(){
        DataAuth data = new DataAuth("loginTest", "Teste123*");

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
    @DisplayName("Test requisition recovery password")
    @Order(4)
    void requestRevoceryPassword(){
        DataRequestResetPassword data = new DataRequestResetPassword("jvitorsb98@outlook.com");

        String sendEmail = given().spec(specificationRequestRecoveryPassword)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(data)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().toString();

        System.out.println(sendEmail);
    }

}
