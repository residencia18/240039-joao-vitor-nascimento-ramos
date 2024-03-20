package com.biblioteca.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
	
    private List<String> errors = new ArrayList<>();

    public ErrorResponse() {}

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
