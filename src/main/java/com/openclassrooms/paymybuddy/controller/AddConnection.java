package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AddConnection {

    @Autowired
    private UserService userService;

    @GetMapping("/addConnection")
    public String addConnection(@RequestParam("friendId") Long friendId) {
        log.info("addConnection template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        boolean resultAddConnection = userService.addConnection(userId, friendId);

        if(resultAddConnection){
            return "redirect:/listConnections?success=true&action=add";
        } else {
            return "redirect:/listConnections?error=true&action=remove";
        }
    }

}
