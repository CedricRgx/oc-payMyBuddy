package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        setUserDTOHome(model, email);
        return "home";
    }

    /**
     * Handles the submission of the credit balance form.
     *
     * @param amount The amount to credit.
     * @param model The Model object used for adding attributes to be rendered on the view.
     * @return The redirect path after processing the credit balance.
     */
    @PostMapping("/creditBalance")
    public String creditBalance(@RequestParam("amount") double amount, Model model) {
        log.info("Credit balance: {}", amount);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Long userId = userService.getUserIdByEmail(email);
        setUserDTOHome(model, email);
        userService.creditUserBalance(userId, amount);

        model.addAttribute("successMessage", "Balance credited successfully.");

        return "home";
    }

    /**
     * Retrieves the UserDTO for the specified email and adds it to the Model.
     *
     * @param model The Model object to which the UserDTO will be added.
     * @param email The email of the user for whom the UserDTO needs to be retrieved.
     * @return The updated Model object with the UserDTO added, or the original Model object if the UserDTO retrieval fails.
     */
    public Model setUserDTOHome(Model model, String email){
        UserDTO userDTO = userService.getUserDTOFromUser(email);
        if(userDTO!=null){
            log.info("Success when displaying the userDTO's attributes");
            model.addAttribute("userDTO", userDTO);
        }else{
            log.error("Error when displaying the userDTO's attributes");
        }
        return model;
    }

}
