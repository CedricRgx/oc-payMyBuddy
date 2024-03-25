package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.service.impl.RegisterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller responsible for managing the registration of new users in the PayMyBuddy application.
 */
@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    /**
     * Displays the registration form to the user.
     *
     * @param model The model to which form data is added.
     * @return The name of the template displaying the registration form.
     */
    @GetMapping("/registration")
    public String viewRegistrationForm(Model model){
        log.info("registrationForm template");
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        model.addAttribute("registerDTO", registerDTO);
        return "registrationForm";
    }

    /**
     * Processes the submission of the registration form. Validates the provided user registration data against
     * defined constraints in RegisterDTO.
     *
     * @param registerDTO The DTO containing the user's registration information.
     * @param result Contains binding result errors related to form validation.
     * @param model The model to which attributes can be added, including validation errors or registration issues.
     * @return Redirects to the login page upon successful registration, otherwise returns to the registration form with errors.
     */
    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult result, Model model){
        log.info("registration of an user");
        if(result.hasErrors()){
            return "registrationForm";
        }
        try{
            registerService.addUser(registerDTO);
        }catch(EmailAlreadyUsedException e) {
            model.addAttribute("errorEmail", e.getMessage());
            log.error("This email is already in use: {}", e.getMessage());
            return "registrationForm";
        }catch(Exception e){
            model.addAttribute("error", "An error occurs");
            log.error("error", e);
            return "registrationForm";
        }
        return "redirect:/login";
    }
}
