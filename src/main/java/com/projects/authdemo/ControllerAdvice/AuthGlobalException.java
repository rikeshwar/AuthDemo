package com.projects.authdemo.ControllerAdvice;

import com.projects.authdemo.Exception.InvalidCredentialException;
import com.projects.authdemo.Exception.UserAllReadyExistException;
import com.projects.authdemo.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthGlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e)
    {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAllReadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAllreadyExistException(UserAllReadyExistException e)
    {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.RESET_CONTENT);
    }
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException e)
    {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
    }

}
