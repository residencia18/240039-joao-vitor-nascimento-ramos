package com.example.atividades.atividade07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class UserTest {

    @Test
    @DisplayName("Test user creation with valid name and email")
    public void testUserCreation() {
        User user = new User("John Doe", "john.doe@example.com");
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
    }
}
