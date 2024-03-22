package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private UserService userService;

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
