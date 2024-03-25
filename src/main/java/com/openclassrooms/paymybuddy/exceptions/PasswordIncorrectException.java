package com.openclassrooms.paymybuddy.exceptions;

/**
 * Custom exception thrown when the provided password does not match the expected value during authentication
 * or password verification processes.
 */
public class PasswordIncorrectException extends RuntimeException{

    /**
     * Constructs a new PasswordIncorrectException with the specified detail message.
     *
     * @param message the detail message, which provides additional context about why the exception was thrown.
     */
    public PasswordIncorrectException(String message) {
        super(message);
    }

}
