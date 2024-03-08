package com.openclassrooms.paymybuddy.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    private String email;

    public EmailAlreadyUsedException(String email){
        super(String.format("This email is already in use"));
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
