package com.openclassrooms.paymybuddy.exceptions;

public class PasswordIncorrectException extends RuntimeException{

    public PasswordIncorrectException(String message) {
        super(message);
    }

}
