package com.openclassrooms.paymybuddy.exceptions;

/**
 * Custom exception thrown when an attempt is made to use an email address that is already in use within the system.
 */
public class EmailAlreadyUsedException extends RuntimeException {

    /**
     * Constructs a new EmailAlreadyUsedException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
