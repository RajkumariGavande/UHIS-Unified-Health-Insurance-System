package com.admin.app.exception;

public class PlanAlreadyExistsException extends RuntimeException {

    public PlanAlreadyExistsException(String planName) {
        super("Plan already exists with name: " + planName);
    }
}