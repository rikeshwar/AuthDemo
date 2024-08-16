package com.projects.authdemo.Exception;

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message)
    {
        super(message);
    }
}
