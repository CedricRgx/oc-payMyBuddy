package com.openclassrooms.paymybuddy.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public String handleEmailAlreadyUsedException(EmailAlreadyUsedException ex, Model model) {
        model.addAttribute("errorEmail", ex.getMessage());
        return "registrationForm";
    }
}