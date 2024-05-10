package br.com.cepedi.Voll.api.security.controller;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.input.DataRequestResetPassword;
import br.com.cepedi.Voll.api.security.model.records.input.DataResetPassword;
import br.com.cepedi.Voll.api.security.service.EmailService;
import br.com.cepedi.Voll.api.security.service.TokenService;
import br.com.cepedi.Voll.api.security.service.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasswordRecoveryControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private PasswordRecoveryController passwordRecoveryController;

    @Test
    @DisplayName("Test resetPasswordRequest with valid email")
    public void testResetPasswordRequestValidEmail() throws MessagingException {
        // Given
        DataRequestResetPassword dataRequestResetPassword = new DataRequestResetPassword("test@example.com");
        User user = new User();
        when(userService.getUserActivatedByEmail(dataRequestResetPassword.email())).thenReturn(user);
        when(tokenService.generateTokenRecoverPassword(user)).thenReturn("token");

        // When
        ResponseEntity<String> response = passwordRecoveryController.resetPasswordRequest(dataRequestResetPassword);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("A password reset email has been sent to"));
    }

    @Test
    @DisplayName("Test resetPasswordRequest with invalid email")
    public void testResetPasswordRequestInvalidEmail() {
        // Given
        DataRequestResetPassword dataRequestResetPassword = new DataRequestResetPassword("invalid@example.com");
        when(userService.getUserActivatedByEmail(dataRequestResetPassword.email())).thenReturn(null);

        // When
        ResponseEntity<String> response = passwordRecoveryController.resetPasswordRequest(dataRequestResetPassword);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("E-mail not found"));
    }

    @Test
    @DisplayName("Test resetPasswordRequest with email where emailService throws exception")
    public void testResetPasswordRequestEmailServiceException() throws MessagingException {
        // Given
        DataRequestResetPassword dataRequestResetPassword = new DataRequestResetPassword("exception@example.com");
        User user = new User();
        when(userService.getUserActivatedByEmail(dataRequestResetPassword.email())).thenReturn(user);
        when(tokenService.generateTokenRecoverPassword(user)).thenReturn("token");

        // Aqui está a correção: passar os argumentos corretos para o método de stubbing
        doThrow(MessagingException.class).when(emailService).sendResetPasswordEmail(eq(user.getName()), eq(dataRequestResetPassword.email()), eq("token"));

        // When
        ResponseEntity<String> response = passwordRecoveryController.resetPasswordRequest(dataRequestResetPassword);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Failed to send email"));
    }


    @Test
    @DisplayName("Test resetPassword with valid token")
    public void testResetPasswordValidToken() {
        // Given
        DataResetPassword dataResetPassword = new DataResetPassword("validToken", "newPassword");
        String email = "test@example.com";
        when(tokenService.isValidToken(dataResetPassword.token())).thenReturn(true);
        when(tokenService.getEmailByToken(dataResetPassword.token())).thenReturn(email);

        // When
        ResponseEntity<String> response = passwordRecoveryController.resetPassword(dataResetPassword);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Password updated successfully"));
        verify(userService, times(1)).updatePassword(eq(email), anyString());
        verify(tokenService, times(1)).revokeToken(dataResetPassword.token());
    }

    @Test
    @DisplayName("Test resetPassword with invalid token")
    public void testResetPasswordInvalidToken() {
        // Given
        DataResetPassword dataResetPassword = new DataResetPassword("invalidToken", "newPassword");
        when(tokenService.isValidToken(dataResetPassword.token())).thenReturn(false);

        // When
        ResponseEntity<String> response = passwordRecoveryController.resetPassword(dataResetPassword);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Invalid or expired token"));
        verify(userService, never()).updatePassword(anyString(), anyString());
        verify(tokenService, never()).revokeToken(anyString());
    }
}

