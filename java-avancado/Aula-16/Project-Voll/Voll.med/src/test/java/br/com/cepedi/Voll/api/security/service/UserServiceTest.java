package br.com.cepedi.Voll.api.security.service;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test getUserActivatedByEmail when user exists")
    public void testGetUserActivatedByEmailWhenUserExists() {
        // Dado
        String email = "testuser@example.com";
        User expectedUser = new User();
        when(userRepository.findUserByEmail(email)).thenReturn(expectedUser);

        // Quando
        User user = userService.getUserActivatedByEmail(email);

        // Então
        assertNotNull(user);
        assertEquals(expectedUser, user);
    }



    @Test
    @DisplayName("Test updatePassword when user exists")
    public void testUpdatePasswordWhenUserExists() {
        // Dado
        String email = "testuser@example.com";
        String newPassword = "newPassword123*";
        User existingUser = new User();
        when(userRepository.findUserByEmail(email)).thenReturn(existingUser);

        // Simulando o comportamento do passwordEncoder.encode
        String encodedNewPassword = "encodedNewPassword";
        when(passwordEncoder.encode(newPassword)).thenReturn(encodedNewPassword);

        // Quando
        userService.updatePassword(email, newPassword);

        // Então
        assertEquals(encodedNewPassword, existingUser.getPassword());
        verify(userRepository, times(1)).save(existingUser);
    }


    @Test
    @DisplayName("Test updatePassword when user does not exist")
    public void testUpdatePasswordWhenUserDoesNotExist() {
        // Dado
        String nonExistentEmail = "nonexistent@example.com";
        String newPassword = "newPassword123";
        when(userRepository.findUserByEmail(nonExistentEmail)).thenReturn(null);

        // Quando/Então
        assertThrows(RuntimeException.class, () -> userService.updatePassword(nonExistentEmail, newPassword));
        verify(userRepository, never()).save(any());
    }
}
