package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class UserManagerTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test fetchUserInfo method in UserManager with existing user")
    public void testFetchUserInfoExistingUser() {
        UserService userService = mock(UserService.class);

        int userId = faker.number().randomDigit();

        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        User fakeUser = new User(name, email);

        when(userService.getUserInfo(userId)).thenReturn(fakeUser);

        UserManager userManager = new UserManager(userService);

        assertEquals(fakeUser, userManager.fetchUserInfo(userId));
    }

    @Test
    @DisplayName("Test fetchUserInfo method in UserManager with non-existing user")
    public void testFetchUserInfoNonExistingUser() {
        UserService userService = mock(UserService.class);

        int userId = faker.number().randomDigit();

        when(userService.getUserInfo(userId)).thenReturn(null);

        UserManager userManager = new UserManager(userService);

        assertThrows(RuntimeException.class, () -> userManager.fetchUserInfo(userId));
    }
}
