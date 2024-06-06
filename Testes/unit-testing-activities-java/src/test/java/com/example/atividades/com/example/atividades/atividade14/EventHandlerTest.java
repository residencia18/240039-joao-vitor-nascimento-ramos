package com.example.atividades.atividade14;

import static org.mockito.Mockito.*;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class EventHandlerTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test handleEvent method")
    public void testHandleEvent() {
        EmailService emailService = mock(EmailService.class);
        EventHandler eventHandler = new EventHandler(emailService);

        String event = faker.lorem().sentence();

        eventHandler.handleEvent(event);

        verify(emailService).sendEmail(
                eq("test@example.com"),
                eq("Event Occurred"),
                eq(event)
        );
    }

}
