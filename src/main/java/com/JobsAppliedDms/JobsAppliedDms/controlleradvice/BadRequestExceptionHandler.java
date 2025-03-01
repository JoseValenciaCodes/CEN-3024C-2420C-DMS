package com.JobsAppliedDms.JobsAppliedDms.controlleradvice;

/*
* Bad Request Exception Handler
* Whenever a 400 related on exception is triggered
* Return a 400 response with the message attached to it
* */

import com.JobsAppliedDms.JobsAppliedDms.exception.*;
import com.JobsAppliedDms.JobsAppliedDms.payload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BadRequestExceptionHandler
{
    // Throw DB validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        // Create the JSON payload to return as error view
        Map<String, String> errors = new HashMap<String, String>();

        // Grab the error message of each error
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
        {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // Return the errors
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Throw Exception when trying to register an existing user
    @ExceptionHandler(UserAlreadyRegistered.class)
    public ResponseEntity<ErrorPayload> handleUserAlreadyRegisteredExceptions(UserAlreadyRegistered ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPayload(ex.getMessage()));
    }

    // Throw Exception when trying to find a non-existing user
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorPayload> handleUserNotFoundExceptions(UserNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorPayload(ex.getMessage()));
    }

    @ExceptionHandler(CompanyNotFound.class)
    public ResponseEntity<ErrorPayload> handleCompanyNotFoundExceptions(CompanyNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorPayload(ex.getMessage()));
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<ErrorPayload> handleCategoryNotFoundExceptions(CategoryNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPayload(ex.getMessage()));
    }

    @ExceptionHandler(JobNotFound.class)
    public ResponseEntity<ErrorPayload> handleJobNotFoundExceptions(JobNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPayload(ex.getMessage()));
    }

    @ExceptionHandler(ApplicationNotFound.class)
    public ResponseEntity<ErrorPayload> handleApplicationNotFoundExceptions(ApplicationNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPayload(ex.getMessage()));
    }

    // Throw Exception when trying to log in with incorrect credentials
    @ExceptionHandler(IncorrectCredentials.class)
    public ResponseEntity<ErrorPayload> handleIncorrectCredentialsExceptions(IncorrectCredentials ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPayload(ex.getMessage()));
    }
}
