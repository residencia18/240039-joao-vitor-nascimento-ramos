package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class UserServiceTest {

    @Test
    @DisplayName("Test getUserInfo method in UserService")
    public void testGetUserInfo() {
        UserService userService = new UserService();

        assertNull(userService.getUserInfo(123));
        assertNull(userService.getUserInfo(456));
        assertNull(userService.getUserInfo(789));
    }
}
