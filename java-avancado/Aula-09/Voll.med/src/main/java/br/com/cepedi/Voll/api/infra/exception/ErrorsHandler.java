package br.com.cepedi.Voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> Error404(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400(MethodArgumentNotValidException exception){
        List<FieldError> errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataExceptionValidate::new).toList());
    }


    private record DataExceptionValidate(String value , String message){

        public DataExceptionValidate(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
