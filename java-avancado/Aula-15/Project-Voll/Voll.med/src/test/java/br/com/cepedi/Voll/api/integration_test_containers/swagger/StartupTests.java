package br.com.cepedi.Voll.api.integration_test_containers.swagger;

import br.com.cepedi.Voll.api.config.TestConfig;
import br.com.cepedi.Voll.api.integration_test_containers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StartupTests extends AbstractIntegrationTest {

    @Test
    @DisplayName("Test show display ui swagger ui")
    void testShouldDisplaySwaggerUiPage(){
        //given and when
        var content = given().basePath("/swagger-ui/index.html").port(TestConfig.SERVE_PORT)
                .when().get().then().statusCode(200).extract().body().asString();
        //then
        assertTrue(content.contains("Swagger UI"));

    }

}
