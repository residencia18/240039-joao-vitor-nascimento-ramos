package br.com.cepedi.Voll.api.infra.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ErrorsHandlerTest {

    private final ErrorsHandler errorsHandler = new ErrorsHandler();

    @Test
    @DisplayName("Test handling EntityNotFoundException")
    public void testHandleEntityNotFoundException() {
        ResponseEntity<Object> responseEntity = errorsHandler.Error404();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }





    @Test
    @DisplayName("Test handling BadCredentialsException")
    public void testHandleBadCredentialsException() {
        ResponseEntity<Object> responseEntity = errorsHandler.handleBadCredentialsError();

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }

    @Test
    @DisplayName("Test handling AccessDeniedException")
    public void testHandleAccessDeniedException() {
        ResponseEntity<Object> responseEntity = errorsHandler.handleAccessDeniedError();

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertEquals("Access denied", responseEntity.getBody());
    }

    @Test
    @DisplayName("Test handling any other Exception")
    public void testHandleOtherException() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<Object> responseEntity = errorsHandler.handle500Error(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error: Test Exception", responseEntity.getBody());
    }
}