package com.openclassrooms.paymybuddy.exceptions;

/**
 * Custom exception thrown when an attempt to update the last connection date of a user in the database fails.
 */
public class UpdateLastConnectionDateFailedException extends RuntimeException{

    /**
     * Constructs a new UpdateLastConnectionDateFailedException with the specified detail message.
     *
     * @param message the detail message which explains the reason for the exception being thrown.
     */
    public UpdateLastConnectionDateFailedException(String message) {
        super(message);
    }

}
