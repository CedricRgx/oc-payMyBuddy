package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for managing the homepage of the PayMyBuddy application.
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * Displays the homepage for the PayMyBuddy application. This method retrieves the currently authenticated
     * user's information.
     *
     * @param model The Model object used for adding attributes to be rendered on the view.
     * @return The name of the homepage view template to be rendered.
     */
    @GetMapping("/home")
    public String viewHomePage(Model model){
        log.info("home template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.getUserDTOFromUser(email);
        if(userDTO!=null){
            model.addAttribute("userDTO", userDTO);
            return "home";
        }
        return "home";
    }

}
