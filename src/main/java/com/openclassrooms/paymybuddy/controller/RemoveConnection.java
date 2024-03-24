package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RemoveConnection {

    @Autowired
    private UserService userService;

    @GetMapping("/removeConnection")
    public String removeConnection(@RequestParam("friendId") Long friendId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        boolean resultRemoveConnection = userService.removeConnection(userId, friendId);

        if(resultRemoveConnection) {
            return "redirect:/listConnections?success=true&action=remove";
        } else {
            return "redirect:/listConnections?error=true&action=remove";
        }
    }
}
