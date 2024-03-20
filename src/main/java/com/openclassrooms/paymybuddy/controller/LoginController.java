package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class LoginController {

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
