package com.openclassrooms.paymybuddy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible for handling the login view and actions within the PayMyBuddy application.
 */
@Controller
@Slf4j
public class LoginController {

    /**
     * Displays the login page and handles potential messages related to login actions.
     *
     * @param model The Model used to pass attributes to the view for rendering.
     * @param error A request parameter indicating that a login error has occurred. Used to show an error message.
     * @param logout A request parameter indicating that the user has successfully logged out. Used to show a logout message.
     * @param disabled A request parameter indicating that the user's account is disabled. Used to show a disabled account message.
     * @return The name of the login view template to be rendered.
     */
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String logout, @RequestParam(required = false) String disabled) {
        if(error!=null){
            model.addAttribute("errorMessage", "Your username or password is incorrect.");}
        if(logout!=null){
            model.addAttribute("logoutMessage", "You have been successfully logged out.");}
        if(disabled!=null){
            model.addAttribute("disabledMessage", "Your account has been disabled.");
        }
        return "login";
    }

}
