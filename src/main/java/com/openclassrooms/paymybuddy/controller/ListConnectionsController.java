package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class ListConnectionsController {

    @Autowired
    private UserService userService;

    @GetMapping("/listConnections")
    public String showConnectionList(Model model) {
        log.info("listConnections template");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserIdByEmail(email);
        User userConnected = userService.getUserById(userId).get();
        List<User> listOfConnections = userService.getActiveFriends(userConnected.getFriends());
        if(listOfConnections == null) {
            log.error("Error when displaying the list of connections on listConnections template");
        } else {
            log.info("Success when displaying the list of connections on listConnections template");
            model.addAttribute("listOfConnections", listOfConnections);
        }
        return "listConnections";
    }

}
