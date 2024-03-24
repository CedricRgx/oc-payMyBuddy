package com.openclassrooms.paymybuddy.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
