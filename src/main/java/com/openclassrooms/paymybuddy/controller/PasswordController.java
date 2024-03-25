package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.PasswordUpdateDTO;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller responsible for managing password updates for users of the PayMyBuddy application.
 */
@Controller
@Slf4j
public class PasswordController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Displays the form for updating a user's password. Initializes a PasswordUpdateDTO object to capture
     * user input and adds it to the model for form binding.
     *
     * @param model The model to which form data is added.
     * @return The name of the template displaying the password update form.
     */
    @GetMapping("/passwordUpdateForm")
    public String showChangePasswordForm(Model model) {
        log.info("passwordUpdateForm template for view");
        PasswordUpdateDTO passwordChangeDTO = PasswordUpdateDTO.builder().build();
        model.addAttribute("passwordChange", passwordChangeDTO);
        return "passwordUpdateForm";
    }


    /**
     * Processes the submission of the password update form. Validates the input against the PasswordUpdateDTO
     * class constraints.
     *
     * @param passwordUpdateDTO The DTO containing the password update form inputs.
     * @param result Contains binding result errors related to form validation.
     * @param model The model to which attributes can be added.
     * @return Redirects to the password update form with a success message if the update is successful,
     *         otherwise returns to the form displaying validation errors.
     */
    @PostMapping("/passwordUpdateForm")
    public String changePassword(@Valid @ModelAttribute("passwordChange") PasswordUpdateDTO passwordUpdateDTO, BindingResult result, Model model) {
        log.info("passwordUpdateForm template for update");
        if (result.hasErrors()) {
            return "passwordUpdateForm";
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentPasswordsaved = userAccountService.findByEmail(email).get().getPassword();

        if(!passwordEncoder.matches(passwordUpdateDTO.getCurrentPassword(), currentPasswordsaved)){
            result.rejectValue("currentPassword", "error.passwordChange", "The current password is incorrect.");
            return "passwordUpdateForm";
        }
        if(!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())){
            result.rejectValue("confirmPassword", "error.passwordChange", "The password confirmation does not match.");
            return "passwordUpdateForm";
        }

        userAccountService.savePassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()), email);
        return "redirect:/passwordUpdateForm?success";
    }

}
