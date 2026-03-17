package com.admin.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),404,LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateResourceException ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),409,LocalDateTime.now()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),400,LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobal(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),500,LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}