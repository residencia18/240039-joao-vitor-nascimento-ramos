package com.example.atividades.atividade07;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.Random.class)
public class UserServiceTest {

    @Test
    @DisplayName("Test saving a valid user")
    public void testSaveValidUser() {
        Database mockDb = mock(Database.class);
        UserService userService = new UserService(mockDb);

        User validUser = new User("John Doe", "john.doe@example.com");
        userService.saveUser(validUser);

        verify(mockDb).saveUser(validUser);
    }

    @Test
    @DisplayName("Test saving a user with null name")
    public void testSaveUserWithNullName() {
        Database mockDb = mock(Database.class);
        UserService userService = new UserService(mockDb);

        User invalidUser = new User(null, "john.doe@example.com");

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(invalidUser));
        verify(mockDb, never()).saveUser(invalidUser);
    }

    @Test
    @DisplayName("Test saving a user with empty name")
    public void testSaveUserWithEmptyName() {
        Database mockDb = mock(Database.class);
        UserService userService = new UserService(mockDb);

        User invalidUser = new User("", "john.doe@example.com");

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(invalidUser));
        verify(mockDb, never()).saveUser(invalidUser);
    }

    @Test
    @DisplayName("Test saving a user with null email")
    public void testSaveUserWithNullEmail() {
        Database mockDb = mock(Database.class);
        UserService userService = new UserService(mockDb);

        User invalidUser = new User("John Doe", null);

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(invalidUser));
        verify(mockDb, never()).saveUser(invalidUser);
    }

    @Test
    @DisplayName("Test saving a user with empty email")
    public void testSaveUserWithEmptyEmail() {
        Database mockDb = mock(Database.class);
        UserService userService = new UserService(mockDb);

        User invalidUser = new User("John Doe", "");

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(invalidUser));
        verify(mockDb, never()).saveUser(invalidUser);
    }
}
