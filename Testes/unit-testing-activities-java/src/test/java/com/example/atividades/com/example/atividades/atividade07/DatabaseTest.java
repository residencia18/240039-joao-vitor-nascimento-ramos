package com.example.atividades.atividade07;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class DatabaseTest {

    @Test
    @DisplayName("Test saveUser method in Database")
    public void testSaveUser() {
        Database db = new Database();
        User user = new User("Jane Doe", "jane.doe@example.com");

        db.saveUser(user);
    }
}
