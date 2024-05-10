package br.com.cepedi.Voll.api.security.service;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class TokenServiceTest {

    @Mock
    private DecodedJWT decodedJWT;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenService, "secret", "testSecret123");
    }


    @Test
    void generateToken_ValidUser_ReturnsToken() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");

        // Act
        String token = tokenService.generateToken(user);

        // Assert
        assertNotNull(token);
    }

    @Test
    void generateTokenRecoverPassword_ValidUser_ReturnsToken() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");

        // Act
        String token = tokenService.generateTokenRecoverPassword(user);

        // Assert
        assertNotNull(token);
    }

    @Test
    void isValidToken_ValidToken_ReturnsTrue() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");
        String token = tokenService.generateToken(user);

        // Act
        boolean isValid = tokenService.isValidToken(token);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void isValidToken_RevokedToken_ReturnsFalse() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");
        String token = tokenService.generateToken(user);
        tokenService.revokeToken(token);

        // Act
        boolean isValid = tokenService.isValidToken(token);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void getEmailByToken_ValidToken_ReturnsEmail() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");
        String token = tokenService.generateToken(user);

        // Act
        String email = tokenService.getEmailByToken(token);

        // Assert
        assertEquals("test@example.com", email);
    }

    @Test
    void getSubject_ValidToken_ReturnsSubject() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setEmail("test@example.com");
        String token = tokenService.generateToken(user);

        // Act
        String subject = tokenService.getSubject(token);

        // Assert
        assertEquals("testuser", subject);
    }

    @Test
    void getSubject_InvalidToken_ThrowsException() {
        // Arrange
        String invalidToken = "invalidToken";

        // Act & Assert
        assertThrows(JWTVerificationException.class, () -> {
            tokenService.getSubject(invalidToken);
        });
    }
}
