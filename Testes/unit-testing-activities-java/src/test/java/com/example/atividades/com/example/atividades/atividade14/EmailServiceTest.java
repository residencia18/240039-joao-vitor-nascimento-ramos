package com.example.atividades.atividade14;

import static org.mockito.Mockito.*;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class EmailServiceTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test sendEmail method")
    public void testSendEmail() {
        EmailService emailService = spy(EmailService.class);

        String recipient = faker.internet().emailAddress();
        String subject = faker.lorem().sentence();
        String body = faker.lorem().paragraph();

        emailService.sendEmail(recipient, subject, body);

        verify(emailService).sendEmail(recipient, subject, body);
    }
}
