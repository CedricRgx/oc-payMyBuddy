package com.openclassrooms.paymybuddy.exceptions;

/**
 * Custom exception thrown when a requested user is not found in the application's data storage.
 */
public class UserNotFoundException extends RuntimeException{


    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message which explains the reason for the exception being thrown.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

}
