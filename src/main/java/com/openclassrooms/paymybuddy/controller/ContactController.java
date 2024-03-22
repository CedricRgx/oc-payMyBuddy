package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@Slf4j
public class ContactController {

    @Autowired
    private UserService userService;

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
