package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling the contact page in the PayMyBuddy application.
 */
@Controller
@Slf4j
public class ContactController {

    @Autowired
    private UserService userService;

    /**
     * Displays the contact information page for the currently authenticated user.
     *
     * @param model The Model object used to pass attributes to the view.
     * @return The name of the contact view template to be rendered.
     */
    @GetMapping("/contact")
    public String viewContactPage(Model model){
        log.info("Contact template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.getUserDTOFromUser(email);
        if(userDTO!=null){
            model.addAttribute("userDTO", userDTO);
            return "contact";
        }
        return "contact";
    }
}
