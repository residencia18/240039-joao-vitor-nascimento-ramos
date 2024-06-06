package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Random.class)
public class UserTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test user creation with valid name and email")
    public void testUserCreation() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();

        User user = new User(name, email);

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }
}
